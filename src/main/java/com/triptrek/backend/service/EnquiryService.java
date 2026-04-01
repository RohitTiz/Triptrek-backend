package com.triptrek.backend.service;

import com.triptrek.backend.dto.EnquiryDTO;
import com.triptrek.backend.entity.Enquiry;
import com.triptrek.backend.repository.EnquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EnquiryService {

    private final EnquiryRepository enquiryRepository;

    @Transactional
    public Enquiry createEnquiry(EnquiryDTO enquiryDTO) {
        Enquiry enquiry = new Enquiry();
        enquiry.setName(enquiryDTO.getName());
        enquiry.setEmail(enquiryDTO.getEmail());
        enquiry.setPhone(enquiryDTO.getPhone());
        enquiry.setAddress(enquiryDTO.getAddress());
        enquiry.setMessage(enquiryDTO.getMessage());
        enquiry.setItemName(enquiryDTO.getItemName());
        enquiry.setItemType(enquiryDTO.getItemType());
        enquiry.setStatus("PENDING");

        return enquiryRepository.save(enquiry);
    }

    public List<Enquiry> getAllEnquiries() {
        return enquiryRepository.findAll();
    }

    public Enquiry getEnquiryById(Long id) {
        return enquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enquiry not found with id: " + id));
    }

    public List<Enquiry> getEnquiriesByEmail(String email) {
        return enquiryRepository.findByEmail(email);
    }

    @Transactional
    public Enquiry updateEnquiryStatus(Long id, String status) {
        Enquiry enquiry = getEnquiryById(id);
        enquiry.setStatus(status);
        return enquiryRepository.save(enquiry);
    }

    @Transactional
    public void deleteEnquiry(Long id) {
        enquiryRepository.deleteById(id);
    }

    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalEnquiries", enquiryRepository.count());
        stats.put("pendingEnquiries", enquiryRepository.countByStatus("PENDING"));
        stats.put("contactedEnquiries", enquiryRepository.countByStatus("CONTACTED"));
        stats.put("resolvedEnquiries", enquiryRepository.countByStatus("RESOLVED"));
        return stats;
    }
}