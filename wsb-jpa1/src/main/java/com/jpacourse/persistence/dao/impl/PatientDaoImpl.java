package com.jpacourse.persistence.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @Autowired
    private DoctorDao doctorDao;

    @Override
    public VisitEntity addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String visitDescription) {
        // Find the patient by ID
        PatientEntity patient = findOne(patientId);

        // Find the doctor by ID
        DoctorEntity doctor = doctorDao.findOne(doctorId);

        // Check if both the patient and doctor exist
        if(patient == null || doctor == null) {
            // Handle the case where the patient or doctor is not found
            throw new IllegalArgumentException("Patient or doctor not found.");
        }

        // Create a new visit entity
        VisitEntity visit = new VisitEntity();
        visit.setTime(visitTime);
        visit.setDescription(visitDescription);
        visit.setDoctor(doctor);
        visit.setPatient(patient);

        // Add the visit to the patient's list of visits (with cascading update)
        patient.addVisit(visit);

        // Perform the merge operation to update the patient with the new visit
        entityManager.merge(patient);

        return visit;
    }

    @Override
    public List<PatientEntity> findByPatientName(String patientName) {
        return entityManager.createQuery(
            "SELECT pat FROM PatientEntity pat WHERE pat.lastName =: patientName",
            PatientEntity.class
        ).setParameter("patientName", patientName)
        .getResultList();
    }

    @Override
    public List<PatientEntity> findWithMoreThanXVisits(Long visitCount) {
        return entityManager.createQuery(
            "SELECT pat FROM PatientEntity pat " +
            " JOIN pat.visits vis " +
            "GROUP BY pat " +
            "HAVING count(vis) >= :visitCount",
            PatientEntity.class
        ).setParameter("visitCount", visitCount)
        .getResultList();
    }

    @Override
    public List<PatientEntity> findByPatientPhoneNumber(String phoneNumberFragment) {
        return entityManager.createQuery(
            "SELECT pat FROM PatientEntity pat WHERE pat.telephoneNumber like :phoneNumberFragment",
            PatientEntity.class
        ).setParameter("phoneNumberFragment", "%" + phoneNumberFragment + "%")
        .getResultList();
    }

    @Override
    public void updatPatient(Long patientId, String newFirstName) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);

        // Wykonanie zmiany
        patient.setFirstName(newFirstName);

        // Zapisanie zmian
        entityManager.merge(patient);
        entityManager.flush();
    }
}
