package com.yau.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yau.crud.bean.Employee;
import com.yau.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 处理员工CRUD请求
 * */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * 查询员工数据(分页查询)
     * @return
     * */
    @RequestMapping("/getEmployees")
    //这不是一个分页查询： 引入PageHelper分页插件
    /*
     * 在查询功能之前，使用PageHelper.startPage(int pageNum,int pageSize)开启分页功能
     *  pageNum：当前页的页码
     *  pageSize：每页显示的条数
     * */
    public String getEmployees(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model){
        PageHelper.startPage(pn,5);
        //startPage后面紧跟的这个查询就是一个分页查询
        List<Employee> list = employeeService.getAll();
        /**
         * 在查询获取list集合之后，使用
         * PageInfo<T> pageInfo=new PageInfo<>(List<T> list,int navigatePages)获取分页相关数据
         * list：分页之后的数据
         * navigatePages：导航分页的页码数
         * */
        /*  使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
            pageInfo封装了详细的分页信息，包括查询出来的数据，传入连续显示的页数
        */
        PageInfo pageInfo=new PageInfo(list,5);
        model.addAttribute("pageInfo",pageInfo);
        return "list";
    }
}
