package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.CareExamination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareExaminationDao extends JpaRepository<CareExamination, Long> {
}