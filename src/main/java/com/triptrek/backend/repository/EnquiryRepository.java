package com.triptrek.backend.repository;

import com.triptrek.backend.entity.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
    List<Enquiry> findByEmail(String email);
    List<Enquiry> findByStatus(String status);
    List<Enquiry> findByItemType(String itemType);
    long countByStatus(String status);
}