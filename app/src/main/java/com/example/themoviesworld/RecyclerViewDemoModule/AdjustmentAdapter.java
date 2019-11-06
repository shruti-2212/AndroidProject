package com.example.themoviesworld.RecyclerViewDemoModule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviesworld.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adding code for now. Yet to be optimised. IRONICAL with the branch
 */
public class AdjustmentAdapter extends RecyclerView.Adapter<AdjustmentAdapter.BaseAdjustmentViewHolder> {

    private List<? extends IAdjustment> mNonNACHServiceList = new ArrayList<>();
    private int mItemLayoutId;
    private LayoutInflater mLayoutInflater;

    public AdjustmentAdapter(Context context, int iLayoutId) {
        this.mItemLayoutId = iLayoutId;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public void setNonNACHServiceList(List<? extends IAdjustment> nonNACHServiceList) {
        this.mNonNACHServiceList = nonNACHServiceList;
        notifyDataSetChanged();
    }

//    public class MyViewHolder extends RecyclerView.ViewHolder {
//
//        public CheckBox serviceTypeCheckbox;
//        public RadioButton mdcDaysRadio;
//        public RadioButton amountRadio;
//
//        public MyViewHolder(View view) {
//            super(view);
//
//            serviceTypeCheckbox = view.findViewById(R.id.service_checkbox);
//            mdcDaysRadio = view.findViewById(R.id.maxi_days__radio);
//            amountRadio = view.findViewById(R.id.rs_radio);
//        }
//    }


    @Override
    public void onBindViewHolder(AdjustmentAdapter.BaseAdjustmentViewHolder holder, int position) {
        // Instead of mLayoutId, you can also define based on viewType for separation.

        switch (mItemLayoutId) {
            case R.layout.view_proposal_adjustment_non_nach:
                NonNACHAdjustmentViewHolder nonNACHAdjVH = (NonNACHAdjustmentViewHolder) holder;
                NonNACHService nonNACHService = (NonNACHService) mNonNACHServiceList.get(position);

                nonNACHAdjVH.serviceNameCB.setText(nonNACHService.getServiceName());
                nonNACHAdjVH.periodAdjustmentRB.setText(nonNACHService.getPeriodAdjustmentType().toString());
                nonNACHAdjVH.valueAdjustmentRB.setText(nonNACHService.getValueAdjustmentType().toString());

                nonNACHAdjVH.serviceNameCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        nonNACHService.setChecked(nonNACHAdjVH.serviceNameCB.isChecked());
                    }
                });

                nonNACHAdjVH.nachAdjustmentRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.period_adjustment_rb:
                                nonNACHService.getPeriodAdjustmentType().setChecked(true);
                                nonNACHService.getValueAdjustmentType().setChecked(false);
                                break;
                            case R.id.value_adjustment_rb:
                                nonNACHService.getPeriodAdjustmentType().setChecked(false);
                                nonNACHService.getValueAdjustmentType().setChecked(true);
                                break;
                        }
                    }
                });

                break;
            case R.layout.view_proposal_adjustment_nach:
                NACHAdjustmentViewHolder nachAdjVH = (NACHAdjustmentViewHolder) holder;
                NACHService nachService = (NACHService) mNonNACHServiceList.get(position);

                nachAdjVH.serviceNameCB.setText(nachService.getServiceName());
                nachAdjVH.serviceNameCB.setChecked(nachService.isChecked());

                nachAdjVH.adjustmentTypeZeroRB.setText(nachService.getNachAdjustmentTypeZero().toString());
                nachAdjVH.adjustmentTypeZeroRB.setChecked(nachService.getNachAdjustmentTypeZero().isChecked());

                nachAdjVH.adjustmentTypeOneRB.setText(nachService.getNachAdjustmentTypeOne().toString());
                nachAdjVH.adjustmentTypeOneRB.setChecked(nachService.getNachAdjustmentTypeOne().isChecked());

                nachAdjVH.adjustmentTypeTwoRB.setText(nachService.getNachAdjustmentTypeTwo().toString());
                nachAdjVH.adjustmentTypeTwoRB.setChecked(nachService.getNachAdjustmentTypeTwo().isChecked());

                nachAdjVH.setUpFeeCB.setChecked(nachService.hasSetUpFee());

                nachAdjVH.serviceNameCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        nachService.setChecked(nachAdjVH.serviceNameCB.isChecked());
                    }
                });

                nachAdjVH.setUpFeeCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        nachService.setSetUpFee(nachAdjVH.setUpFeeCB.isChecked());
                    }
                });

                nachAdjVH.nachAdjustmentRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                        switch (checkedId) {
                            case R.id.adjustment_type_0_rb:
                                nachService.getNachAdjustmentTypeZero().setChecked(true);
                                nachService.getNachAdjustmentTypeOne().setChecked(false);
                                nachService.getNachAdjustmentTypeTwo().setChecked(false);
                                break;
                            case R.id.adjustment_type_1_rb:
                                nachService.getNachAdjustmentTypeZero().setChecked(false);
                                nachService.getNachAdjustmentTypeOne().setChecked(true);
                                nachService.getNachAdjustmentTypeTwo().setChecked(false);
                                break;
                            case R.id.adjustment_type_2_rb:
                                nachService.getNachAdjustmentTypeZero().setChecked(false);
                                nachService.getNachAdjustmentTypeOne().setChecked(false);
                                nachService.getNachAdjustmentTypeTwo().setChecked(true);
                                break;
                        }
                    }
                });

                break;
        }

