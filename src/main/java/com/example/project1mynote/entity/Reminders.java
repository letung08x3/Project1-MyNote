package com.example.project1mynote.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Reminders")
@ToString
public class Reminders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "reminder_date")
    private LocalDateTime reminderDate;

    @Column(name = "repeat_interval")
    private String repeatInterval;

    @Column(name = "status", nullable = false)
    private boolean status;

}
