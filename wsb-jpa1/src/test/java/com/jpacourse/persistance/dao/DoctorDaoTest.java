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

import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorDaoTest {

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private AddressDao addressDao;

    @Transactional
    @Test
    public void testFindByPatientName() {
        // given
        final String pPatientName = "Smith";

        // when
        final List<DoctorEntity> docs = doctorDao.findByPatientName(pPatientName);

        // then
        assertThat(docs).isNotNull();
        assertThat(docs).isNotEmpty();
        final Collection<VisitEntity> visits = docs.get(0).getVisits();
        assertThat(visits).isNotNull();
        assertThat(visits).isNotEmpty();
    }
}
