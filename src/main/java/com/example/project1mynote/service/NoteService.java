package com.example.project1mynote.service;

import com.example.project1mynote.dto.NoteDto;
import com.example.project1mynote.dtoRequest.NoteCreateRequest;
import com.example.project1mynote.dtoRequest.NoteUpdateRequest;
import com.example.project1mynote.entity.Category;
import com.example.project1mynote.entity.Notes;
import com.example.project1mynote.entity.RepeatInterval;
import com.example.project1mynote.entity.User;
import com.example.project1mynote.repository.categoryRepository;
import com.example.project1mynote.repository.noteRepository;
import com.example.project1mynote.repository.remindersRepository;
import com.example.project1mynote.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService implements InNotesService{
    @Autowired
    noteRepository noteRepository;

    @Autowired
    categoryRepository categoryRepository;

    @Autowired
    remindersRepository remindersRepository;

    @Autowired
    userRepository userRepository;


//    @Override
//    public List<NoteDto> getAllNotes() {
//        List<NoteDto> noteDtoList = new ArrayList<>();
//
//        List<Notes> notesList = noteRepository.findAll();
//        System.out.println("Danh sách notes là:" + notesList);
//
//        for (Notes notes: notesList
//             ) {
//            NoteDto noteDto = new NoteDto();
//            noteDto.setCategory(notes.getCategory().getCategoryName().toString());
//            noteDto.setTitle(notes.getTitle());
//            noteDto.setContent(notes.getContent());
//            noteDto.setPinned(notes.isPinned());
//            noteDto.setReminderEnabled(notes.isReminderEnabled());
//            noteDto.setReminderDate(notes.getReminderDate());
//            noteDto.setRepeatInterval(notes.getRepeatInterval().toString());
//            noteDto.setCreateDate(notes.getCreateDate());
//            noteDto.setUpdateDate(notes.getUpdateDate());
//
//            noteDtoList.add(noteDto);
//        }
//        return noteDtoList;
//    }

    @Override
    public List<NoteDto> getNoteByCurrentUser(String username) {
       List<Notes> notesList = noteRepository.findByUser_username(username);
        System.out.println("listNote theo user là:" + notesList);
        List<NoteDto> noteDtoList = new ArrayList<>();

        for (Notes notes: notesList
        ) {
            NoteDto noteDto = new NoteDto();
            noteDto.setCategory(notes.getCategory().getCategoryName().toString());
            noteDto.setTitle(notes.getTitle());
            noteDto.setContent(notes.getContent());
            noteDto.setPinned(notes.isPinned());
            noteDto.setReminderEnabled(notes.isReminderEnabled());
            noteDto.setReminderDate(notes.getReminderDate());
            noteDto.setRepeatInterval(notes.getRepeatInterval().toString());
            noteDto.setCreateDate(notes.getCreateDate());
            noteDto.setUpdateDate(notes.getUpdateDate());

            noteDtoList.add(noteDto);
        }
        return noteDtoList;
    }

    @Override
    public NoteDto createNote(NoteCreateRequest noteCreateRequest, String userName) {
        User user = userRepository.findByUsername(userName);

        Notes notes = new Notes();
        notes.setUser(user);

        Optional<Category> categoryOpt = categoryRepository.findById(noteCreateRequest.getCategoryId());
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            notes.setCategory(category);
        } else {
            throw new RuntimeException("Category not found with id: " + noteCreateRequest.getCategoryId());
        }
        notes.setTitle(noteCreateRequest.getTitle());
        notes.setContent(noteCreateRequest.getContent());
        notes.setPinned(noteCreateRequest.isPinned());

        // xứ lý nếu người dùng nhắc hẹn
        if (noteCreateRequest.isReminderEnabled()){
            notes.setReminderEnabled(true);
            notes.setReminderDate(noteCreateRequest.getReminderDate());

            String interval = noteCreateRequest.getRepeatInterval();
            if (interval != null){
                try {
                    RepeatInterval repeatInterval = RepeatInterval.valueOf(interval.toUpperCase());
                    notes.setRepeatInterval(repeatInterval);
                } catch (IllegalArgumentException e){
                    throw new IllegalArgumentException("Repeat interval không hợp lệ: " + interval);
                }
            }
        }
        noteRepository.save(notes);
        NoteDto noteDto = new NoteDto();

        noteDto.setCategory(notes.getCategory().getCategoryName().toString());
        noteDto.setTitle(notes.getTitle());
        noteDto.setContent(notes.getContent());
        noteDto.setPinned(notes.isPinned());
        noteDto.setReminderEnabled(notes.isReminderEnabled());
        noteDto.setReminderDate(notes.getReminderDate());
        noteDto.setRepeatInterval(notes.getRepeatInterval().toString());
        noteDto.setCreateDate(notes.getCreateDate());
        noteDto.setUpdateDate(notes.getUpdateDate());

        return noteDto;
    }



    @Override
    public NoteDto getNoteById(int id) {
        Notes notes = noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found")) ;
        NoteDto noteDto = new NoteDto();
        noteDto.setCategory(notes.getCategory().getCategoryName().toString());
        noteDto.setTitle(notes.getTitle());
        noteDto.setContent(notes.getContent());
        noteDto.setPinned(notes.isPinned());
        noteDto.setReminderEnabled(notes.isReminderEnabled());
        noteDto.setReminderDate(notes.getReminderDate());
        noteDto.setRepeatInterval(notes.getRepeatInterval().toString());
        noteDto.setCreateDate(notes.getCreateDate());
        noteDto.setUpdateDate(notes.getUpdateDate());

        return noteDto;
    }

    @Override
    public String deleteNoteById(int id, String username) {
        Notes note = noteRepository.findById(id).orElseThrow(()->new RuntimeException("Note not found"));
        if (!note.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Access denied!");
        }
        noteRepository.delete(note);
        return "Delete thành công";
    }

    @Override
    public NoteDto updateNote(int id, NoteUpdateRequest noteUpdateRequest) {
        Notes notes = noteRepository.findById(id).get();
        notes.setTitle(noteUpdateRequest.getTitle());
        notes.setContent(noteUpdateRequest.getContent());
        notes.setReminderDate(noteUpdateRequest.getReminderDate());

        Optional<Category> categoryOpt = categoryRepository.findById(noteUpdateRequest.getCategoryId());
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            notes.setCategory(category);
        } else {
            throw new RuntimeException("Category not found with id: " + noteUpdateRequest.getCategoryId());
        }

