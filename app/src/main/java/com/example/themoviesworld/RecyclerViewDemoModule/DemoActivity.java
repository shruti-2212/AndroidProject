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
   /* private AdjustmentAdapter mNACHAdjustmentAdapter;

    private AdjustmentAdapter mNonNACHAdjustmentAdapter;*/

    private AdjustmentAdapter mAdjustmentAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);

        mAdjustmentRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdjustmentRV.setItemAnimator(new DefaultItemAnimator());

        /*mNACHAdjustmentAdapter = new AdjustmentAdapter(this, R.layout.view_proposal_adjustment_nach);
        mNACHAdjustmentAdapter.setNonNACHServiceList(getServiceList());

        mNonNACHAdjustmentAdapter = new AdjustmentAdapter(this, R.layout.view_proposal_adjustment_non_nach);
        mNonNACHAdjustmentAdapter.setNonNACHServiceList(getNonNACHServiceList());*/

        mAdjustmentAdapter = new AdjustmentAdapter(this);
        mAdjustmentRV.setAdapter(mAdjustmentAdapter);

        mAdjustmentTypeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.no_rb:
                        mAdjustmentRV.setVisibility(View.GONE);
                        break;
                    case R.id.nach_rb:
                        mAdjustmentRV.setVisibility(View.VISIBLE);
                        mAdjustmentAdapter.setAdjustmentServiceList(getNachServiceList());
                        //mAdjustmentRV.setAdapter(mNACHAdjustmentAdapter);
                        break;
                    case R.id.non_nach_rb:
                        mAdjustmentRV.setVisibility(View.VISIBLE);
                        mAdjustmentAdapter.setAdjustmentServiceList(getNonNACHServiceList());
                        //mAdjustmentRV.setAdapter(mNonNACHAdjustmentAdapter);
                        break;
                }
            }
        });

    }

    private List<NACHService> getNachServiceList() {

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

        NACHService nachService2 = new NACHService();
        nachService2.setServiceName("STAR 1");
        nachService2.setServiceId("2");

        AdjustmentType adjustmentType20 = new AdjustmentType();
        adjustmentType20.setAdjustmentTypeId("900");
        adjustmentType20.setAdjustmentTypeName("0");
        nachService2.setNachAdjustmentTypeZero(adjustmentType20);

        AdjustmentType adjustmentType21 = new AdjustmentType();
        adjustmentType21.setAdjustmentTypeId("901");
        adjustmentType21.setAdjustmentTypeName("1");
        nachService2.setNachAdjustmentTypeOne(adjustmentType21);

        AdjustmentType adjustmentType22 = new AdjustmentType();
        adjustmentType22.setAdjustmentTypeId("902");
        adjustmentType22.setAdjustmentTypeName("2");
        nachService2.setNachAdjustmentTypeTwo(adjustmentType22);

        nachServiceList.add(nachService2);

        NACHService nachService3 = new NACHService();
        nachService3.setServiceName("STAR 2");
        nachService3.setServiceId("2");

        AdjustmentType adjustmentType30 = new AdjustmentType();
        adjustmentType30.setAdjustmentTypeId("900");
        adjustmentType30.setAdjustmentTypeName("0");
        nachService3.setNachAdjustmentTypeZero(adjustmentType30);

        AdjustmentType adjustmentType31 = new AdjustmentType();
        adjustmentType31.setAdjustmentTypeId("901");
        adjustmentType31.setAdjustmentTypeName("1");
        nachService3.setNachAdjustmentTypeOne(adjustmentType31);

        AdjustmentType adjustmentType32 = new AdjustmentType();
        adjustmentType32.setAdjustmentTypeId("902");
        adjustmentType32.setAdjustmentTypeName("2");
        nachService3.setNachAdjustmentTypeTwo(adjustmentType32);

        nachServiceList.add(nachService3);

        NACHService nachService4 = new NACHService();
        nachService4.setServiceName("STAR 3");
        nachService4.setServiceId("2");

        AdjustmentType adjustmentType40 = new AdjustmentType();
        adjustmentType40.setAdjustmentTypeId("900");
        adjustmentType40.setAdjustmentTypeName("0");
        nachService4.setNachAdjustmentTypeZero(adjustmentType40);

        AdjustmentType adjustmentType41 = new AdjustmentType();
        adjustmentType41.setAdjustmentTypeId("901");
        adjustmentType41.setAdjustmentTypeName("1");
        nachService4.setNachAdjustmentTypeOne(adjustmentType41);

        AdjustmentType adjustmentType42 = new AdjustmentType();
        adjustmentType42.setAdjustmentTypeId("902");
        adjustmentType42.setAdjustmentTypeName("2");
        nachService4.setNachAdjustmentTypeTwo(adjustmentType42);

        nachServiceList.add(nachService4);

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
        nonNACHService1.setServiceId("2");

        AdjustmentType adjustmentType20 = new AdjustmentType();
        adjustmentType20.setAdjustmentTypeId("13");
        adjustmentType20.setAdjustmentTypeName("30 Days in Maxi");
        nonNACHService1.setPeriodAdjustmentType(adjustmentType20);

        AdjustmentType adjustmentType21 = new AdjustmentType();
        adjustmentType21.setAdjustmentTypeId("14");
        adjustmentType21.setAdjustmentTypeName("Rs. 7,000");
        nonNACHService1.setValueAdjustmentType(adjustmentType21);

        nonNachServiceList.add(nonNACHService1);

        NonNACHService nonNACHService2 = new NonNACHService();
        nonNACHService2.setServiceName("Sample1");
        nonNACHService2.setServiceId("3");

        AdjustmentType adjustmentType30 = new AdjustmentType();
        adjustmentType30.setAdjustmentTypeId("15");
        adjustmentType30.setAdjustmentTypeName("45 Days in Maxi");
        nonNACHService2.setPeriodAdjustmentType(adjustmentType30);

        AdjustmentType adjustmentType31 = new AdjustmentType();
        adjustmentType31.setAdjustmentTypeId("16");
        adjustmentType31.setAdjustmentTypeName("Rs. 8,000");
        nonNACHService2.setValueAdjustmentType(adjustmentType31);

        nonNachServiceList.add(nonNACHService2);

        NonNACHService nonNACHService3 = new NonNACHService();
        nonNACHService3.setServiceName("Sample2");
        nonNACHService3.setServiceId("4");

        AdjustmentType adjustmentType40 = new AdjustmentType();
        adjustmentType40.setAdjustmentTypeId("17");
        adjustmentType40.setAdjustmentTypeName("45 Days in Maxi");
        nonNACHService3.setPeriodAdjustmentType(adjustmentType40);

        AdjustmentType adjustmentType41 = new AdjustmentType();
        adjustmentType41.setAdjustmentTypeId("18");
        adjustmentType41.setAdjustmentTypeName("Rs. 8,000");
        nonNACHService3.setValueAdjustmentType(adjustmentType41);

        nonNachServiceList.add(nonNACHService3);

        NonNACHService nonNACHService4 = new NonNACHService();
        nonNACHService4.setServiceName("Sample3");
        nonNACHService4.setServiceId("5");

        AdjustmentType adjustmentType50 = new AdjustmentType();
        adjustmentType50.setAdjustmentTypeId("19");
        adjustmentType50.setAdjustmentTypeName("45 Days in Maxi");
        nonNACHService4.setPeriodAdjustmentType(adjustmentType50);

        AdjustmentType adjustmentType51 = new AdjustmentType();
        adjustmentType51.setAdjustmentTypeId("20");
        adjustmentType51.setAdjustmentTypeName("Rs. 8,000");
        nonNACHService4.setValueAdjustmentType(adjustmentType51);

        nonNachServiceList.add(nonNACHService4);

        return nonNachServiceList;
    }

}
