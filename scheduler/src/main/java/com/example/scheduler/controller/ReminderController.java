package com.example.scheduler.controller;

import com.example.scheduler.entity.Reminder;
import com.example.scheduler.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {
    private final ReminderService service;

    @PostMapping
    public Reminder createReminder(@RequestBody Reminder reminder) {
        return service.save(reminder);
    }


}
