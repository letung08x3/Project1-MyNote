package com.example.project1mynote.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class NoteDto {

    private String category;
    private String title;
    private String content;
    private boolean isPinned;

    private boolean reminderEnabled;
    private LocalDateTime reminderDate;
    private String repeatInterval;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;


    public NoteDto() {

    }

    public NoteDto(String category, String content, String title, boolean pinned, LocalDateTime createDate, LocalDateTime updateDate, LocalDateTime reminderDate, boolean reminderEnabled, String repeatInterval) {
    }
}
