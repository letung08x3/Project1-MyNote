package com.example.project1mynote.dtoRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class NoteUpdateRequest {
    private int categoryId;
    private String title;
    private String content;
    private boolean isPinned;

    // thuộc tính nhắc hẹn
    private boolean reminderEnabled; // true nếu người dùng muốn nhắc hẹn
    private LocalDateTime reminderDate;
    private String repeatInterval;
}