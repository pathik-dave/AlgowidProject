package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
//@Data
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Getter
//    @Setter
    public Integer id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)//, mappedBy = "file"
    private List<Student> students;

    public File() {
    }

    public File(Integer id, String name, List<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
