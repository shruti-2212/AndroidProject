package com.example.themoviesworld.RecyclerViewDemoModule;

import com.google.gson.annotations.Expose;

public class NACHService implements IAdjustment {

    @Expose
    private String serviceName = "";

    @Expose
    private String serviceId = "";

    @Expose
    private boolean checked; // Checkbox state - Service state

    @Expose
    private boolean setUpFee;

    @Expose
    private AdjustmentType nachAdjustmentTypeZero;

    @Expose
    private AdjustmentType nachAdjustmentTypeOne;

    @Expose
    private AdjustmentType nachAdjustmentTypeTwo;

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

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public boolean hasSetUpFee() {
        return setUpFee;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setSetUpFee(boolean setUpFee) {
        this.setUpFee = setUpFee;
    }

    public AdjustmentType getNachAdjustmentTypeZero() {
        return nachAdjustmentTypeZero;
    }

    public void setNachAdjustmentTypeZero(AdjustmentType nachAdjustmentTypeZero) {
        this.nachAdjustmentTypeZero = nachAdjustmentTypeZero;
    }

    public AdjustmentType getNachAdjustmentTypeOne() {
        return nachAdjustmentTypeOne;
    }

    public void setNachAdjustmentTypeOne(AdjustmentType nachAdjustmentTypeOne) {
        this.nachAdjustmentTypeOne = nachAdjustmentTypeOne;
    }

    public AdjustmentType getNachAdjustmentTypeTwo() {
        return nachAdjustmentTypeTwo;
    }

    public void setNachAdjustmentTypeTwo(AdjustmentType nachAdjustmentTypeTwo) {
        this.nachAdjustmentTypeTwo = nachAdjustmentTypeTwo;
    }
}
