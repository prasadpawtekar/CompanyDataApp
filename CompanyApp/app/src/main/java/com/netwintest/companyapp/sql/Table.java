package com.netwintest.companyapp.sql;

/**
 * Created by Admin on 7/24/2018.
 */

public class Table {
    public static class Employee {
        public static final String TABLE_NAME = "tbl_emp";

        public static final class Column {
            public static final String EMP_ID = "emp_id",
            EMP_NAME = "emp_name",
            EMP_AGE = "emp_age",
            EMP_DESIGNATION = "emp_designation",
            EMP_DOJ = "emp_doj";
        }

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+
                "(" +
                Column.EMP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Column.EMP_NAME + " TEXT,"+
                Column.EMP_AGE + " INTEGER,"+
                Column.EMP_DESIGNATION + " TEXT,"+
                Column.EMP_DOJ + " INTEGER"+
                ")";
    }
}
