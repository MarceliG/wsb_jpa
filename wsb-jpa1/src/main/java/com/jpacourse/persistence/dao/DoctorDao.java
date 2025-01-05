package com.jpacourse.persistence.dao;

import java.util.List;

import com.jpacourse.persistence.entity.DoctorEntity;

public interface DoctorDao extends Dao<DoctorEntity, Long> {
    List<DoctorEntity> findByPatientName(String pPatientName);

}
