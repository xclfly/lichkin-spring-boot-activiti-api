package com.lichkin.springframework.db.dao.impl;

import static com.lichkin.springframework.db.LKDBActivitiStatics.DAO_NAME;
import static com.lichkin.springframework.db.LKDBActivitiStatics.PERSISTENCE_UNIT;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.lichkin.springframework.db.daos.LKBaseDao;

/**
 * 数据库访问类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Component(DAO_NAME)
public class LKActivitiDao extends LKBaseDao {

	/** 实体类管理类对象 */
	@PersistenceContext(unitName = PERSISTENCE_UNIT)
	private EntityManager entityManager;


	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
