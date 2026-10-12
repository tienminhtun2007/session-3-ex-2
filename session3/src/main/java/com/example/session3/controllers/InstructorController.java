package com.example.session3.controllers;

import com.example.session3.modals.entities.Instructors;
import com.example.session3.modals.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public List<Instructors> getAllInstructors() {
        return instructorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructors> getInstructorById(@PathVariable Long id) {
        Instructors instructor = instructorService.getInstructorById(id);
        if (instructor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(instructor);
    }

    @PostMapping
    public ResponseEntity<Instructors> createInstructor(@RequestBody Instructors instructor) {
        if (instructor.getName() == null || instructor.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Instructors created = instructorService.createInstructor(instructor);
        return ResponseEntity.status(   HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructors> updateInstructor(@PathVariable Long id, @RequestBody Instructors instructor) {
        Instructors updated = instructorService.updateInstructor(id, instructor);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructorById(@PathVariable Long id) {
        Instructors deleted = instructorService.deleteInstructorById(id);
        if (deleted == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
}
