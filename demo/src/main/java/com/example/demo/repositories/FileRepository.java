package com.example.demo.repositories;

import com.example.demo.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File,Integer> {
    long deleteByName(String fileName);
    List<File> findByName(String name);
}
