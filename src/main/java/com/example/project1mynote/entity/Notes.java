package com.example.project1mynote.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Getter
@Setter
@ToString
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "title", nullable = false, length = 100, unique = true)
    private String title;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @Column(name = "reminder_enabled", nullable = false)
    private boolean reminderEnabled = false;

    @Column(name = "reminder_date")
    private LocalDateTime reminderDate;

    @Column(name = "is_pinned", nullable = false)
    private boolean isPinned = false;

    @Column(name = "repeat_interval")
    private RepeatInterval repeatInterval;

    @Column(name = "create_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @PrePersist
    @PreUpdate
    public void checkReminderSettings() {
        if (!reminderEnabled) {
            reminderDate = null;
            repeatInterval = null;
        }
    }


}
