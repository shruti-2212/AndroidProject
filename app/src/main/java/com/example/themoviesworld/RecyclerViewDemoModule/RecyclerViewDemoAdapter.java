package com.example.themoviesworld.RecyclerViewDemoModule;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviesworld.R;


import java.util.List;

public class RecyclerViewDemoAdapter extends RecyclerView.Adapter<RecyclerViewDemoAdapter.MyViewHolder> {

    private List<ServiceTypePojo> serviceTypePojoList;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CheckBox serviceTypeCheckbox;
        public RadioButton mdcDaysRadio;
        public RadioButton amountRadio;

        public MyViewHolder(View view) {
            super(view);

            serviceTypeCheckbox = view.findViewById(R.id.service_checkbox);
            mdcDaysRadio = view.findViewById(R.id.maxi_days__radio);
            amountRadio = view.findViewById(R.id.rs_radio);
        }
    }

    public RecyclerViewDemoAdapter(Context context, List<ServiceTypePojo> serviceTypePojo) {

        this.mContext = context;
        this.serviceTypePojoList = serviceTypePojo;
    }

    @Override
    public void onBindViewHolder(RecyclerViewDemoAdapter.MyViewHolder holder, int position) {

        String serviceName = serviceTypePojoList.get(position).getServiceName();

        holder.serviceTypeCheckbox.setText(serviceName);
        holder.mdcDaysRadio.setText(serviceTypePojoList.get(position).getMaxiDays());
        holder.amountRadio.setText(serviceTypePojoList.get(position).getAmount());
    }

    @Override
    public int getItemCount() {

        return serviceTypePojoList.size();
    }

    @Override
    public RecyclerViewDemoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_proposal_adjustment_non_nach, parent, false);
        return new RecyclerViewDemoAdapter.MyViewHolder(v);
    }
}