// xứ lý nếu người dùng nhắc hẹn
        if (noteUpdateRequest.isReminderEnabled()){
            notes.setReminderEnabled(true);
            notes.setReminderDate(noteUpdateRequest.getReminderDate());

            String interval = noteUpdateRequest.getRepeatInterval();
            if (interval != null){
                try {
                    RepeatInterval repeatInterval = RepeatInterval.valueOf(interval.toUpperCase());
                    notes.setRepeatInterval(repeatInterval);
                } catch (IllegalArgumentException e){
                    throw new IllegalArgumentException("Repeat interval không hợp lệ: " + interval);
                }
            }
        }

        noteRepository.save(notes);
        NoteDto noteDto = new NoteDto();

        noteDto.setCategory(notes.getCategory().getCategoryName().toString());
        noteDto.setTitle(notes.getTitle());
        noteDto.setContent(notes.getContent());
        noteDto.setPinned(notes.isPinned());
        noteDto.setReminderEnabled(notes.isReminderEnabled());
        noteDto.setReminderDate(notes.getReminderDate());
        noteDto.setRepeatInterval(notes.getRepeatInterval().toString());
        noteDto.setCreateDate(notes.getCreateDate());
        noteDto.setUpdateDate(notes.getUpdateDate());
        return noteDto;
    }
}
