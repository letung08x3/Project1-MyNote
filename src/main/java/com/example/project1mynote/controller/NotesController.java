package com.example.project1mynote.controller;

import com.example.project1mynote.dto.NoteDto;
import com.example.project1mynote.dtoRequest.NoteCreateRequest;
import com.example.project1mynote.dtoRequest.NoteUpdateRequest;
import com.example.project1mynote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public List<NoteDto> getAllNotes(){
        return  noteService.getAllNotes();
    }

    @PostMapping("/create")
    public NoteDto createNewNote(@RequestBody NoteCreateRequest noteCreateRequest){
        return noteService.createNote(noteCreateRequest);
    }
    @PutMapping("/{id}/update")
    public NoteDto updateNote(@PathVariable int id, @RequestBody NoteUpdateRequest noteUpdateRequest ){
        return noteService.updateNote(id, noteUpdateRequest);
    }

    @GetMapping("/{id}")
    public NoteDto getNoteById(@PathVariable int id){
        return noteService.getNoteById(id);
    }

    @DeleteMapping("/{id}/delete")
    ResponseEntity<String> deleteNoteById(@PathVariable int id){
        noteService.deleteNoteById(id);
        return ResponseEntity.ok("Note delete successfully");
    }
}
