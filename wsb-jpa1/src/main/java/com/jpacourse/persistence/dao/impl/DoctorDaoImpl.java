package com.jpacourse.persistence.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.enums.TreatmentType;

@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao {

    @Override
    public List<DoctorEntity> findByVisitDescription(String pDesc) {
        return entityManager.createQuery("SELECT DISTINCT doc FROM DoctorEntity doc " +
            " JOIN doc.visits visit " +
            " WHERE visit.description like :desc ", DoctorEntity.class
        ).setParameter("desc", "%"+pDesc+"%")
        .getResultList();
    }

    @Override
    public List<DoctorEntity> findByMinNumberOfVisits(long pNumOfVisits) {
        return entityManager.createQuery("SELECT doc from DoctorEntity doc " +
            " JOIN doc.visits visit " +
            " GROUP BY doc " +
            " HAVING COUNT(visit) >= :visitNum", DoctorEntity.class
        ).setParameter("visitNum", pNumOfVisits)
        .getResultList(); 
    }

    @Override
    public List<DoctorEntity> findByVisitDateRange(LocalDateTime pDateFrom, LocalDateTime pDateTo) {
        return entityManager.createQuery("SELECT doc FROM DoctorEntity doc " +
            " JOIN doc.visits visit " +
            " WHERE visit.time BETWEEN :timeFrom AND :timeTo ", DoctorEntity.class
        ).setParameter("timeFrom", pDateFrom)
        .setParameter("timeTo", pDateTo)
        .getResultList();
    }

    @Override
    public List<DoctorEntity> findByPatient(String pPatientName) {
        return entityManager.createQuery("SELECT doc FROM DoctorEntity doc " +
            " JOIN doc.visits visit " +
            " JOIN visit.patient patient " +
            " WHERE patient.lastName = :name ", DoctorEntity.class
        ).setParameter("name", pPatientName)
        .getResultList();
    }

    @Override
    public List<DoctorEntity> findByTreatmentType(TreatmentType pTreatmentType) {
        return entityManager.createQuery("SELECT doc FROM DoctorEntity doc " +
            "JOIN doc.visits visit " +
            "JOIN visit.treatments treatment " +
            "WHERE treatment.type = :type", 
            DoctorEntity.class
        ).setParameter("type", pTreatmentType)
        .getResultList();
    }

    @Override
    public long countVisitsOfDoctorInTimeRange(String pDocName, LocalDateTime pDateFrom, LocalDateTime pDateTo) {
        return entityManager.createQuery("SELECT COUNT(visit) FROM DoctorEntity doc " +
            " JOIN doc.visits visit " +
            " WHERE visit.time BETWEEN :timeFrom AND :timeTo " +
            " AND doc.lastName = :name ", Long.class
        ).setParameter("timeFrom", pDateFrom)
        .setParameter("timeTo", pDateTo)
        .setParameter("name", pDocName)
        .getSingleResult();
    }

    @Override
    public List<DoctorEntity> findAllByJpql() {
        return entityManager.createQuery("FROM DoctorEntity", DoctorEntity.class).getResultList();
    }

}
