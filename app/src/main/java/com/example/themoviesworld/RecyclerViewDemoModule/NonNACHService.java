package com.example.themoviesworld.RecyclerViewDemoModule;

import com.google.gson.annotations.Expose;

public class NonNACHService implements IAdjustment {
    @Expose
    private String serviceName = "";

    @Expose
    private String serviceId = "";

    @Expose
    private boolean checked; // Checkbox state - Service state

    @Expose
    private AdjustmentType periodAdjustmentType;

    @Expose
    private AdjustmentType valueAdjustmentType;


    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public int getAdjustmentType() {
        return 2;
    }

    public AdjustmentType getPeriodAdjustmentType() {
        return periodAdjustmentType;
    }

    public AdjustmentType getValueAdjustmentType() {
        return valueAdjustmentType;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setPeriodAdjustmentType(AdjustmentType periodAdjustmentType) {
        this.periodAdjustmentType = periodAdjustmentType;
    }

    public void setValueAdjustmentType(AdjustmentType valueAdjustmentType) {
        this.valueAdjustmentType = valueAdjustmentType;
    }
}
