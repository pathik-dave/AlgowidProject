package com.example.demo.controllers;

import com.example.demo.dto.ResponseMessage;
import com.example.demo.helper.ExcelHelper;
import com.example.demo.model.File;
import com.example.demo.model.Student;
import com.example.demo.services.StudentService;
import com.example.demo.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(name = "role")String role) {
        String message = "";

        if(role.equals(Role.ADMIN.name())) {
            if (ExcelHelper.hasExcelFormat(file)) {
                try {
                    studentService.save(file);

                    message = "Uploaded the file successfully: " + file.getOriginalFilename();
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
                } catch (Exception e) {
                    message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
                }
            }

            message = "Please upload an excel file!";
        }else {
            message = "You don't have enough privilages to upload a file!";
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/list/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();

            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/files")
    public ResponseEntity<List<File>> getAllFiles() {
        try {
            List<File> files = studentService.getAllFiles();

            if (files.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(files, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/files/{filename}")
    public ResponseEntity<List<File>> getFileByName(@PathVariable("filename")String fileName) {
        try {
            List<File> files = studentService.getFileByName(fileName);

            if (files.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(files, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<ResponseMessage> deleteFile(@PathVariable("filename")String fileName, @RequestParam(name = "role")String role) {
        String message = "";
        try {
            if(role.equals(Role.ADMIN.name())) {
                Long recordCount = studentService.deleteFile(fileName);

                if (recordCount == 0) {
                    message = fileName + " does not exist !";
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
                }
                message = fileName + " deleted successfully !";
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            }
            message = " You don't have enough privilages to delete a file! !";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@GetMapping("/student")
    public List<Student> index(){
        return studentService.getStudentList();
    }

    @PostMapping("/student")
    public Student create(@RequestBody Student student){
        return studentService.save(student);
    }*/
}
