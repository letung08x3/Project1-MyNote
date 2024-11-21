package com.example.project1mynote.service;

import com.example.project1mynote.dto.NoteDto;
import com.example.project1mynote.dtoRequest.NoteCreateRequest;
import com.example.project1mynote.dtoRequest.NoteUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InNotesService{
    List<NoteDto> getAllNotes ();
    NoteDto createNote(NoteCreateRequest noteCreateRequest);
    NoteDto getNoteById(int id);
    String deleteNoteById(int id);
    NoteDto updateNote(int id, NoteUpdateRequest noteUpdateRequest);
}
