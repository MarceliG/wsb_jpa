package com.jpacourse.persistence.dao.impl;

import java.time.LocalDateTime;

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
}
