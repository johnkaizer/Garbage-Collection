package com.project.garbagecollectionsys.drivers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Drivers, Long> {
    List<Drivers> findByNameContainingIgnoreCase(String name); // Search by name (case insensitive)

    Drivers findByName(String name);
}

