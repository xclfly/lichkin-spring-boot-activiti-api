package com.lichkin.activiti.daos.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lichkin.springframework.entities.impl.SysActivitiConfigEntity;

@Repository
public interface SysActivitiConfigDao extends JpaRepository<SysActivitiConfigEntity, String> {

	public SysActivitiConfigEntity findOneByProcessKey(String processKey);

}
