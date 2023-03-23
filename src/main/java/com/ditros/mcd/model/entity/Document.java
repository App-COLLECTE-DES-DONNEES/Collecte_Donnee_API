package com.ditros.mcd.model.entity;

import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public abstract class Document extends JournalData {
    protected String identification;
    protected String recipientFirstname;
    protected String recipientLastname;
    protected LocalDate issueDate;
    protected LocalDate expireAt;
    protected String image;
    @ManyToOne
    protected DocumentType type;
}
