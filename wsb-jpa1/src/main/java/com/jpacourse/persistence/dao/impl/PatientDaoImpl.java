package com.jpacourse.persistence.dao.impl;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public VisitEntity addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String visitDescription) {
        // Find the patient by ID
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);

        // Find the doctor by ID
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);

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

        // Persist the visit explicitly
        entityManager.persist(visit);

        // Add the visit to the patient's list of visits (with cascading update)
        patient.addVisit(visit);

        // Perform the merge operation to update the patient with the new visit
        entityManager.merge(patient);

        // Flush the persistence context to ensure ID generation
        entityManager.flush();

        return visit;
    }
}
