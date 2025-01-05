package com.jpacourse.persistence.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.entity.DoctorEntity;

@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao {


    @Override
    public List<DoctorEntity> findByPatientName(String pPatientName) {
        return entityManager.createQuery("select doc from DoctorEntity doc " +
            " join doc.visits visit " +
            " join visit.patient patient " +
            " where patient.lastName =: name", DoctorEntity.class)
            .setParameter("name", pPatientName)
            .getResultList();
    }
}
