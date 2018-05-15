package com.lichkin.activiti.daos.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.springframework.entities.impl.SysActivitiConfigEntity;

@Repository
public interface SysActivitiConfigDao extends JpaRepository<SysActivitiConfigEntity, String> {

	public SysActivitiConfigEntity findOneByUsingStatusAndCompIdAndProcessCode(LKUsingStatusEnum usingStatus, String compId, String processCode);

}
