package com.triptrek.backend.controller;

import com.triptrek.backend.dto.EnquiryDTO;
import com.triptrek.backend.entity.Enquiry;
import com.triptrek.backend.service.EnquiryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enquiries")

@RequiredArgsConstructor
public class EnquiryController {

    private final EnquiryService enquiryService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createEnquiry(@Valid @RequestBody EnquiryDTO enquiryDTO) {
        Enquiry saved = enquiryService.createEnquiry(enquiryDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Enquiry submitted successfully!");
        response.put("data", saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Enquiry>> getAllEnquiries() {
        return ResponseEntity.ok(enquiryService.getAllEnquiries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enquiry> getEnquiryById(@PathVariable Long id) {
        return ResponseEntity.ok(enquiryService.getEnquiryById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<Enquiry>> getEnquiriesByEmail(@PathVariable String email) {
        return ResponseEntity.ok(enquiryService.getEnquiriesByEmail(email));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Enquiry> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(enquiryService.updateEnquiryStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEnquiry(@PathVariable Long id) {
        enquiryService.deleteEnquiry(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Enquiry deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getStatistics() {
        return ResponseEntity.ok(enquiryService.getStatistics());
    }
}