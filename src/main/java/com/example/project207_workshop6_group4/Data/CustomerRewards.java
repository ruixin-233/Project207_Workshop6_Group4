package com.example.project207_workshop6_group4.Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CustomerRewards {
    private SimpleIntegerProperty customerId;
    private SimpleIntegerProperty rewardId;
    private SimpleStringProperty rwdNumber;


    public CustomerRewards(int customerId, int rewardId, String rwdNumber) {
        this.customerId = new SimpleIntegerProperty(customerId);
        this.rewardId = new SimpleIntegerProperty(rewardId);
        this.rwdNumber = new SimpleStringProperty(rwdNumber);
    }

    public int getCustomerId() {
        return customerId.get();
    }

    public SimpleIntegerProperty customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public int getRewardId() {
        return rewardId.get();
    }

    public SimpleIntegerProperty rewardIdProperty() {
        return rewardId;
    }

    public void setRewardId(int rewardId) {
        this.rewardId.set(rewardId);
    }

    public String getRwdNumber() {
        return rwdNumber.get();
    }

    public SimpleStringProperty rwdNumberProperty() {
        return rwdNumber;
    }

    public void setRwdNumber(String rwdNumber) {
        this.rwdNumber.set(rwdNumber);
    }

}
