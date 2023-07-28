package com.ditros.mcd.model.entity;
import com.ditros.mcd.model.dto.AccidentActivity;
import com.ditros.mcd.model.entity.inherited.JournalData;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserMonitoring extends JournalData {

private Long userId;

private Long ActivityId;
private Long AccidentId;

}
