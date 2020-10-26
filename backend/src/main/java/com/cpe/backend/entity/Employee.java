package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.cpe.backend.entity.Gender;

@Data
@Entity
@NoArgsConstructor
@Table(name="EMPLOYEE")
public class Employee {

    @Id
    @SequenceGenerator(name="employee_seq",sequenceName="employee_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_seq")
    @Column(name = "EMPLOYEE_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private  String name;
    private  String phone;
    private  String address;
    private boolean status;

    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "GENDER_ID", insertable = true)
    private Gender createdBy;//gender

    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Admin.class)
    @JoinColumn(name = "ADMIN_ID", insertable = true)
    private Admin rentAdmin;//admin
    
    public void setAddress(String address) {
		this.address = address;
	}

}