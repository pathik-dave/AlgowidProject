package com.example.demo.services;

import com.example.demo.helper.ExcelHelper;
import com.example.demo.model.File;
import com.example.demo.model.Student;
import com.example.demo.repositories.FileRepository;
import com.example.demo.repositories.StudentRecordsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentRecordsRepository studentRepo;

    @Autowired
    FileRepository fileRepo;

    @Override
    public void save(MultipartFile uploadedFile) {
        try {
            File fileObj = new File();
            List<Student> students = ExcelHelper.excelToTutorials(uploadedFile.getInputStream());
            fileObj.setStudents(students);
            fileObj.setName(uploadedFile.getOriginalFilename());
            fileRepo.save(fileObj);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public List<File> getAllFiles() {
        return fileRepo.findAll();
    }

    @Override
    public List<File> getFileByName(String fileName) {
        return fileRepo.findByName(fileName);
    }

    @Transactional
    @Override
    public long deleteFile(String fileName) {
        return fileRepo.deleteByName(fileName);
    }

    @Override
    public Student save(Student student) {
        studentRepo.save(student);
        return student;
    }
}
