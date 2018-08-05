package com.lichkin.framework.defines.activiti.beans.out;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 审批流程出参
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@RequiredArgsConstructor
public class LKActivitiRejectProcessOut {

	/** 流程是否结束 */
	private final boolean processIsEnd;

}
