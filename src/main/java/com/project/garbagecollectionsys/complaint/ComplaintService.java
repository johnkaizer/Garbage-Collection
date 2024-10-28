package com.project.garbagecollectionsys.complaint;

import com.project.garbagecollectionsys.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Optional<Complaint> getComplaintById(Long id) {
        return complaintRepository.findById(id);
    }

    public Complaint createComplaint(Complaint complaint) {
        complaint.setDate(LocalDateTime.now()); // Set the date to now when created
        complaint.setStatus(Status.PENDING); // Default status to PENDING
        return complaintRepository.save(complaint);
    }

    public Complaint updateComplaint(Long id, Complaint updatedComplaint) {
        return complaintRepository.findById(id).map(complaint -> {
            complaint.setDescription(updatedComplaint.getDescription());
            complaint.setStatus(updatedComplaint.getStatus());
            complaint.setRoute(updatedComplaint.getRoute());
            return complaintRepository.save(complaint);
        }).orElseThrow(() -> new RuntimeException("Complaint not found"));
    }

    public void deleteComplaint(Long id) {
        complaintRepository.deleteById(id);
    }

    public List<Complaint> getComplaintsByStatus(Status status) {
        return complaintRepository.findByStatus(status);
    }

    public List<Complaint> getComplaintsByUserId(Long userId) {
        return complaintRepository.findByUserId(userId);
    }

    public List<Complaint> getComplaintsByRouteId(Long routeId) {
        return complaintRepository.findByRouteId(routeId);
    }
}

