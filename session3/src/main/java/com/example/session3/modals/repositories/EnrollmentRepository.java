package com.example.session3.modals.repositories;

import com.example.session3.modals.entities.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();

    public EnrollmentRepository() {
        enrollments.add(new Enrollment(1L, "Le Van C", 1L));
        enrollments.add(new Enrollment(2L, "Pham Thi D", 2L));
    }

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public Enrollment findById(Long id) {
        if (id == null) {
            return null;
        }
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Enrollment create(Enrollment enrollment) {
        if (enrollment.getId() == null) {
            long nextId = enrollments.stream()
                    .mapToLong(Enrollment::getId)
                    .max()
                    .orElse(0L) + 1;
            enrollment.setId(nextId);
        }
        enrollments.add(enrollment);
        return enrollment;
    }

    public Enrollment update(Long id, Enrollment updatedEnrollment) {
        Enrollment existing = findById(id);
        if (existing == null) {
            return null;
        }
        existing.setStudentName(updatedEnrollment.getStudentName());
        existing.setCourseId(updatedEnrollment.getCourseId());
        return existing;
    }

    public Enrollment deleteById(Long id) {
        Enrollment existing = findById(id);
        if (existing == null) {
            return null;
        }
        enrollments.remove(existing);
        return existing;
    }
}
