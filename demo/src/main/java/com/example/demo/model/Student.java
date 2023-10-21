package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
//@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "roll_no")
    private String roll_no;
    @Column(name = "name")
    private String name;
    @Column(name = "school_name")
    private String school_name;
    @Column(name = "grade")
    private String grade;

//    @ManyToOne
//    @JoinColumn(name="file_id")
//    private File file;

    public Student() {
    }

    public Student(int id, String roll_no, String name, String school_name, String grade, File file) {
        this.id = id;
        this.roll_no = roll_no;
        this.name = name;
        this.school_name = school_name;
        this.grade = grade;
//        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

//    public File getFile() {
//        return file;
//    }
//
//    public void setFile(File file) {
//        this.file = file;
//    }
}
