package com.cpe.backend;

import com.cpe.backend.entity.Admin;
import com.cpe.backend.entity.Gender;
import com.cpe.backend.repository.AdminRepository;
import com.cpe.backend.repository.GenderRepository;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	ApplicationRunner init(AdminRepository adminRepository, GenderRepository genderRepository) {
		return args -> {

		/*	
			Stream.of("Takoonkan", "Sitthichai", "Somchai", "Tanapon").forEach(name -> {
				Admin admin = new Admin(); // สร้าง Object admin
				admin.setName(name); // set ชื่อ (name) ให้ Object ชื่อ admin
				adminRepository.save(admin); // บันทึก Objcet ชื่อ admin
			});

			Stream.of("Male", "Female").forEach(name -> {
				Gender gender = new Gender(); // สร้าง Object gender
				gender.setName(name); // set ชื่อ (name) ให้ Object ชื่อ gender
				genderRepository.save(gender); // บันทึก Objcet ชื่อ gender
			});
			*/

		};

	}

}
