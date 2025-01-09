package com.jpacourse.persistence.dao;

import java.util.List;

import com.jpacourse.persistence.entity.VisitEntity;

public interface VisitDao extends Dao<VisitEntity, Long> {
    public List<VisitEntity> findVisitsByPatientId(Long patientId);
}