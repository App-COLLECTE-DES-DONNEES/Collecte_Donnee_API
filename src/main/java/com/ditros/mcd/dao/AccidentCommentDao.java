package com.ditros.mcd.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ditros.mcd.model.entity.AccidentComment;

public interface AccidentCommentDao extends JpaRepository<AccidentComment, Long>{
    

}
