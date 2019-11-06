package com.example.themoviesworld.RecyclerViewDemoModule;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;

public class AdjustmentType {

    @Expose
    private String adjustmentTypeName;

    @Expose
    private String adjustmentTypeId;

    @Expose
    private boolean checked; //  RadioButton state - AdjustmentType State

//    public String getAdjustmentTypeName() {
//        return adjustmentTypeName;
//    }

    public String getAdjustmentTypeId() {
        return adjustmentTypeId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @NonNull
    @Override
    public String toString() {
        return adjustmentTypeName;
    }

    public void setAdjustmentTypeName(String adjustmentTypeName) {
        this.adjustmentTypeName = adjustmentTypeName;
    }

    public void setAdjustmentTypeId(String adjustmentTypeId) {
        this.adjustmentTypeId = adjustmentTypeId;
    }
}
