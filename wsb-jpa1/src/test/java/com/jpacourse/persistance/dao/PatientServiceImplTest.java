package com.jpacourse.persistance.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.enums.TreatmentType;
import com.jpacourse.service.PatientService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceImplTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientDao patientDao;

    @Transactional
    @Test
    public void testShouldReturnPatientTOWithCorrectFields() {
        // given
        PatientEntity patientEntity = patientDao.findOne(1L); // assuming patient with ID 1 exists
        assertThat(patientEntity).isNotNull();

        // when
        PatientTO patientTO = patientService.getPatient(1L);

        // then
        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getId()).isEqualTo(patientEntity.getId());
        assertThat(patientTO.getFirstName()).isEqualTo(patientEntity.getFirstName());
        assertThat(patientTO.getLastName()).isEqualTo(patientEntity.getLastName());
        assertThat(patientTO.getIsActive()).isEqualTo(patientEntity.getIsActive());
        assertThat(patientTO.getAddress()).isEqualTo(patientEntity.getAddress().getCity()); // Address field
        assertThat(patientTO.getVisits()).isNotEmpty(); // Assuming patient has visits

        VisitTO visit = patientTO.getVisits().get(0);
        assertThat(visit.getTime()).isNotNull();
        assertThat(visit.getDoctorName()).isNotEmpty();
        assertThat(visit.getMedicalTreatments()).isNotNull().containsAnyOf(TreatmentType.values());
    }
}
