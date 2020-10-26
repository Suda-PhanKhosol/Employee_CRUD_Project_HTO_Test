package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="ADMIN")
public class Admin {
	@Id
	@SequenceGenerator(name="admin_seq",sequenceName="admin_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="admin_seq")
	@Column(name="ADMIN_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String name;


	@OneToMany
	private Collection<Employee> admin_employee;
}
