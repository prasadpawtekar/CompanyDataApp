package com.netwintest.companyapp.sql;

import android.content.Context;

import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteException;

import java.io.File;

/**
 * Created by Admin on 7/24/2018.
 */

public class DBHelper {
    /*
    * For encryption and decryption, using SQLiteCypher
    * which is in built feature in net.sqlcipher library
    * which provides high performance light weight encryption
    * and decryption.
    * For more info, refer : https://www.zetetic.net/sqlcipher/design/*/

    static SQLiteDatabase db;
    static final String DB_NAME = "company_db.db";
    static final String DB_PASSWORD = "netwin123";

    public static SQLiteDatabase getDb(Context context) {
        if(db == null) {
            try {
                SQLiteDatabase.loadLibs(context);
                File path = context.getDatabasePath(DB_NAME);
                path.getParentFile().mkdirs();
                //path.delete();
                db = SQLiteDatabase.openOrCreateDatabase(path, DB_PASSWORD, null);

                db.execSQL(Table.Employee.CREATE_TABLE);
            } catch (SQLiteException sqlExp) {
                sqlExp.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return db;
    }

}
