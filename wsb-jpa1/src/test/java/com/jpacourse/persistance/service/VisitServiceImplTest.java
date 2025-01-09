package com.jpacourse.persistance.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.enums.TreatmentType;
import com.jpacourse.service.VisitService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitServiceImplTest {
    
    @Autowired
    private VisitService visitService;

    @Autowired
    private PatientDao patientDao;

    @Test
    public void testGetVisitsByPatientId() {
        // given
        Long patientId = 1L;
        PatientEntity patientEntity = patientDao.findOne(patientId);

        assertThat(patientEntity).isNotNull();

        // when
        List<VisitTO> visits = visitService.getVisitsByPatientId(patientEntity.getId());

        // then
        assertThat(visits).isNotNull().isNotEmpty();

        visits.forEach(visit -> {
            assertThat(visit.getTime()).isInstanceOf(LocalDateTime.class);
            
            assertThat(visit.getDoctorName()).isInstanceOf(String.class)
                .isNotEmpty();
            
            assertThat(visit.getMedicalTreatments()).isNotNull()
                .isNotEmpty();

            visit.getMedicalTreatments().forEach(treatment -> 
                assertThat(treatment).isInstanceOf(TreatmentType.class)
            );
        });
    }
}
