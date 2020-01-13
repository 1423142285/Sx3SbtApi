package com.fh.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fh.commont.annotation.ExcelAnnotation;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName("stb_student")
@ExcelAnnotation(title = "学生信息", sheetName = "学生信息")
public class Student {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @ExcelAnnotation(column = "学生姓名")
    private String name;
    @ExcelAnnotation(column = "年龄")
    private Integer age;
    @ExcelAnnotation(column = "生日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @ExcelAnnotation(column = "地址")
    private String address;
    private Integer isDel;
    @ExcelAnnotation(column = "图片")
    private String imgpath;
    private String ip;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
