package com.project.garbagecollectionsys.complaint;

import com.project.garbagecollectionsys.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    // Get all complaints
    @GetMapping
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    // Get complaint by ID
    @GetMapping("/{id}")
    public Optional<Complaint> getComplaintById(@PathVariable Long id) {
        return complaintService.getComplaintById(id);
    }

    // Create a new complaint
    @PostMapping
    public Complaint createComplaint(@RequestBody Complaint complaint) {
        return complaintService.createComplaint(complaint);
    }

    // Update a complaint by ID
    @PutMapping("/{id}")
    public Complaint updateComplaint(@PathVariable Long id, @RequestBody Complaint complaint) {
        return complaintService.updateComplaint(id, complaint);
    }

    // Delete a complaint by ID
    @DeleteMapping("/{id}")
    public void deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
    }

    // Get complaints by status
    @GetMapping("/status/{status}")
    public List<Complaint> getComplaintsByStatus(@PathVariable Status status) {
        return complaintService.getComplaintsByStatus(status);
    }

    // Get complaints by user ID
    @GetMapping("/user/{userId}")
    public List<Complaint> getComplaintsByUserId(@PathVariable Long userId) {
        return complaintService.getComplaintsByUserId(userId);
    }

    // Get complaints by route ID
    @GetMapping("/route/{routeId}")
    public List<Complaint> getComplaintsByRouteId(@PathVariable Long routeId) {
        return complaintService.getComplaintsByRouteId(routeId);
    }
}
