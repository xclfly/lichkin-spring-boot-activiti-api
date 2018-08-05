package com.lichkin.framework.activiti.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.activiti.beans.in.impl.LKActivitiComplateProcessIn_SingleLineProcess;
import com.lichkin.framework.activiti.beans.in.impl.LKActivitiGetDetailProcessIn_SingleLineProcess;
import com.lichkin.framework.activiti.beans.in.impl.LKActivitiRejectProcessIn_SingleLineProcess;
import com.lichkin.framework.activiti.beans.in.impl.LKActivitiStartProcessIn_SingleLineProcess;
import com.lichkin.framework.activiti.beans.in.impl.LKActivitiStartProcessTaskIn_SingleLineProcess;
import com.lichkin.framework.activiti.beans.out.impl.LKActivitiCompleteProcessOut_SingleLineProcess;
import com.lichkin.framework.activiti.beans.out.impl.LKActivitiGetDetailProcessOut_SingleLineProcess;
import com.lichkin.framework.activiti.beans.out.impl.LKActivitiRejectProcessOut_SingleLineProcess;
import com.lichkin.framework.activiti.beans.out.impl.LKActivitiStartProcessOut_SingleLineProcess;
import com.lichkin.framework.defines.activiti.services.LKActivitiCompleteProcessService;
import com.lichkin.framework.defines.activiti.services.LKActivitiGetDetailProcessService;
import com.lichkin.framework.defines.activiti.services.LKActivitiRejectProcessService;
import com.lichkin.framework.defines.activiti.services.LKActivitiStartProcessService;
import com.lichkin.framework.defines.activiti.statics.LKActivitiStatics;
import com.lichkin.framework.json.LKJsonUtils;