//        String serviceName = nonNACHServiceList.get(position).getServiceName();
//
//        holder.serviceTypeCheckbox.setText(serviceName);
//        holder.mdcDaysRadio.setText(nonNACHServiceList.get(position).getMaxiDays());
//        holder.amountRadio.setText(nonNACHServiceList.get(position).getAmount());

    }

    @Override
    public int getItemCount() {
        return mNonNACHServiceList.size();
    }

    @Override
    public AdjustmentAdapter.BaseAdjustmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mItemLayoutId, parent, false);
        return getViewHolder(view);//new AdjustmentAdapter.MyViewHolder(v);
    }

    /**
     * Base class for AdjustmentViewHolder
     */
    static class BaseAdjustmentViewHolder extends RecyclerView.ViewHolder {
        BaseAdjustmentViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    /**
     * NACH adjustment views
     */
    class NACHAdjustmentViewHolder extends BaseAdjustmentViewHolder {
//        service_name_cb, nach_details_txv, radio_group, adjustment_type_2_rb, maxi_days_radio_1 ,maxi_days_radio_2, set_up_fee_cb

        @BindView(R.id.service_name_cb)
        CheckBox serviceNameCB;

        @BindView(R.id.nach_details_txv)
        TextView nachDetailsTxv;

        @BindView(R.id.nach_adjustment_rg)
        RadioGroup nachAdjustmentRG;

        @BindView(R.id.adjustment_type_0_rb)
        RadioButton adjustmentTypeZeroRB;

        @BindView(R.id.adjustment_type_1_rb)
        RadioButton adjustmentTypeOneRB;

        @BindView(R.id.adjustment_type_2_rb)
        RadioButton adjustmentTypeTwoRB;

        @BindView(R.id.set_up_fee_cb)
        CheckBox setUpFeeCB;

        NACHAdjustmentViewHolder(View iView) {
            super(iView);
            ButterKnife.bind(this, iView);
        }

    }

    /**
     * Non NACH adjustment views
     */
    class NonNACHAdjustmentViewHolder extends BaseAdjustmentViewHolder {
        //        service_name_cb, nach_adjustment_rg, period_adjustment_rb, value_adjustment_rb
        @BindView(R.id.service_name_cb)
        CheckBox serviceNameCB;

        @BindView(R.id.nach_adjustment_rg)
        RadioGroup nachAdjustmentRG;

        @BindView(R.id.period_adjustment_rb)
        RadioButton periodAdjustmentRB;

        @BindView(R.id.value_adjustment_rb)
        RadioButton valueAdjustmentRB;

        NonNACHAdjustmentViewHolder(View iView) {
            super(iView);
            ButterKnife.bind(this, iView);
        }
    }

    public BaseAdjustmentViewHolder getViewHolder(View iView) {
        switch (mItemLayoutId) {
            case R.layout.view_proposal_adjustment_nach:
                return new NACHAdjustmentViewHolder(iView);
            case R.layout.view_proposal_adjustment_non_nach:
            default:
                return new NonNACHAdjustmentViewHolder(iView);
        }
    }

}
