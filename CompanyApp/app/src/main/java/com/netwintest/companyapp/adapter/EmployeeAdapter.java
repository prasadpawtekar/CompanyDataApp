package com.netwintest.companyapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.netwintest.companyapp.R;
import com.netwintest.companyapp.holder.EmployeeHolder;
import com.netwintest.companyapp.holder.MessageHolder;
import com.netwintest.companyapp.sql.datamodel.EmployeeData;
import com.netwintest.companyapp.sql.datamodel.MessageData;

import java.util.ArrayList;

/**
 * Created by Admin on 7/24/2018.
 */

public class EmployeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static final int VIEW_TYPE_EMPLOYEE=1, VIEW_TYPE_MSG=2;
    Context context;
    ArrayList<Object> list;

    public EmployeeAdapter(Context context, ArrayList<Object> list) {
        this.context = context;
        this.list = list;
        if(this.list.size() == 0 ) {
            this.list.add(new MessageData(context.getResources().getString(R.string.msg_employee_data_is_empty)));
        }
        Log.e("CompayApp", "List is : "+this.list);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_EMPLOYEE) {
            return new EmployeeHolder(((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.holder_employee, parent, false));
        } else if(viewType == VIEW_TYPE_MSG) {
            return new MessageHolder(((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.holder_message, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if(viewType == VIEW_TYPE_EMPLOYEE) {
            ((EmployeeHolder) holder).bind(context, (EmployeeData) list.get(position));
        } else if ( viewType == VIEW_TYPE_MSG) {
            ((MessageHolder) holder).bind(context, (MessageData) list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = list.get(position);
        if(obj instanceof EmployeeData) {
            return VIEW_TYPE_EMPLOYEE;
        } else if (obj instanceof MessageData) {
            return VIEW_TYPE_MSG;
        }
        return super.getItemViewType(position);
    }
}
