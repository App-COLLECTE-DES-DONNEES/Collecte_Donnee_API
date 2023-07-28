package com.ditros.mcd.model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.ditros.mcd.model.entity.UserMonitoring;
import com.ditros.mcd.model.entity.inherited.JournalData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccidentActivity extends JournalData{


   private String Activityname;
} 
