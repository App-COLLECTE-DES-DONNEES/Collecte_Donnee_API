package com.ditros.mcd.model.entity;

import com.ditros.mcd.enumeration.OrganizationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@SQLDelete(sql="UPDATE organization SET deleted=true where id=?")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String street;
    private String postal;
    private String lang;
    private String currency;
    private String tel1;
    private String tel2;
    private String managerName;
    private String managerPhone;
    private double latitude;
    private double longitude;

    @ManyToOne
    private Organization parent;

    @ManyToOne
    private City city;

    @ManyToOne
    private Country country;

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
    @OneToMany(mappedBy = "parent")
    private List<Organization> children;

    public List<Organization> getAllChildren(){
        return getAllChildren(this);
    }

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'POLICESTATION'")
    private OrganizationType type;

    private List<Organization> getAllChildren(Organization parent){
        List<Organization> allChildren = new ArrayList<>();
        for(Organization child : parent.getChildren()){
            allChildren.add(child);
            allChildren.addAll(getAllChildren(child));
        }
        return allChildren;
    }
    public List<Long> getMyIdAndAllChildrenId(Organization parent){
        List<Long> allChildren = new ArrayList<>();
        allChildren.add(parent.id);
        for(Organization child : parent.getChildren()){
            allChildren.addAll(getMyIdAndAllChildrenId(child));
        }
        System.out.println("all organisation ids = " + allChildren.toString());
        return allChildren;
    }

    public void disable(){
        this.setActiveStatus(false);
    }
}
