package com.cpe.backend.repository;

import com.cpe.backend.entity.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface AdminRepository extends JpaRepository<Admin, Long> {
	Admin findById(long id);
}