/**
 * 流程服务类 -> 单线流程
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class LKActivitiService_SingleLineProcess implements

		LKActivitiStatics,

		LKActivitiStartProcessService<LKActivitiStartProcessIn_SingleLineProcess, LKActivitiStartProcessOut_SingleLineProcess>,

		LKActivitiCompleteProcessService<LKActivitiComplateProcessIn_SingleLineProcess, LKActivitiCompleteProcessOut_SingleLineProcess>,

		LKActivitiGetDetailProcessService<LKActivitiGetDetailProcessIn_SingleLineProcess, LKActivitiGetDetailProcessOut_SingleLineProcess>,

		LKActivitiRejectProcessService<LKActivitiRejectProcessIn_SingleLineProcess, LKActivitiRejectProcessOut_SingleLineProcess>

{

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private ManagementService managementService;


	/**
	 * 启动流程
	 * @param in 入参
	 * @return 流程实例
	 */
	@Override
	@Transactional(transactionManager = "activitiPlatformTransactionManager")
	public LKActivitiStartProcessOut_SingleLineProcess startProcess(LKActivitiStartProcessIn_SingleLineProcess in) {
		Map<String, Object> variables = new HashMap<>();
		// 设置通用流程变量
		variables.put(KEY_PROCESS_KEY, in.getProcessKey());
		variables.put(KEY_PROCESS_NAME, in.getProcessName());
		variables.put(KEY_PROCESS_TYPE, in.getProcessType().toString());

		// 设置其它流程变量
		List<LKActivitiStartProcessTaskIn_SingleLineProcess> taskInfos = in.getTaskList();
		//
		variables.put(KEY_TASKINFOS, taskInfos);
		// 根据传的节点数量自动创建节点数
		variables.put(KEY_TASKINFOS_JSON, LKJsonUtils.toJson(taskInfos));
		// 设置流程发起人
		variables.put(KEY_START_USER_NAME, taskInfos.get(0).getUserName());

		// 启动流程
		ProcessInstance instance = runtimeService.startProcessInstanceByKey(in.getProcessKey(), variables);

		// 完成第一步处理
		completeProcess(new LKActivitiComplateProcessIn_SingleLineProcess(instance.getProcessInstanceId(), taskInfos.get(0).getUserId(), in.getComment()));

		// 初始化返回值
		LKActivitiStartProcessOut_SingleLineProcess out = new LKActivitiStartProcessOut_SingleLineProcess(instance.getProcessInstanceId());

		// 返回结果
		return out;
	}


	/**
	 * 获取当前流程正在进行的task
	 * @param processInstanceId
	 * @param userId
	 * @return
	 */
	@Transactional(transactionManager = "activitiPlatformTransactionManager")
	public Task getCurrentTask(String processInstanceId, String userId) {
		return taskService.createTaskQuery().processInstanceId(processInstanceId).taskCandidateOrAssigned(userId).singleResult();
	}


	/**
	 * 办理流程
	 * @param in 入参
	 * @return 流程实例
	 */
	@Override
	@Transactional(transactionManager = "activitiPlatformTransactionManager")
	public LKActivitiCompleteProcessOut_SingleLineProcess completeProcess(LKActivitiComplateProcessIn_SingleLineProcess in) {
		// 查询当前要办理的task
		Task task = getCurrentTask(in.getProcessInstanceId(), in.getUserId());
		// 获取流程变量的参数信息
		Map<String, Object> variables = taskService.getVariables(task.getId());
		@SuppressWarnings("unchecked")
		List<LKActivitiStartProcessTaskIn_SingleLineProcess> taskInfos = (List<LKActivitiStartProcessTaskIn_SingleLineProcess>) variables.get(KEY_TASKINFOS);
		taskInfos.remove(0);
		variables.put(KEY_TASKINFOS, taskInfos);
		// 增加审批时的备注信息
		if (StringUtils.isNotBlank(in.getComment())) {
			taskService.addComment(task.getId(), in.getProcessInstanceId(), in.getComment());
		}
		taskService.complete(task.getId(), variables);

		boolean processIsEnd = false;
		// 查询当前流程是否已经结束
		HistoricProcessInstance process = historyService.createHistoricProcessInstanceQuery().processInstanceId(in.getProcessInstanceId()).finished().singleResult();
		if (process != null) {
			processIsEnd = true;
		}
		return new LKActivitiCompleteProcessOut_SingleLineProcess(processIsEnd);
	}


	/**
	 * 查询流程变量
	 * @param processInstanceIds
	 * @param variableNames
	 * @return
	 */
	private List<HistoricVariableInstance> getHisVariableInfoByNames(String processInstanceIds, String variableNames) {
		return historyService.createNativeHistoricVariableInstanceQuery().sql("SELECT * FROM " + managementService.getTableName(HistoricVariableInstance.class) + " T WHERE T.NAME_ in (" + variableNames + ") and T.PROC_INST_ID_ in (" + processInstanceIds + ") ").list();
	}


	/**
	 * 获取流程详情
	 * @param in 入参
	 * @return 流程实例
	 */
	@Override
	public List<LKActivitiGetDetailProcessOut_SingleLineProcess> getDetailProcess(LKActivitiGetDetailProcessIn_SingleLineProcess in) {
		// 流程所有节点列表（包含未审批的）
		List<LKActivitiGetDetailProcessOut_SingleLineProcess> taskAllList = new ArrayList<>();

		// 从His中获取processInstance
		HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(in.getProcessInstanceId()).singleResult();
		if (processInstance != null) {
			String variableNames = "'" + KEY_PROCESS_NAME + "','" + KEY_TASKINFOS_JSON + "'";
			List<HistoricVariableInstance> variableList = getHisVariableInfoByNames(in.getProcessInstanceId(), variableNames);

			String processName = "";
			String taskInfoJson = "";
			for (HistoricVariableInstance variable : variableList) {
				if (variable.getVariableName().equals(KEY_PROCESS_NAME)) {
					processName = String.valueOf(variable.getValue());
				}
				if (variable.getVariableName().equals(KEY_TASKINFOS_JSON)) {
					taskInfoJson = String.valueOf(variable.getValue());
				}
			}

			List<LKActivitiStartProcessTaskIn_SingleLineProcess> taskInfos = LKJsonUtils.toList(taskInfoJson, LKActivitiStartProcessTaskIn_SingleLineProcess.class);

			// 根据ProcessDefinitionId查询BpmnModel 获取所有的流程节点
			BpmnModel model = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
			if (model != null) {
				int step = taskInfos.size();
				Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
				int i = 0;
				for (FlowElement e : flowElements) {
					// 只获取UserTask的节点
					if ((e instanceof UserTask) && (i < step)) {
						LKActivitiStartProcessTaskIn_SingleLineProcess taskInfo = taskInfos.get(i);
						LKActivitiGetDetailProcessOut_SingleLineProcess taskAll = new LKActivitiGetDetailProcessOut_SingleLineProcess(in.getProcessInstanceId());
						taskAll.setTaskId(e.getId());
						taskAll.setTaskName(taskInfo.getTaskName());
						taskAll.setApproveUserName(taskInfo.getUserName());
						taskAll.setProcessName(processName);
						taskAll.setProcessStartUserName(taskInfos.get(0).getUserName());
						taskAll.setProcessStartTime(processInstance.getStartTime());
						// 流程结束时才有endTime
						if (processInstance.getEndTime() != null) {
							taskAll.setProcessIsEnd(true);
							taskAll.setProcessEndTime(processInstance.getEndTime());
						}
						taskAllList.add(taskAll);
						i++;
					}
				}
			}

			getApproveHistory(in.getProcessInstanceId(), taskAllList);
		}
		return taskAllList;
	}


	/**
	 * 获取流程中已经变动的节点
	 * @param processInstanceId
	 * @return
	 */
	private void getApproveHistory(String processInstanceId, List<LKActivitiGetDetailProcessOut_SingleLineProcess> taskAllList) {
		// 查询流程的备注信息
		List<Comment> commentList = taskService.getProcessInstanceComments(processInstanceId);

		// 该流程实例的所有节点审批记录
		List<HistoricTaskInstance> hisTaskList = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).orderByHistoricTaskInstanceStartTime().asc().list();
		for (Iterator<HistoricTaskInstance> iterator = hisTaskList.iterator(); iterator.hasNext();) {
			HistoricTaskInstance taskInstance = iterator.next();
			for (LKActivitiGetDetailProcessOut_SingleLineProcess taskAll : taskAllList) {
				if (taskAll.getTaskId().equals(taskInstance.getTaskDefinitionKey())) {
					taskAll.setTaskName(taskInstance.getName());
					taskAll.setTaskStartTime(taskInstance.getStartTime());
					// 获取流程节点结束时间(未审批时此值为空)
					taskAll.setTaskEndTime(taskInstance.getEndTime());
					if (CollectionUtils.isNotEmpty(commentList)) {
						for (Comment comment : commentList) {
							if (comment.getTaskId().equals(taskInstance.getId())) {
								taskAll.setTaskComment(comment.getFullMessage());
								break;
							}
						}
					}
					break;
				}
			}

		}
	}


	@Override
	public LKActivitiRejectProcessOut_SingleLineProcess RejectProcess(LKActivitiRejectProcessIn_SingleLineProcess in) {
		processReject(in.getProcessInstanceId(), in.getUserId());
		return null;
	}


	public void processReject(String processInstanceId, String userId) {
		// 查询当前要办理的task
		Task myTask = getCurrentTask(processInstanceId, userId);
		if (myTask == null) {
			throw new ServiceException("流程未启动或已执行完成，无法撤回");
		}

		String processDefinitionId = myTask.getProcessDefinitionId();
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);

		String currentActivityId = myTask.getTaskDefinitionKey();

		FlowNode currentFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(currentActivityId);

		// 获取目标节点
		FlowNode targetFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement("step1");

		// 记录原活动方向
		List<SequenceFlow> oriSequenceFlows = new ArrayList<>();
		oriSequenceFlows.addAll(currentFlowNode.getOutgoingFlows());

		// 清理活动方向
		currentFlowNode.getOutgoingFlows().clear();

		// 建立新方向
		List<SequenceFlow> newSequenceFlowList = new ArrayList<>();
		SequenceFlow newSequenceFlow = new SequenceFlow();

		String uuid = UUID.randomUUID().toString().replace("-", "");
		newSequenceFlow.setId(uuid);
		newSequenceFlow.setSourceFlowElement(currentFlowNode);
		newSequenceFlow.setTargetFlowElement(targetFlowNode);
		newSequenceFlowList.add(newSequenceFlow);
		currentFlowNode.setOutgoingFlows(newSequenceFlowList);

		// Authentication.setAuthenticatedUserId(userId);
		taskService.addComment(myTask.getId(), myTask.getProcessInstanceId(), "驳回");

		Map<String, Object> currentVariables = myTask.getProcessVariables();
		// 完成任务
		taskService.complete(myTask.getId(), currentVariables);
		// 恢复原方向
		currentFlowNode.setOutgoingFlows(oriSequenceFlows);

		// runtimeService.deleteProcessInstance(processInstanceId, "驳回删除流程");

	}

}
