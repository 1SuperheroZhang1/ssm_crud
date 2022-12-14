package com.yau.crud.service;

import com.yau.crud.bean.Employee;
import com.yau.crud.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 查询所有员工
     * @return
     * */
    public List<Employee> getAll(){
        return employeeMapper.selectByExampleWithDept(null);
    }
}
