package com.example.session3.modals.repositories;
import com.example.session3.modals.entities.Instructors;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InstructorRepository {
    private final List<Instructors> instructors = new ArrayList<>();

    public InstructorRepository() {
        instructors.add(new Instructors(1L, "Nguyen Van A", "nguyenvana@example.com"));
        instructors.add(new Instructors(2L, "Tran Thi B", "tranthib@example.com"));
    }

    public List<Instructors> findAll() {
        return instructors;
    }

    public Instructors findById(Long id) {
        if (id == null) {
            return null;
        }
        return instructors.stream()
                .filter(instructor -> instructor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Instructors create(Instructors instructor) {
        if (instructor.getId() == null) {
            long nextId = instructors.stream()
                    .mapToLong(Instructors::getId)
                    .max()
                    .orElse(0L) + 1;
            instructor.setId(nextId);
        }
        instructors.add(instructor);
        return instructor;
    }

    public Instructors update(Long id, Instructors updatedInstructor) {
        Instructors existing = findById(id);
        if (existing == null) {
            return null;
        }
        existing.setName(updatedInstructor.getName());
        existing.setEmail(updatedInstructor.getEmail());
        return existing;
    }

    public Instructors deleteById(Long id) {
        Instructors existing = findById(id);
        if (existing == null) {
            return null;
        }
        instructors.remove(existing);
        return existing;
    }
}
