package com.project.garbagecollectionsys.complaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    // Add a new complaint
    public Complaint addComplaint(Complaint complaint) {
        complaint.setStatus("Pending");  // Default status is PENDING
        complaint.setDate(LocalDateTime.now());  // Set the current date and time
        return complaintRepository.save(complaint);
    }

    // Edit an existing complaint
    public Complaint editComplaint(Long id, Complaint updatedComplaint) {
        Optional<Complaint> existingComplaint = complaintRepository.findById(id);
        if (existingComplaint.isPresent()) {
            Complaint complaint = existingComplaint.get();
            complaint.setPhone(updatedComplaint.getPhone());
            complaint.setRoute(updatedComplaint.getRoute());
            complaint.setDescription(updatedComplaint.getDescription());
            complaint.setStatus(updatedComplaint.getStatus());
            return complaintRepository.save(complaint);
        }
        return null; // Complaint not found
    }

    // Delete a complaint
    public void deleteComplaint(Long id) {
        complaintRepository.deleteById(id);
    }

    // Filter complaints by phone number
    public List<Complaint> getComplaintsByPhone(String phone) {
        return complaintRepository.findByPhone(phone);
    }

    // Filter complaints by status
    public List<Complaint> getComplaintsByStatus(String status) {
        return complaintRepository.findByStatus(status);
    }

    // Filter complaints by phone and status
    public List<Complaint> getComplaintsByPhoneAndStatus(String phone, String status) {
        return complaintRepository.findByPhoneAndStatus(phone, status);
    }
}
