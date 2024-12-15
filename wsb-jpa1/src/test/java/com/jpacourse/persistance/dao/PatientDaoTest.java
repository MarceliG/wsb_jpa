package com.jpacourse.persistance.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void testShouldFindPatientById() {
        // given
        PatientEntity patient = new PatientEntity();
        patient.setId(1L);
        patient.setFirstName("John");
        patient.setLastName("Doe");

        // when
        PatientEntity foundPatient = patientDao.findOne(1L);

        // then
        assertThat(foundPatient).isNotNull();
        assertThat(foundPatient.getFirstName()).isEqualTo("John");
    }

    @Test
    public void testShouldSavePatient() {
        // given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Alice");
        patient.setLastName("Smith");
        long entitiesNumBefore = patientDao.count();

        // when
        final PatientEntity saved = patientDao.save(patient);

        // then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(patientDao.count()).isEqualTo(entitiesNumBefore + 1);
    }

    @Transactional
    @Test
    public void testShouldAddVisitToPatient() {
        // given
        PatientEntity patient = new PatientEntity();
        patient.setId(1L);
        patient.setFirstName("John");
        patient.setLastName("Doe");

        DoctorEntity doctor = new DoctorEntity();
        doctor.setId(1L);
        doctor.setFirstName("Dr. Adam");
        doctor.setLastName("Brown");

        VisitEntity visit = new VisitEntity();
        visit.setTime(LocalDateTime.now());
        visit.setDescription("Routine checkup");
        visit.setDoctor(doctor);
        visit.setPatient(patient);

        // when
        patientDao.addVisitToPatient(1L, 1L, visit.getTime(), visit.getDescription());

        // then
        assertThat(patient.getVisits()).contains(visit);
    }

    @Transactional
    @Test
    public void testShouldSaveAndRemovePatient() {
        // given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Emma");
        patient.setLastName("Stone");

        // when
        final PatientEntity saved = patientDao.save(patient);
        assertThat(saved.getId()).isNotNull();
        final PatientEntity newSaved = patientDao.findOne(saved.getId());
        assertThat(newSaved).isNotNull();

        patientDao.delete(saved.getId());

        // then
        final PatientEntity removed = patientDao.findOne(saved.getId());
        assertThat(removed).isNull();
    }
}
