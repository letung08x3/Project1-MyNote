package com.example.project1mynote.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Notes {
    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
//                ", user=" + user +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", reminderEnabled=" + reminderEnabled +
                ", reminderDate=" + reminderDate +
                ", isPinned=" + isPinned +
                ", repeatInterval=" + (repeatInterval != null ? repeatInterval.toString() : "null") +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
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
