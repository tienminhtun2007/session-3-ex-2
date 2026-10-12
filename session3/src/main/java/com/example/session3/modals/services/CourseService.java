package com.example.session3.modals.services;

import com.example.session3.modals.entities.Course;
import com.example.session3.modals.repositories.CourseRepository;
import com.example.session3.modals.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course createCourse(Course course) {
        if (course.getInstructorId() == null || instructorRepository.findById(course.getInstructorId()) == null) {
            return null;
        }
        return courseRepository.create(course);
    }

    public Course updateCourse(Long id, Course course) {
        if (course.getInstructorId() != null && instructorRepository.findById(course.getInstructorId()) == null) {
            return null;
        }
        return courseRepository.update(id, course);
    }

    public Course deleteCourseById(Long id) {
        return courseRepository.deleteById(id);
    }
}
