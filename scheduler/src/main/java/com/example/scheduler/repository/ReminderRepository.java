package com.example.scheduler.repository;

import com.example.scheduler.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReminderRepository  extends JpaRepository<Reminder,Long>{
    List<Reminder>findBySentFalseAndScheduledTimeBefore(LocalDateTime time);
}
