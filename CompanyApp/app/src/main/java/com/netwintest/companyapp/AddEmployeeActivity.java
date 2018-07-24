package com.netwintest.companyapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.netwintest.companyapp.sql.EmployeeModel;
import com.netwintest.companyapp.sql.datamodel.EmployeeData;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddEmployeeActivity extends AppCompatActivity {
    @BindView(R.id.tilEmpName)
    TextInputLayout tilEmpName;

    @BindView(R.id.tilDesignation)
    TextInputLayout tilDesignation;

    @BindView(R.id.tilEmpAge)
    TextInputLayout tilEmpAge;

    @BindView(R.id.tvDoj)
    TextView tvDoj;

    long dateOfJoining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setTitle(R.string.app_name);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.ivCalendar)
    public void ivCalendarOnClick() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String selectedDate = day+"/"+(month+1)+"/"+year;
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, day);
                        dateOfJoining = calendar.getTimeInMillis();
                        tvDoj.setText(selectedDate);
                    }
                },
                year,
                month,
                day
        );
        dpd.show();
    }

    @OnClick(R.id.btnSave) public void saveEmployee() {
        if(isValidForm()) {
            try {
                String name = tilEmpName.getEditText().getText().toString().trim();
                String designation = tilDesignation.getEditText().getText().toString().trim();
                int age = Integer.parseInt(tilEmpAge.getEditText().getText().toString().trim());
                EmployeeData data = new EmployeeData(name, designation, age, dateOfJoining);
                EmployeeModel empModel = new EmployeeModel(this);
                boolean added = empModel.addEmployee(data);
                if (added) {
                    Toast.makeText(this, R.string.msg_employee_added_successfully, Toast.LENGTH_LONG).show();
                    resetForm();
                } else {
                    Toast.makeText(this, R.string.msg_failed_to_save_employee_data, Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, R.string.sorry_message, Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean isValidForm() {

        boolean result = true;
        try {
            if (tilEmpName.getEditText().getText().toString().trim().length() == 0) {
                result = false;
                tilEmpName.getEditText().setError(getString(R.string.error_enter_name));
            }

            if (tilDesignation.getEditText().getText().toString().trim().length() == 0) {
                result = false;
                tilDesignation.getEditText().setError(getString(R.string.error_enter_designation));
            }

            try {
                if (tilEmpAge.getEditText().getText().toString().trim().length() == 0) {
                    tilEmpAge.setError(getString(R.string.error_enter_age));
                    result = false;
                } else {
                    int age = Integer.parseInt(tilEmpAge.getEditText().getText().toString().trim());
                    if (age < 18 || age > 60) {
                        tilEmpAge.setError(getString(R.string.error_age_violation));
                        result = false;
                    }
                }
            } catch (NumberFormatException npe) {
                tilEmpAge.setError(getString(R.string.error_invalid_age));
            }

            if (dateOfJoining == 0) {
                tvDoj.setError(getString(R.string.error_select_doj));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.sorry_message, Toast.LENGTH_LONG).show();
        }
        return result;
    }

    @OnClick(R.id.btnShowEmployees) public void showEmployees() {
        Intent intent = new Intent(this, EmployeeListActivity.class);
        startActivity(intent);
    }

    public void resetForm() {
        try {
            tilEmpAge.getEditText().setText("");
            tilDesignation.getEditText().setText("");
            tilEmpName.getEditText().setText("");
            tvDoj.setText(getString(R.string.label_select_doj));
            dateOfJoining = 0;
            tilEmpName.getEditText().requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.sorry_message, Toast.LENGTH_LONG).show();
        }
    }
}
