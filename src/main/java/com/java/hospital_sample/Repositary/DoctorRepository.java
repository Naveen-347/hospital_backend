package com.java.hospital_sample.Repositary;
 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.hospital_sample.user.Doctor;
 
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
 
    // Custom method for login check
    Doctor findByDocidAndPassword(Long docid, String password);
    
    
}
 