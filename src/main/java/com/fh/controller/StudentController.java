package com.fh.controller;

import com.fh.entity.po.Student;
import com.fh.entity.vo.DataTablesData;
import com.fh.entity.vo.TableSearch;
import com.fh.service.StudentService;
import com.fh.utils.ExportExcel;
import com.fh.utils.FileUtilesalbb;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("student")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("queryStudentList")
    public DataTablesData queryStudentList(TableSearch search){
        DataTablesData data = studentService.queryStudentList(search);
        return data;
    }

    //上传文件
    @PostMapping("uploadFile")
    public Map uploadFile(MultipartFile picture){
        Map map = new HashMap();
        try {
            String s = FileUtilesalbb.saveFile(picture.getInputStream(), picture.getOriginalFilename());
            map.put("code",200);
            map.put("filePath",s);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",250);
            map.put("remark",e.getMessage());
        }
        return map;
    }

    //新增学生信息
    @PostMapping("addStudent")
    public Map addStudent(Student student){
        Map map = new HashMap();
        try {
            studentService.addStudent(student);
            map.put("code",200);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",250);
            map.put("remark",e.getMessage());
        }
        return map;
    }


    //删除学生信息
    @PostMapping("deleteStudent")
    public Map deleteStudent(Integer id){
        Map map = new HashMap();
        try {
            studentService.updateStudentById(id);
            map.put("code",200);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",250);
            map.put("remark",e.getMessage());
        }
        return map;
    }

    //回显学生信息
    @PostMapping("queryStudentById")
    public Map queryStudentById(Integer id){
        Map map = new HashMap();
        try {
            Student student = studentService.queryStudentById(id);
            map.put("code",200);
            map.put("data",student);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",250);
            map.put("remark",e.getMessage());
        }
        return map;
    }

    //修改学生信息
    @PostMapping("updateStudent")
    public Map updateStudent(Student student){
        Map map = new HashMap();
        try {
            studentService.updateStudent(student);
            map.put("code",200);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",250);
            map.put("remark",e.getMessage());
        }
        return map;
    }

    @GetMapping("importExcel")
    public void updateStudent(HttpServletResponse response) throws InvocationTargetException, NoSuchMethodException, IOException, IllegalAccessException {
        List<Student> list = studentService.queryStudentList();
        XSSFWorkbook xssfSheets = ExportExcel.exportExcel(list, Student.class);
        //下载文件
        //设置编码
        response.setCharacterEncoding("utf-8");
        //设置响应数据类型
        response.setContentType("application/octet-stream");//设置响应类型 告诉浏览器输出内容为流
        //设置响应文件名
        response.setHeader("Content-disposition", "attachment;filename=" + UUID.randomUUID().toString() + ".xlsx");
        //获取响应流
        ServletOutputStream os = response.getOutputStream();
        //将workbook的内容 写入输出流中
        xssfSheets.write(os);
        os.close();
    }

}
