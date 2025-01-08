package com.jpacourse.persistence.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.entity.DoctorEntity;

@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao {


    @Override
    public List<DoctorEntity> findByPatientName(String pPatientName) {
        return entityManager.createQuery(
            " select doc from DoctorEntity doc " +
            " join doc.visits visit " +
            " join visit.patient patient " +
            " where patient.lastName =: name", DoctorEntity.class)
            .setParameter("name", pPatientName)
            .getResultList();
    }

    @Override
    public List<DoctorEntity> findVisitByPatientId(Long pId) {
        return entityManager.createQuery(
            " select doc from DoctorEntity doc " +
            " join doc.visits visit " +
            " join visit.patient patient " +
            " where patient.id =: id", DoctorEntity.class)
            .setParameter("id", pId)
            .getResultList();
    }


    @Override
    public List<DoctorEntity> findWithMoreThanXVisits(Long pVisitCount) {
        return entityManager.createQuery(
            " select doc from DoctorEntity doc " +
            " join doc.visits visit " +
            " group by doc " +
            " having count(visit) >= :visitCount", DoctorEntity.class)
            .setParameter("visitCount", pVisitCount)
            .getResultList();
    }

    @Override
    public List<DoctorEntity> findByPatientPhoneNumber(String phoneNumberFragment) {
        return entityManager.createQuery(
            " select doc from DoctorEntity doc " +
            " join doc.visits visit " +
            " join visit.patient patient " +
            " where patient.telephoneNumber like :phoneNumberFragment", DoctorEntity.class)
            .setParameter("phoneNumberFragment", "%" + phoneNumberFragment + "%")
            .getResultList();
    }

    @Override
    public void updateDoctor(Long doctorId, String newFirstName) {
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);

        // Wykonanie zmiany
        doctor.setFirstName(newFirstName);

        // Zapisanie zmian
        entityManager.merge(doctor);  // merge automatycznie obsługuje wersjonowanie
    }
}
