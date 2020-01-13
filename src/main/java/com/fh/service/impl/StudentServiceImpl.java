package com.fh.service.impl;

import com.fh.dao.StudentDao;
import com.fh.entity.po.Student;
import com.fh.entity.vo.DataTablesData;
import com.fh.entity.vo.TableSearch;
import com.fh.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDao studentDao;

    @Resource
    private HttpServletRequest request;

    @Override
    public DataTablesData queryStudentList(TableSearch search) {
        long count = studentDao.queryCount();
        List<Student> list = studentDao.queryStudentList(search);
        DataTablesData data = new DataTablesData();
        data.setDraw(search.getDraw());
        data.setRecordsTotal((int)count);
        data.setRecordsFiltered((int)count);
        data.setData(list);
        return data;
    }

    @Override
    public void addStudent(Student student) {
        int year = student.getBirthday().getYear();
        int year1 = new Date().getYear();
        student.setAge(year1-year);
        student.setIsDel(0);
        student.setIp(request.getRemoteAddr());
        studentDao.insert(student);
    }

    @Override
    public void updateStudentById(Integer id) {
        Student student = new Student();
        student.setIsDel(1);
        student.setId(id);
        studentDao.updateById(student);
    }

    @Override
    public void updateStudent(Student student) {
        int year = student.getBirthday().getYear();
        int year1 = new Date().getYear();
        student.setAge(year1-year);
        studentDao.updateById(student);
    }

    @Override
    public Student queryStudentById(Integer id) {
        return studentDao.selectById(id);
    }

    @Override
    public List<Student> queryStudentList() {
        return studentDao.selectList(null);
    }
}
