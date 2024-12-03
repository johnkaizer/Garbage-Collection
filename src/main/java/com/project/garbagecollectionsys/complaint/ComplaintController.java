package com.project.garbagecollectionsys.complaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    // Add a new complaint
    @PostMapping
    public Complaint addComplaint(@RequestBody Complaint complaint) {
        return complaintService.addComplaint(complaint);
    }

    // Edit an existing complaint
    @PutMapping("/{id}")
    public Complaint editComplaint(@PathVariable Long id, @RequestBody Complaint complaint) {
        return complaintService.editComplaint(id, complaint);
    }

    // Delete a complaint
    @DeleteMapping("/{id}")
    public void deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
    }

    // Get complaints by phone number
    @GetMapping("/filter/phone")
    public List<Complaint> getComplaintsByPhone(@RequestParam String phone) {
        return complaintService.getComplaintsByPhone(phone);
    }

    // Get complaints by status
    @GetMapping("/filter/status")
    public List<Complaint> getComplaintsByStatus(@RequestParam String status) {
        return complaintService.getComplaintsByStatus(status);
    }

    // Get complaints by phone number and status
    @GetMapping("/filter")
    public List<Complaint> getComplaintsByPhoneAndStatus(@RequestParam String phone, @RequestParam String status) {
        return complaintService.getComplaintsByPhoneAndStatus(phone, status);
    }
    @GetMapping
    public ResponseEntity<List<Complaint>> getComplaints(@RequestParam String userId) {
        List<Complaint> complaints = complaintService.getComplaintsByUserId(userId);
        return ResponseEntity.ok(complaints);
    }
    // Get all complaints
    @GetMapping("/all")
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }
}

