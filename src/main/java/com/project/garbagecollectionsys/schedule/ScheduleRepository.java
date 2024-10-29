package com.project.garbagecollectionsys.schedule;

import com.project.garbagecollectionsys.enums.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByDayOfWeek(DayOfWeek dayOfWeek);
}

