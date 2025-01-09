package com.jpacourse.persistance.dao;

import java.time.LocalDateTime;
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
import com.jpacourse.persistence.enums.TreatmentType;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorDaoTest {

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void testFindByDesc() {
        // given
        final String pDesc = "Routine";

        // when
        final List<DoctorEntity> docs = doctorDao.findByVisitDescription(pDesc);

        // then
        assertThat(docs).isNotNull();
        final Collection<VisitEntity> visits = docs.get(0).getVisits();
        assertThat(visits).isNotNull();
    }

    @Transactional
    @Test
    public void testFindById() {
        // given
        long minNumOfVisits = 4;

        // when
        final List<DoctorEntity> docs = doctorDao.findByMinNumberOfVisits(minNumOfVisits);

        // then
        assertThat(docs).isNotNull();
    }

    @Transactional
    @Test
    public void shouldReturnDocsHavingVisitsToGivenDateRange() {
        // given
        String dateFromAsString = "2024-06-06T16:00:00";
        String dateToAsString = "2024-06-11T06:00:00";

        LocalDateTime dateFrom = LocalDateTime.parse(dateFromAsString);
        LocalDateTime dateTo = LocalDateTime.parse(dateToAsString);

        // when
        final List<DoctorEntity> docs = doctorDao.findByVisitDateRange(dateFrom, dateTo);

        // then
        assertThat(docs).isNotNull();
    }

    @Transactional
    @Test
    public void shouldReturnPatienByPatientName() {
        // given
        String patientName = "Tomas";

        // when
        final List<DoctorEntity> docs = doctorDao.findByPatient(patientName);

        // then
        assertThat(docs).isNotNull();
    }
    
    @Transactional
    @Test
    public void shouldReturnTreatmentTypeByPatientName() {
        // given

        // when
        final List<DoctorEntity> docs = doctorDao.findByTreatmentType(TreatmentType.RTG);

        // then
        assertThat(docs).isNotNull();
    }
}
