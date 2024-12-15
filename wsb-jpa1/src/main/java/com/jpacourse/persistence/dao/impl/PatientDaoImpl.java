package com.jpacourse.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

}
