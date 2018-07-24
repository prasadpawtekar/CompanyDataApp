package com.netwintest.companyapp.sql.datamodel;

import com.netwintest.companyapp.sql.Table;

import org.chalup.microorm.annotations.Column;

/**
 * Created by Admin on 7/24/2018.
 */

public class EmployeeData {
    @Column(Table.Employee.Column.EMP_ID)
    long empId;

    @Column(Table.Employee.Column.EMP_NAME)
    String name;

    @Column(Table.Employee.Column.EMP_DESIGNATION)
    String designation;

    @Column(Table.Employee.Column.EMP_AGE)
    int age;

    @Column(Table.Employee.Column.EMP_DOJ)
    long doj;

    public EmployeeData(String name, String designation, int age, long doj) {
        this.name = name;
        this.designation = designation;
        this.age = age;
        this.doj = doj;
    }

    public EmployeeData(long empId, String name, String designation, int age, long doj) {
        this.empId = empId;
        this.name = name;
        this.designation = designation;
        this.age = age;
        this.doj = doj;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getDoj() {
        return doj;
    }

    public void setDoj(long doj) {
        this.doj = doj;
    }

    @Override
    public String toString() {
        return "EmployeeData\n{" +
                "\nempId=" + empId +
                ",\n name='" + name + '\'' +
                ",\n designation='" + designation + '\'' +
                ",\n age=" + age +
                ",\n doj=" + doj +
                '}';
    }
}
