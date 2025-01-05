package com.jpacourse.persistence.dao;

import java.util.List;

import com.jpacourse.persistence.entity.DoctorEntity;

public interface DoctorDao extends Dao<DoctorEntity, Long> {
    List<DoctorEntity> findByPatientName(String pPatientName);
    List<DoctorEntity> findVisitByPatientId(Long pId);
    List<DoctorEntity> findWithMoreThanXVisits(Long visitCount);
    List<DoctorEntity> findByPatientPhoneNumber(String visitCount);
    void updateDoctor(Long doctorId, String newFirstName);
    
}
