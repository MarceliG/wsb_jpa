package com.jpacourse.persistance.dao;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorDaoTest {

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void testFindByPatientName() {
        // given
        final String pPatientName = "Smith";

        // when
        final List<DoctorEntity> doctors = doctorDao.findByPatientName(pPatientName);

        // then
        assertThat(doctors).isNotNull();
        assertThat(doctors).isNotEmpty();
        final Collection<VisitEntity> visits = doctors.get(0).getVisits();
        assertThat(visits).isNotNull();
        assertThat(visits).isNotEmpty();
    }

    @Transactional
    @Test
    public void testFindVisitByPatientId() {
        // given
        final Long patientId = 1L;  
    
        // when
        final List<DoctorEntity> doctors = doctorDao.findVisitByPatientId(patientId);
    
        // then
        assertThat(doctors).isNotNull();
        assertThat(doctors).isNotEmpty();
        
        final Collection<VisitEntity> visits = doctors.get(0).getVisits();
        assertThat(visits).isNotNull();
        assertThat(visits).isNotEmpty();
    }

    @Transactional
    @Test
    public void testFindPatientsWithMoreThanXVisits() {
        // given
        final Long visitThreshold = 2L;  
    
        // when
        final List<DoctorEntity> doctors = doctorDao.findWithMoreThanXVisits(visitThreshold);
    
        // then
        assertThat(doctors).isNotNull();
    }

    @Transactional
    @Test
    public void testFindByPatientPhoneNumber() {
        // given
        final String fragmentPhoneNumber = "66";  
    
        // when
        final List<DoctorEntity> doctors = doctorDao.findByPatientPhoneNumber(fragmentPhoneNumber);
    
        // then
        assertThat(doctors).isNotNull();
    }


    @Transactional
    @Test
    public void testOptimisticLocking() {
        // given
        final Long doctorId = 1L;
        final String newFirstName = "John Updated";

        // when
        doctorDao.updateDoctor(doctorId, newFirstName);

        // then
        final List<DoctorEntity> updatedDoctor = doctorDao.findVisitByPatientId(doctorId);
        assertThat(updatedDoctor.get(0).getFirstName()).isEqualTo(newFirstName);
        assertThat(updatedDoctor.get(0).getVersion()).isGreaterThan(0);
    }
}
