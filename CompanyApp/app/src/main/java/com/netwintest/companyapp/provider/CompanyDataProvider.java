package com.netwintest.companyapp.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.netwintest.companyapp.sql.DBHelper;
import com.netwintest.companyapp.sql.Table;

import net.sqlcipher.database.SQLiteDatabase;

/**
 * Created by Admin on 7/24/2018.
 */

public class CompanyDataProvider extends ContentProvider {
    SQLiteDatabase db;
    Context context;
    UriMatcher uriMatcher;

    final static int EMPLOYEE_MATCH = 1;
    final static String AUTHORITY = "com.netwintest.companyapp.companydata";
    final static String EMPLOYEE = "employee";

    @Override
    public boolean onCreate() {
        context = getContext();
        db = DBHelper.getDb(context);
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, EMPLOYEE, EMPLOYEE_MATCH);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if(uriMatcher.match(uri) == EMPLOYEE_MATCH) {
            return db.query(Table.Employee.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        if(uriMatcher.match(uri) == EMPLOYEE_MATCH) {
            return "vnd.android.cursor.dir/vnd.com.example.companyapp.provider.employee";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if(uriMatcher.match(uri) == EMPLOYEE_MATCH) {
            long id = db.insert(Table.Employee.TABLE_NAME, null, values);
            Uri u = Uri.parse("content://"+AUTHORITY+"/"+EMPLOYEE+"/"+id);
            return u;
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        if ( uriMatcher.match(uri) == EMPLOYEE_MATCH ) {
            return db.delete(Table.Employee.TABLE_NAME, selection, selectionArgs);
        }

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        if ( uriMatcher.match(uri) == EMPLOYEE_MATCH ) {
            return db.update("course", values, selection, selectionArgs);
        }
        return 0;
    }


}
