package com.example.scheduler.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
@Data
@Entity

public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String email;
    private String message;
    private LocalDateTime scheduledTime;
    private  boolean sent;


}
