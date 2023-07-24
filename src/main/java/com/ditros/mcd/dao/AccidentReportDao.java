package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AccidentReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccidentReportDao extends JpaRepository<AccidentReport, Long> {
}
