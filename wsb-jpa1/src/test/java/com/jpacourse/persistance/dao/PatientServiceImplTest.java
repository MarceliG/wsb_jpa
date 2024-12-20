package com.jpacourse.persistance.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.PatientService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceImplTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private VisitDao visitDao;

    // @Transactional
    // @Test
    // public void testShouldDeletePatientAndCascadeVisits() {
    // // given
    // PatientEntity patient = patientDao.findOne(1L); // assuming patient with ID 1
    // exists
    // assertThat(patient).isNotNull();
    // Long patientId = patient.getId();
    // Long visitCountBefore = visitDao.count(); // count visits before deletion

    // // when
    // patientService.deletePatient(patientId);

    // // then
    // // Check that the patient is deleted
    // PatientEntity deletedPatient = patientDao.findOne(patientId);
    // assertThat(deletedPatient).isNull();

    // // Check that the visits related to the patient are deleted (cascading should
    // // delete them)
    // Long visitCountAfter = visitDao.count();
    // assertThat(visitCountAfter).isEqualTo(visitCountBefore - 1); // Assuming
    // there was 1 visit for the patient

    // Check that the doctors are not deleted (doctors are not directly related to
    // patients)
    // Assuming that the doctor associated with the visit still exists
    // Adjust the doctor ID according to your test data
    // VisitEntity visit = visitDao.findByPatient(patient);
    // assertThat(visit).isNotNull(); // Ensure the visit exists for the patient
    // Long doctorId = visit.getDoctor().getId(); // Get the doctor from the visit
    // assertThat(patientDao.findOne(doctorId)).isNotNull(); // Doctor should still
    // exist in the database
    // }

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
    }
}
