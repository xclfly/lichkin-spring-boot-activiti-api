package com.lichkin.framework.defines.activiti.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.framework.defines.activiti.beans.in.LKActivitiGetDoneProcessIn;
import com.lichkin.framework.defines.activiti.beans.in.LKActivitiGetPendingProcessIn;
import com.lichkin.framework.defines.activiti.beans.out.LKActivitiGetDoneProcessOut;
import com.lichkin.framework.defines.activiti.beans.out.LKActivitiGetPendingProcessOut;
import com.lichkin.framework.defines.activiti.beans.out.LKActivitiProcessTaskInfoOut;
import com.lichkin.framework.defines.activiti.services.LKActivitiGetDoneProcessService;
import com.lichkin.framework.defines.activiti.services.LKActivitiGetPendingProcessService;
import com.lichkin.framework.defines.activiti.statics.LKActivitiStatics;
import com.lichkin.framework.utils.LKBeanUtils;

/**
 * 流程服务类 -> 单线流程
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class LKActivitiService implements

		LKActivitiStatics,

		LKActivitiGetPendingProcessService<LKActivitiGetPendingProcessIn, LKActivitiGetPendingProcessOut>,

		LKActivitiGetDoneProcessService<LKActivitiGetDoneProcessIn, LKActivitiGetDoneProcessOut>

{

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private ManagementService managementService;


	/**
	 * 获取待办流程
	 * @param in 入参
	 * @return 流程实例
	 */
	@Override
	public List<LKActivitiGetPendingProcessOut> getPendingProcess(LKActivitiGetPendingProcessIn in) {
		List<Task> taskList = taskService.createTaskQuery().taskAssignee(in.getUserId()).active().orderByTaskCreateTime().asc().listPage(in.getFirstResult(), in.getMaxResults());
		Set<String> idsSet = new HashSet<>();

		List<LKActivitiProcessTaskInfoOut> taskInfoList = new ArrayList<>();
		for (Task task : taskList) {
			idsSet.add(task.getProcessInstanceId());
			LKActivitiProcessTaskInfoOut taskInfo = new LKActivitiProcessTaskInfoOut();
			taskInfo.setProcessInstanceId(task.getProcessInstanceId());
			taskInfo.setTaskName(task.getName());
			taskInfo.setTaskStartTime(task.getCreateTime());
			taskInfoList.add(taskInfo);
		}
		setProcessInfo(taskInfoList, idsSet);

		List<LKActivitiGetPendingProcessOut> outList = new ArrayList<>();
		for (LKActivitiProcessTaskInfoOut taskInfo : taskInfoList) {
			LKActivitiGetPendingProcessOut out = new LKActivitiGetPendingProcessOut(taskInfo.getProcessInstanceId());
			outList.add(LKBeanUtils.copyProperties(taskInfo, out));
		}
		return outList;
	}


	/**
	 * 获取已办流程
	 * @param in 入参
	 * @return 流程实例
	 */
	@Override
	public List<LKActivitiGetDoneProcessOut> getDoneProcess(LKActivitiGetDoneProcessIn in) {
		List<HistoricTaskInstance> hisTaskList = historyService.createHistoricTaskInstanceQuery().taskAssignee(in.getUserId()).finished().orderByTaskCreateTime().desc().listPage(in.getFirstResult(), in.getMaxResults());

		List<LKActivitiProcessTaskInfoOut> taskInfoList = new ArrayList<>();
		Set<String> idsSet = new HashSet<>();
		for (HistoricTaskInstance hisTask : hisTaskList) {
			idsSet.add(hisTask.getProcessInstanceId());

			LKActivitiProcessTaskInfoOut taskInfo = new LKActivitiProcessTaskInfoOut();
			taskInfo.setProcessInstanceId(hisTask.getProcessInstanceId());
			taskInfo.setTaskName(hisTask.getName());
			taskInfo.setTaskStartTime(hisTask.getStartTime());
			taskInfo.setTaskEndTime(hisTask.getEndTime());
			taskInfoList.add(taskInfo);
		}
		setProcessInfo(taskInfoList, idsSet);

		List<LKActivitiGetDoneProcessOut> outList = new ArrayList<>();
		for (LKActivitiProcessTaskInfoOut taskInfo : taskInfoList) {
			LKActivitiGetDoneProcessOut out = new LKActivitiGetDoneProcessOut(taskInfo.getProcessInstanceId());
			outList.add(LKBeanUtils.copyProperties(false, taskInfo, out));
		}
		return outList;
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
	 * 设置流程信息
	 * @param taskInfoList
	 * @param processInstanceIdsSet
	 */
	private void setProcessInfo(List<LKActivitiProcessTaskInfoOut> taskInfoList, Set<String> processInstanceIdsSet) {
		if (CollectionUtils.isNotEmpty(taskInfoList)) {
			List<HistoricProcessInstance> procList = historyService.createHistoricProcessInstanceQuery().processInstanceIds(processInstanceIdsSet).list();

			String processInstanceIds = StringUtils.join(processInstanceIdsSet, ",");
			String variableNames = "'" + KEY_PROCESS_TYPE + "','" + KEY_PROCESS_NAME + "','" + KEY_START_USER_NAME + "'";
			List<HistoricVariableInstance> variableList = getHisVariableInfoByNames(processInstanceIds, variableNames);

			// 查询每个流程当前活跃的任务节点
			List<Task> currentTasks = taskService.createTaskQuery().processInstanceIdIn(new ArrayList<>(processInstanceIdsSet)).active().list();

			// 设置流程相关信息
			for (LKActivitiProcessTaskInfoOut taskInfo : taskInfoList) {
				// 设置流程开始时间
				for (HistoricProcessInstance proc : procList) {
					if (taskInfo.getProcessInstanceId().equals(proc.getId())) {
						taskInfo.setProcessStartTime(proc.getStartTime());
						if (proc.getEndTime() != null) {
							taskInfo.setProcessIsEnd(true);
							taskInfo.setProcessEndTime(proc.getEndTime());
						}
						break;
					}
				}
				// 设置流程当前活跃的节点
				for (Task task : currentTasks) {
					if (taskInfo.getProcessInstanceId().equals(task.getProcessInstanceId())) {
						taskInfo.setActiveTaskName(task.getName());
						break;
					}
				}

				for (HistoricVariableInstance variable : variableList) {
					if (taskInfo.getProcessInstanceId().equals(variable.getProcessInstanceId())) {
						// 设置流程类型
						if (variable.getVariableName().equals(KEY_PROCESS_TYPE)) {
							taskInfo.setProcessType(String.valueOf(variable.getValue()));
						}
						// 设置流程名
						if (variable.getVariableName().equals(KEY_PROCESS_NAME)) {
							taskInfo.setProcessName(String.valueOf(variable.getValue()));
						}
						// 设置流程发起人姓名
						if (variable.getVariableName().equals(KEY_START_USER_NAME)) {
							taskInfo.setProcessStartUserName(String.valueOf(variable.getValue()));
						}
					}
				}
			}
		}
	}

}
