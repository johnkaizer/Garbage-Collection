package com.project.garbagecollectionsys.complaint;

import com.project.garbagecollectionsys.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByStatus(Status status);
    List<Complaint> findByUserId(Long userId);
    List<Complaint> findByRouteId(Long routeId);
}

