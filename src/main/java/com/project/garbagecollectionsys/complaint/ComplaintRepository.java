package com.project.garbagecollectionsys.complaint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByPhone(String phone);
    List<Complaint> findByStatus(String status);
    List<Complaint> findByPhoneAndStatus(String phone, String status);

    List<Complaint> findByUserId(String userId);
}
