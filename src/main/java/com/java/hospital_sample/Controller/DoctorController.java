
package com.java.hospital_sample.Controller;

import com.java.hospital_sample.Repositary.DoctorRepository;
import com.java.hospital_sample.user.Doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    // Get all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get doctor by ID
    @GetMapping("/{docid}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long docid) {
        return doctorRepository.findById(docid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create doctor (normal save)
    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Update doctor
    @PutMapping("/{docid}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long docid, @RequestBody Doctor doctor) {
        return doctorRepository.findById(docid)
                .map(existing -> {
                    existing.setName(doctor.getName());
                    existing.setDesignation(doctor.getDesignation());
                    existing.setPassword(doctor.getPassword());
                    return ResponseEntity.ok(doctorRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete doctor
    @DeleteMapping("/{docid}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long docid) {
        doctorRepository.deleteById(docid);
        return ResponseEntity.noContent().build();
    }

    // -------- SIGNUP (plain text password) --------
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Doctor doctor) {
        // You could check if doctor with same name exists here
        Doctor saved = doctorRepository.save(doctor);
        return ResponseEntity.ok(saved);
    }

    // -------- LOGIN (plain text password) --------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Doctor doctor) {
        Optional<Doctor> found = doctorRepository.findAll().stream()
                .filter(d -> d.getName().equals(doctor.getName()) &&
                             d.getPassword().equals(doctor.getPassword()))
                .findFirst();
        if (found.isPresent()) {
            return ResponseEntity.ok(found.get());
        } else {
            return ResponseEntity.status(401).body("Invalid name or password");
        }
    }
}