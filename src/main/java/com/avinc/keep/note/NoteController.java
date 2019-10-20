package com.avinc.keep.note;

import com.avinc.keep.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/notes")
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }


    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    @PutMapping("/notes/{noteId}")
    public Note updateNote(@PathVariable Long noteId,
                               @Valid @RequestBody Note noteRequest) {
        return noteRepository.findById(noteId)
                .map(note -> {
                    note.setName(noteRequest.getName());
                    note.setDescription(noteRequest.getDescription());
                    return noteRepository.save(note);
                }).orElseThrow(() -> new ResourceNotFoundException("Note not found with id " + noteId));
    }


    @DeleteMapping("/notes/{noteId}")
    public ResponseEntity<?> deleteNote(@PathVariable Long noteId) {
        return noteRepository.findById(noteId)
                .map(note -> {
                    noteRepository.delete(note);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Note not found with id " + noteId));
    }
}
