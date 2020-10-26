package com.cpe.backend.controller;

import com.cpe.backend.entity.Admin;
import com.cpe.backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class AdminController {

    @Autowired
    private final AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/admin")
    public Collection<Admin> Admins() {
        return adminRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/admin/{id}")
    public Optional<Admin> Admins(@PathVariable Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin;
    }

}