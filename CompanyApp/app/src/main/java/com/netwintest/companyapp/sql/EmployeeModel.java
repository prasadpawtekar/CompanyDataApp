package com.netwintest.companyapp.sql;

import android.content.ContentValues;
import android.content.Context;

import com.netwintest.companyapp.sql.datamodel.EmployeeData;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import org.chalup.microorm.MicroOrm;

import java.util.ArrayList;

/**
 * Created by Admin on 7/24/2018.
 */

public class EmployeeModel {
    MicroOrm orm;
    SQLiteDatabase db ;

    public EmployeeModel(Context context) {
        orm = new MicroOrm();
        db = DBHelper.getDb(context);
    }
    public boolean addEmployee(EmployeeData data) {
        ContentValues values = orm.toContentValues(data);
        values.remove(Table.Employee.Column.EMP_ID);


        long id = db.insert(Table.Employee.TABLE_NAME, null, values);
        return id != -1;
    }

    public ArrayList<EmployeeData> getEmployees() {
        ArrayList<EmployeeData> list = new ArrayList<>();
        Cursor cur = db.rawQuery("select * from "+Table.Employee.TABLE_NAME, null);
        while(cur.moveToNext()) {
            EmployeeData data = orm.fromCursor(cur, EmployeeData.class);
            list.add(data);
        }
        cur.close();
        return list;
    }
}
