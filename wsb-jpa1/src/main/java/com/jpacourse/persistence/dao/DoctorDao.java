package com.jpacourse.persistence.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.enums.TreatmentType;

public interface DoctorDao extends Dao<DoctorEntity, Long> {
    List<DoctorEntity> findByVisitDescription(String pDesc);
    List<DoctorEntity> findByMinNumberOfVisits(long pNumOfVisits);
    List<DoctorEntity> findByVisitDateRange(LocalDateTime pDateFrom, LocalDateTime pDateTo);
    List<DoctorEntity> findByPatient(String pPatientName);
    List<DoctorEntity> findByTreatmentType(TreatmentType pTreatmentType);
    long countVisitsOfDoctorInTimeRange(String pDocName, LocalDateTime pDateFrom, LocalDateTime pDateTo);
    List<DoctorEntity> findAllByJpql();
}
