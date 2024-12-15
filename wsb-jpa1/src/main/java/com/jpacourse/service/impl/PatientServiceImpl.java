package com.jpacourse.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.PatientService;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public PatientTO savePatient(PatientTO patientTO) {
        PatientEntity patientEntity = PatientMapper.toEntity(patientTO);
        PatientEntity savedEntity = patientDao.save(patientEntity);
        return PatientMapper.toTO(savedEntity);
    }

    @Override
    public PatientTO getPatient(Long id) {
        PatientEntity patientEntity = patientDao.getOne(id);
        return PatientMapper.toTO(patientEntity);
    }

    @Override
    public List<PatientTO> getAllPatients() {
        List<PatientEntity> entities = patientDao.findAll();
        return entities.stream()
                .map(PatientMapper::toTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePatient(Long id) {
        patientDao.delete(id);
    }
}
