package com.example.project1mynote.controller;

import com.example.project1mynote.dto.NoteDto;
import com.example.project1mynote.dtoRequest.NoteCreateRequest;
import com.example.project1mynote.dtoRequest.NoteUpdateRequest;
import com.example.project1mynote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public List<NoteDto> getNoteCurrentUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("user đang đăng nhập là:" + username);
        return  noteService.getNoteByCurrentUser(username);
    }

    @PostMapping("/create")
    public NoteDto createNewNote(@RequestBody NoteCreateRequest noteCreateRequest){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return noteService.createNote(noteCreateRequest, username);
    }
    @PutMapping("/{id}/update")
    public NoteDto updateNote(@PathVariable int id, @RequestBody NoteUpdateRequest noteUpdateRequest ){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        return noteService.updateNote(id, noteUpdateRequest);
    }

    @GetMapping("/{id}")
    public NoteDto getNoteById(@PathVariable int id){
        return noteService.getNoteById(id);
    }

    @DeleteMapping("/{id}/delete")
    ResponseEntity<String> deleteNoteById(@PathVariable int id){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        noteService.deleteNoteById(id, userName);
        return ResponseEntity.ok("Note delete successfully");
    }
}
