package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDao  extends JpaRepository<Department, Long> {
}
