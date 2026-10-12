package com.example.session3.modals.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    private Long id;
    private String studentName;
    private Long courseId;
}
