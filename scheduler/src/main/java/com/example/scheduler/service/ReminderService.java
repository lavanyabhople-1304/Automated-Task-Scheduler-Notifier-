package com.example.scheduler.service;
import com.example.scheduler.entity.Reminder;
import com.example.scheduler.repository.ReminderRepository;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReminderService {

    private final ReminderRepository repository;
    private final JavaMailSender mailSender;

    public Reminder save(Reminder reminder){
        return repository.save(reminder);
    }

    @Scheduled(cron = "0 * * * * *")
    public void processReminder(){
        List<Reminder> reminders=   repository.findBySentFalseAndScheduledTimeBefore(LocalDateTime.now());

        for (Reminder reminder:reminders){
            try {
                sentEmail(reminder);
                reminder.setSent(true);
                repository.save(reminder);
                log.info("Email sent to {}",reminder.getEmail());
            }catch (Exception e){
                log.error("Failed to sent Email to {}", reminder.getEmail());
            }
        }
    }
private void sentEmail (Reminder reminder){
        SimpleMailMessage message =new SimpleMailMessage();
        message.setTo(reminder.getEmail());
        message.setSubject("Reminder Notificatiion");
        message.setText(reminder.getMessage());
        mailSender.send(message);
}

}
