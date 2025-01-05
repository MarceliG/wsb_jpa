package com.jpacourse.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.TreatmentType;

public final class VisitMapper {

    public static VisitTO toVisitTO(final VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }

        VisitTO visitTO = new VisitTO();
        visitTO.setTime(visitEntity.getTime());
        visitTO.setDoctorName(visitEntity.getDoctor().getFirstName() + " " + visitEntity.getDoctor().getLastName());

        // Map the TreatmentType enum values
        List<TreatmentType> medicalTreatments = visitEntity.getTreatments().stream()
                .map(treatment -> treatment.getType())
                .collect(Collectors.toList());
        visitTO.setMedicalTreatments(medicalTreatments);

        return visitTO;
    }
}
