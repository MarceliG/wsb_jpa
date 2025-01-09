package com.jpacourse.service;

import java.util.List;

import com.jpacourse.dto.VisitTO;

public interface VisitService {
    List<VisitTO> getVisitsByPatientId(Long patientId);
}
