package com.example.gradebookbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "marks", schema = "public")
public class Mark {
    @Id
    @Column(name = "mark_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mark_id;

    @Column(name = "teacher_id")
    private Integer teacher_id;

    @Column(name = "student_id")
    private String student_id;

    @Column(name = "subject_id")
    private String subject_id;

    @Column(name = "mark")
    private String mark;

    @Column(name = "semester")
    private String semester;
}
