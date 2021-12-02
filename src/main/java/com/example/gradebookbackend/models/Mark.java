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
    private Integer student_id;

    @Column(name = "subject_id")
    private Integer subject_id;

    @Column(name = "mark")
    private Integer mark;

    @Column(name = "semester")
    private Integer semester;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User student;

    public String getSubjectName() {
        return subject.getName();
    }

    public String getTeacherName() {
        return teacher.getSurname().toString() + " " +
                teacher.getName().charAt(0) + "." +
                (teacher.getPatronymic().isEmpty() ? "" : teacher.getPatronymic().charAt(0) + ".");
    }

    public String getStudentName() {
        return student.getSurname().toString() + " " +
                student.getName().charAt(0) + "." +
                (student.getPatronymic().isEmpty() ? "" : student.getPatronymic().charAt(0) + ".");
    }
}
