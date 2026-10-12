package com.example.session3.modals.repositories;

import com.example.session3.modals.entities.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    public CourseRepository() {
        courses.add(new Course(1L, "Lap trinh Java Co ban", "ACTIVE", 1L));
        courses.add(new Course(2L, "Spring Boot Web Development", "PENDING", 2L));
    }

    public List<Course> findAll() {
        return courses;
    }

    public Course findById(Long id) {
        if (id == null) {
            return null;
        }
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Course create(Course course) {
        if (course.getId() == null) {
            long nextId = courses.stream()
                    .mapToLong(Course::getId)
                    .max()
                    .orElse(0L) + 1;
            course.setId(nextId);
        }
        courses.add(course);
        return course;
    }

    public Course update(Long id, Course updatedCourse) {
        Course existing = findById(id);
        if (existing == null) {
            return null;
        }
        existing.setTitle(updatedCourse.getTitle());
        existing.setStatus(updatedCourse.getStatus());
        existing.setInstructorId(updatedCourse.getInstructorId());
        return existing;
    }

    public Course deleteById(Long id) {
        Course existing = findById(id);
        if (existing == null) {
            return null;
        }
        courses.remove(existing);
        return existing;
    }
}
