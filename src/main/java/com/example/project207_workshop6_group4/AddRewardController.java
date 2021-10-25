package com.example.project207_workshop6_group4;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import com.example.project207_workshop6_group4.Data.CustomerRewards;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddRewardController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cbRewardId"
    private ComboBox<?> cbRewardId; // Value injected by FXMLLoader

    @FXML // fx:id="cbRwdNumber"
    private ComboBox<?> cbRwdNumber; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustomerId"
    private TextField tfCustomerId; // Value injected by FXMLLoader

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    //private ObservableList<CustomerRewards> mainData;
    private int selectedIndex;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cbRewardId != null : "fx:id=\"cbRewardId\" was not injected: check your FXML file 'AddReward.fxml'.";
        assert cbRwdNumber != null : "fx:id=\"cbRwdNumber\" was not injected: check your FXML file 'AddReward.fxml'.";
        assert cbRwdNumber != null : "fx:id=\"cbRwdNumber\" was not injected: check your FXML file 'AddReward.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'AddReward.fxml'.";

        this.getRewardIDs(AddRewardController.this.cbRewardId.getValue());
        this.getRwdNumbers(AddRewardController.this.cbRwdNumber.getValue());


        this.btnSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                AddRewardController.this.btnSaveClicked(mouseEvent);
            }
        });
    }

    private void btnSaveClicked(MouseEvent mouseEvent) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "user", "password");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO `customers_rewards`(`RewardId`, `RwdNumber`, `CustomerId`) VALUES (?, ?, ?)");
            stmt.setInt(1, (Integer)this.cbRewardId.getValue());
            stmt.setString(2, (String)this.cbRwdNumber.getValue());
            stmt.setInt(3, Integer.parseInt(this.tfCustomerId.getText()));
            int numRows = stmt.executeUpdate();
            if (numRows == 0) {
                System.out.println("update failed");
            }
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

    }

    private void getRewardIDs(Object value) {
        ArrayList rewardIdData = new ArrayList();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "user", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select distinct RewardId from customers_rewards");
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rsmd.getColumnCount());

            while (rs.next()) {
                rewardIdData.add(rs.getInt("RewardId"));
            }

            Collections.sort(rewardIdData);
            this.cbRewardId.getItems().addAll(rewardIdData);
        } catch (SQLException var6) {
            var6.printStackTrace();
        }
    }

    private void getRwdNumbers(Object rwdNumber) {
        ArrayList rwdNumData = new ArrayList();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "user", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select distinct RwdNumber from customers_rewards");
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rsmd.getColumnCount());

            while (rs.next()) {
                rwdNumData.add(rs.getString("RwdNumber"));
            }

            Collections.sort(rwdNumData);
            this.cbRwdNumber.getItems().addAll(rwdNumData);
        } catch (SQLException var6) {
            var6.printStackTrace();
        }
    }

//    public void setMainObservableList(ObservableList<CustomerRewards> data) {
//        this.mainData = data;
//    }

    public void setMainSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        //CustomerRewards cr = mainData.get(selectedIndex);

        tfCustomerId.setText(selectedIndex + "");
    }

}
