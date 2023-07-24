package com.ditros.mcd.model.entity.inherited;

import com.ditros.mcd.model.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "deleted=false")
@Data
@AllArgsConstructor @NoArgsConstructor
public class JournalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created_date",updatable=false)
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column(columnDefinition = "bit default 1")
    private boolean activeStatus= true;

    @Column(name="created_by")
    @CreatedBy
    private String createdBy;

    @Column(name="modified_by")
    @LastModifiedBy
    private String modifiedBy;



    private boolean deleted = Boolean.FALSE;
    private String lang = "fr";

    public void disable(){
        this.setActiveStatus(false);
    }
}
