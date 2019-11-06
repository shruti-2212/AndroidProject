package com.example.themoviesworld.RecyclerViewDemoModule;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviesworld.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DemoActivity extends AppCompatActivity {

    @BindView(R.id.common_adjustment_rv)
    RecyclerView mAdjustmentRV;

    @BindView(R.id.adjustment_types_rg)
    RadioGroup mAdjustmentTypeRG;

    @BindView(R.id.no_rb)
    RadioButton mNoAdjustmentRB;

    @BindView(R.id.nach_rb)
    RadioButton mNACHAdjustmentRB;

    @BindView(R.id.non_nach_rb)
    RadioButton mNonNACHAdjustmentRB;

    // Only one adapter is required and will do. Will remove
    private AdjustmentAdapter mNACHAdjustmentAdapter;

    private AdjustmentAdapter mNonNACHAdjustmentAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);

        mAdjustmentRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdjustmentRV.setItemAnimator(new DefaultItemAnimator());

        mNACHAdjustmentAdapter = new AdjustmentAdapter(this, R.layout.view_proposal_adjustment_nach);
        mNACHAdjustmentAdapter.setNonNACHServiceList(getServiceList());

        mNonNACHAdjustmentAdapter = new AdjustmentAdapter(this, R.layout.view_proposal_adjustment_non_nach);
        mNonNACHAdjustmentAdapter.setNonNACHServiceList(getNonNACHServiceList());

        mAdjustmentTypeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.no_rb:
                        mAdjustmentRV.setVisibility(View.GONE);
                        break;
                    case R.id.nach_rb:
                        mAdjustmentRV.setVisibility(View.VISIBLE);
                        mAdjustmentRV.setAdapter(mNACHAdjustmentAdapter);
                        break;
                    case R.id.non_nach_rb:
                        mAdjustmentRV.setVisibility(View.VISIBLE);
                        mAdjustmentRV.setAdapter(mNonNACHAdjustmentAdapter);
                        break;
                }
            }
        });

    }

    private List<NACHService> getServiceList() {

        List<NACHService> nachServiceList = new ArrayList<>();

        NACHService nachService = new NACHService();
        nachService.setServiceName("MDC");
        nachService.setServiceId("1");

        AdjustmentType adjustmentType0 = new AdjustmentType();
        adjustmentType0.setAdjustmentTypeId("900");
        adjustmentType0.setAdjustmentTypeName("0");
        nachService.setNachAdjustmentTypeZero(adjustmentType0);

        AdjustmentType adjustmentType1 = new AdjustmentType();
        adjustmentType1.setAdjustmentTypeId("901");
        adjustmentType1.setAdjustmentTypeName("1");
        nachService.setNachAdjustmentTypeOne(adjustmentType1);

        AdjustmentType adjustmentType2 = new AdjustmentType();
        adjustmentType2.setAdjustmentTypeId("902");
        adjustmentType2.setAdjustmentTypeName("2");
        nachService.setNachAdjustmentTypeTwo(adjustmentType2);

        nachServiceList.add(nachService);


        NACHService nachService1 = new NACHService();
        nachService1.setServiceName("STAR");
        nachService1.setServiceId("2");

        AdjustmentType adjustmentType10 = new AdjustmentType();
        adjustmentType10.setAdjustmentTypeId("900");
        adjustmentType10.setAdjustmentTypeName("0");
        nachService1.setNachAdjustmentTypeZero(adjustmentType10);

        AdjustmentType adjustmentType11 = new AdjustmentType();
        adjustmentType11.setAdjustmentTypeId("901");
        adjustmentType11.setAdjustmentTypeName("1");
        nachService1.setNachAdjustmentTypeOne(adjustmentType11);

        AdjustmentType adjustmentType12 = new AdjustmentType();
        adjustmentType12.setAdjustmentTypeId("902");
        adjustmentType12.setAdjustmentTypeName("2");
        nachService1.setNachAdjustmentTypeTwo(adjustmentType12);

        nachServiceList.add(nachService1);

        return nachServiceList;
    }


    private List<NonNACHService> getNonNACHServiceList() {
        List<NonNACHService> nonNachServiceList = new ArrayList<>();

        NonNACHService nonNACHService = new NonNACHService();
        nonNACHService.setServiceName("MDC");
        nonNACHService.setServiceId("1");

        AdjustmentType adjustmentType10 = new AdjustmentType();
        adjustmentType10.setAdjustmentTypeId("11");
        adjustmentType10.setAdjustmentTypeName("45 Days in Maxi");
        nonNACHService.setPeriodAdjustmentType(adjustmentType10);

        AdjustmentType adjustmentType11 = new AdjustmentType();
        adjustmentType11.setAdjustmentTypeId("12");
        adjustmentType11.setAdjustmentTypeName("Rs. 10,000");
        nonNACHService.setValueAdjustmentType(adjustmentType11);

        nonNachServiceList.add(nonNACHService);

        NonNACHService nonNACHService1 = new NonNACHService();
        nonNACHService1.setServiceName("TrustSeal");
        nonNACHService1.setServiceId("3");

        AdjustmentType adjustmentType20 = new AdjustmentType();
        adjustmentType20.setAdjustmentTypeId("13");
        adjustmentType20.setAdjustmentTypeName("30 Days in Maxi");
        nonNACHService1.setPeriodAdjustmentType(adjustmentType20);

        AdjustmentType adjustmentType21 = new AdjustmentType();
        adjustmentType21.setAdjustmentTypeId("14");
        adjustmentType21.setAdjustmentTypeName("Rs. 7,000");
        nonNACHService1.setValueAdjustmentType(adjustmentType21);

        nonNachServiceList.add(nonNACHService1);

        return nonNachServiceList;
    }

}
