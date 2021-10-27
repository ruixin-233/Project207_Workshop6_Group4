/**
 * Author: Laura Luo
 * Date: Oct 18, 2021
 * Project 207 - WorkShop6
 */

package com.example.project207_workshop6_group4.Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RewardSponsor {
    private SimpleIntegerProperty SponsorId;
    private SimpleStringProperty SponsorName;
    //private SimpleStringProperty rwdDecription;

    public RewardSponsor(int SponsorId, String SponsorName) {
        this.SponsorId = new SimpleIntegerProperty(SponsorId);
        this.SponsorName = new SimpleStringProperty(SponsorName);
    }

    public int getSponsorId() {
        return SponsorId.get();
    }

    public SimpleIntegerProperty sponsorIdProperty() {
        return SponsorId;
    }

    public void setSponsorId(int sponsorId) {
        this.SponsorId.set(sponsorId);
    }

    public String getSponsorName() {
        return SponsorName.get();
    }

    public SimpleStringProperty sponsorNameProperty() {
        return SponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.SponsorName.set(sponsorName);
    }
}
