package com.jpacourse.persistance.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Specialization;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private DoctorDao doctorDao;

    @Test
    public void testShouldFindPatientById() {
        // given
        final AddressEntity patientAddress = createAdress(
            "line1", 
            "line2", 
            "City1", 
            "66-666"
        );

        PatientEntity patient = new PatientEntity();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setDateOfBirth(LocalDate.of(1999, 10, 4));
        patient.setPatientNumber("ABCDE");
        patient.setIsActive(true);
        patient.setTelephoneNumber("666777666");
        patient.setAddress(patientAddress);

        final PatientEntity savedPatient = patientDao.save(patient);
        assertThat(savedPatient).isNotNull();
        assertThat(savedPatient.getId()).isNotNull();

        // when
        final PatientEntity foundPatient = patientDao.findOne(savedPatient.getId());

        // then
        assertThat(foundPatient).isNotNull();
        assertThat(foundPatient.getFirstName()).isEqualTo("John");
    }

    @Test
    public void testShouldSavePatient() {
        // given
        final AddressEntity patientAddress = createAdress(
            "line3", 
            "line4", 
            "City2", 
            "63-666"
        );

        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Alice");
        patient.setLastName("Smith");
        patient.setDateOfBirth(LocalDate.of(1999, 10, 4));
        patient.setPatientNumber("BCDEF");
        patient.setIsActive(false);
        patient.setTelephoneNumber("777666777");
        patient.setAddress(patientAddress);
        long entitiesNumBefore = patientDao.count();

        // when
        final PatientEntity saved = patientDao.save(patient);

        // then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(patientDao.count()).isEqualTo(entitiesNumBefore + 1);
    }

    @Test
    public void testShouldAddVisitToPatient() {
        // given
        final AddressEntity doctorPatientAddress = createAdress(
            "line5", 
            "line6", 
            "City3", 
            "64-666"
        );

        PatientEntity patient = new PatientEntity();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setDateOfBirth(LocalDate.of(1999, 10, 4));
        patient.setPatientNumber("CDEFG");
        patient.setIsActive(true);
        patient.setTelephoneNumber("321123123");
        patient.setAddress(doctorPatientAddress);
        final PatientEntity savedPatient = patientDao.save(patient);

        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("Dr. Adam");
        doctor.setLastName("Brown");
        doctor.setTelephoneNumber("333222111");
        doctor.setDoctorNumber("1123");
        doctor.setSpecialization(Specialization.DERMATOLOGIST);
        doctor.setAddress(doctorPatientAddress);
        final DoctorEntity savedDoctor = doctorDao.save(doctor);

        // when
        VisitEntity savedVisit = patientDao.addVisitToPatient(savedPatient.getId(), savedDoctor.getId(), LocalDateTime.now(), "Routine checkup");

        // then
        assertThat(savedVisit).isNotNull();
        assertThat(savedVisit.getId()).isNotNull();
        assertThat(savedPatient.getVisits()).contains(savedVisit);
    }

    @Test
    public void testShouldSaveAndRemovePatient() {
        // given
        final AddressEntity patientAddress = createAdress(
            "line7", 
            "line8", 
            "City4", 
            "65-666"
        );

        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Emma");
        patient.setLastName("Stone");
        patient.setDateOfBirth(LocalDate.of(1999, 10, 4));
        patient.setPatientNumber("DEFGH");
        patient.setIsActive(true);
        patient.setTelephoneNumber("123456789");
        patient.setAddress(patientAddress);
        
        // when
        final PatientEntity savedPatient = patientDao.save(patient);
        assertThat(savedPatient.getId()).isNotNull();
        final PatientEntity newSaved = patientDao.findOne(savedPatient.getId());
        assertThat(newSaved).isNotNull();

        patientDao.delete(newSaved.getId());

        // then
        final PatientEntity removed = patientDao.findOne(newSaved.getId());
        assertThat(removed).isNull();
    }

    /* Help */
    private AddressEntity createAdress(String line1, String line2, String city, String postalCode)
    {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressLine1(line1);
        addressEntity.setAddressLine2(line2);
        addressEntity.setCity(city);
        addressEntity.setPostalCode(postalCode);

        return addressDao.save(addressEntity);
    }
}
