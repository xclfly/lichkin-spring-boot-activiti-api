package com.lichkin.activiti.daos.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lichkin.springframework.entities.impl.SysActivitiApiRequestLogStartProcessEntity;

@Repository
public interface SysActivitiApiRequestLogStartProcessDao extends JpaRepository<SysActivitiApiRequestLogStartProcessEntity, String> {

}
