package com.netwintest.companyapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.netwintest.companyapp.adapter.EmployeeAdapter;
import com.netwintest.companyapp.sql.EmployeeModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmployeeListActivity extends AppCompatActivity {
    @BindView(R.id.rvEmployees)
    RecyclerView rvEmployees;

    EmployeeAdapter adapter;
    ArrayList<Object> list;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        context = this;

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        rvEmployees.setLayoutManager(new LinearLayoutManager(context));
        loadEmployees();
    }

    public void loadEmployees() {
        EmployeeModel model = new EmployeeModel(context);

        list = new ArrayList<>();

        list.addAll(model.getEmployees());
        adapter = new EmployeeAdapter(context, list);
        rvEmployees.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
