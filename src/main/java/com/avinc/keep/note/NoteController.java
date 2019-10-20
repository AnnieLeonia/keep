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

    @GetMapping("/questions")
    public List<Note> getQuestions() {
        return noteRepository.findAll();
    }


    @PostMapping("/questions")
    public Note createQuestion(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    @PutMapping("/questions/{questionId}")
    public Note updateQuestion(@PathVariable Long questionId,
                               @Valid @RequestBody Note noteRequest) {
        return noteRepository.findById(questionId)
                .map(note -> {
                    note.setName(noteRequest.getName());
                    note.setDescription(noteRequest.getDescription());
                    return noteRepository.save(note);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }


    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        return noteRepository.findById(questionId)
                .map(note -> {
                    noteRepository.delete(note);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }
}
