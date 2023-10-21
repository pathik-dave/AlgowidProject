package com.example.demo.services;

import com.example.demo.model.File;
import com.example.demo.model.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    void save(MultipartFile file);
    List<Student> getAllStudents();
    Student save(Student student);
    List<File> getAllFiles();
    List<File> getFileByName(String fileName);
    long deleteFile(String fileName);
}
