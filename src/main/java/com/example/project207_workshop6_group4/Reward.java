package com.example.project207_workshop6_group4;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Reward {
    private SimpleStringProperty rwdNumber;
    private SimpleStringProperty rwdDescription;
    private SimpleIntegerProperty rwdSponsorId;

    public Reward(String rwdNumber, String rwdDescription, Integer rwdSponsorId) {
        this.rwdNumber = new SimpleStringProperty(rwdNumber);
        this.rwdDescription = new SimpleStringProperty(rwdDescription);
        this.rwdSponsorId = new SimpleIntegerProperty(rwdSponsorId);
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

    public String getRwdDescription() {
        return rwdDescription.get();
    }

    public SimpleStringProperty rwdDescriptionProperty() {
        return rwdDescription;
    }

    public void setRwdDescription(String rwdDescription) {
        this.rwdDescription.set(rwdDescription);
    }

    public int getRwdSponsorId() {
        return rwdSponsorId.get();
    }

    public SimpleIntegerProperty rwdSponsorIdProperty() {
        return rwdSponsorId;
    }

    public void setRwdSponsorId(int rwdSponsorId) {
        this.rwdSponsorId.set(rwdSponsorId);
    }
}
