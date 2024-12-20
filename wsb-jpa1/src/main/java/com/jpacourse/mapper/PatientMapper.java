package com.jpacourse.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

public final class PatientMapper {

    public static PatientTO toTO(final PatientEntity patientEntity) {
        if (patientEntity == null) {
            return null;
        }

        final PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setIsActive(patientEntity.getIsActive());
        patientTO.setAddress(patientEntity.getAddress() != null ? patientEntity.getAddress().getCity() : null);

        // Map the visits list using toVisitTO
        List<VisitTO> visitTOs = patientEntity.getVisits().stream()
                .map(PatientMapper::toVisitTO)
                .collect(Collectors.toList());
        patientTO.setVisits(visitTOs);

        return patientTO;
    }

    public static PatientEntity toEntity(final PatientTO patientTO) {
        if (patientTO == null) {
            return null;
        }

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setIsActive(patientTO.getIsActive());

        return patientEntity;
    }

    private static VisitTO toVisitTO(final VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }

        VisitTO visitTO = new VisitTO();
        visitTO.setTime(visitEntity.getTime());
        visitTO.setDoctorName(visitEntity.getDoctor().getFirstName() + " " + visitEntity.getDoctor().getLastName());

        // Map the TreatmentType enum values to strings
        List<String> medicalTreatments = visitEntity.getTreatments().stream()
                .map(treatment -> treatment.getType().toString()) // Convert TreatmentType to String
                .collect(Collectors.toList());
        visitTO.setMedicalTreatments(medicalTreatments);

        return visitTO;
    }
}
