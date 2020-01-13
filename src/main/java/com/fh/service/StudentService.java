package com.fh.service;

import com.fh.entity.po.Student;
import com.fh.entity.vo.DataTablesData;
import com.fh.entity.vo.TableSearch;

import java.util.List;

public interface StudentService {
    DataTablesData queryStudentList(TableSearch search);

    void addStudent(Student student);

    void updateStudentById(Integer id);

    void updateStudent(Student student);

    Student queryStudentById(Integer id);

    List<Student> queryStudentList();
}
