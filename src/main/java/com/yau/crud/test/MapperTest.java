package com.yau.crud.test;

import com.yau.crud.bean.Department;
import com.yau.crud.bean.Employee;
import com.yau.crud.mapper.DepartmentMapper;

import com.yau.crud.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试dao层的工作
 *
 * 推荐Spring的项目就可以使用Spring的单元测试，可以自动注入需要的组件
 * 1、导入SpringTest模块
 * 2、使用@ContextConfiguration注解指定Spring配置文件的位置
 * 3、直接autowired要使用的组件即可
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession; //批量的sqlSession
    /**
     * 测试DepartmentMapper
     * */
    @Test
    public void testCRUD(){
//        //1、创建Spring IOC容器
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring.xml");
//        //2、从容器中获取mapper
//        DepartmentMapper departmentMapper = applicationContext.getBean(DepartmentMapper.class);

//        System.out.println(departmentMapper);//org.apache.ibatis.binding.MapperProxy@106faf11

        //1、插入几个部门
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"技术部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));

        //2、生成员工数据，测试员工插入
//        employeeMapper.insertSelective(new Employee(null,"Jerry","M","Jerry@atguigu.com",1));
        //3、批量插入多个员工:批量、使用可以执行批量操作的sqlSession
//        for(){
//            employeeMapper.insertSelective(new Employee(null,"Jerry","M","Jerry@atguigu.com",1));
//        }
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for(int i=0;i<1000;i++){
            String uid = UUID.randomUUID().toString().substring(0, 5)+i;
            mapper.insertSelective(new Employee(null,uid,"M",uid+"@atguigu.com",1));
        }
        System.out.println("批量插入完成！");


    }
}
