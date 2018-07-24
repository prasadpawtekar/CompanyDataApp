package com.netwintest.companyapp.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.netwintest.companyapp.R;
import com.netwintest.companyapp.sql.datamodel.EmployeeData;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 7/24/2018.
 */

public class EmployeeHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvEmpName)
    TextView tvEmpName;

    @BindView(R.id.tvAge)
    TextView tvAge;

    @BindView(R.id.tvDesignation)
    TextView tvDesignation;

    @BindView(R.id.tvDateOfJoining)
    TextView tvDateOfJoining;

    public EmployeeHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Context context, EmployeeData data) {
        tvEmpName.setText(data.getName());
        tvAge.setText(context.getResources().getString(R.string.label_age, data.getAge()));
        tvDesignation.setText(data.getDesignation());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(data.getDoj());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String doj = day+"/"+month+"/"+year;

        tvDateOfJoining.setText(context.getResources().getString(R.string.label_emp_doj, doj));
    }
}
