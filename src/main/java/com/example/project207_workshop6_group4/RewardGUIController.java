

package com.example.project207_workshop6_group4;

import com.example.project207_workshop6_group4.Data.Reward;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RewardGUIController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnSaveReward"
    private Button btnSaveReward; // Value injected by FXMLLoader

    @FXML // fx:id="comboRwdNumber"
    private ComboBox<String> comboRwdNumber; // Value injected by FXMLLoader

    @FXML // fx:id="txtRwdDesc"
    private TextField txtRwdDesc; // Value injected by FXMLLoader

    @FXML // fx:id="txtRwdSponsor"
    private TextField txtRwdSponsor; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddReward"
    private Button btnAddReward; // Value injected by FXMLLoader

    @FXML // fx:id="comboSponsorID"
    private ComboBox<Integer> comboSponsorID; // Value injected by FXMLLoader

    @FXML // fx:id="btnEditReward"
    private Button btnEditReward; // Value injected by FXMLLoader

    @FXML
    private Button btnSponsorTable; // Value injected by FXMLLoader

    // Creating an Array List for the Reward Object data
    private ObservableList<Reward> rewardData = FXCollections.observableArrayList();

    // Creating an Array List for the Reward Number combo box
    private ObservableList<String> dataRwdNumber = FXCollections.observableArrayList();

    // Creating an Array List for the Sponsor ID combo box
    private ObservableList<Integer> dataSponsorID = FXCollections.observableArrayList();


    // Laura Edited
    // Added sponsor button to retrieve sponsor list
    @FXML
    void btnSponsorTableClicked(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(getClass().getResource("reward-sponsor.fxml"));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This is the onEditButtonClick method that handles the mouse click event for the Edit Button on the Reward GUI.
     * @param event - mouse click event
     */
    @FXML
    void onEditButtonClick(MouseEvent event) {
        // disable the Edit Button
        btnEditReward.setDisable(true);

        // disable the Add Button
        btnAddReward.setDisable(true);

        // enable the text fields to allow user to change content
        txtRwdDesc.setEditable(true);

        // enable the Sponsor ID combo box
        comboSponsorID.setDisable(false);

        // disable the Reward Number combo box
        comboRwdNumber.setDisable(true);

        // enable the Save Button
        btnSaveReward.setDisable(false);
    }

    /**
     * This is the onSaveButtonClick method that handles the mouse click event for the Save Button on the Reward GUI.
     * @param event - mouse click event
     */
    @FXML
    void onSaveButtonClick(MouseEvent event) {
        // disable the Save Button
        btnSaveReward.setDisable(true);

        // enable the Edit Button
        btnEditReward.setDisable(false);

        // enable the Add Button
        btnAddReward.setDisable(false);

        // disable the text fields so the information cannot be changed
        txtRwdDesc.setEditable(false);

        // disable the Sponsor ID combo box
        comboSponsorID.setDisable(true);

        // enable the Rewards Number combo box
        comboRwdNumber.setDisable(false);

        // Update information in the Travel Experts database
        String rewardDesc = txtRwdDesc.getText();
        int sponsorId = comboSponsorID.getValue();
        String rewardNum = comboRwdNumber.getValue();

        // Setting up the values to open the database connection from file input
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/travelexperts-2";

//        try{
//            FileInputStream fsIn = new FileInputStream("c:\\connection.properties");
//            Properties p = new Properties();
//            p.load(fsIn);
//            username = (String) p.get("user");
//            password = (String) p.get("password");
//            url = (String) p.get("URL");
//        } catch (IOException e){
//            e.printStackTrace();
//        }

        // Opening the database connection
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            // SQL Query Statements to get data from the TravelExperts database
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Rewards SET RwdDesc = ?," +
                    " RwdSponsorId = ? WHERE RwdNumber = ?");

            pstmt.setString(1, rewardDesc);
            pstmt.setInt(2, sponsorId);
            pstmt.setString(3, rewardNum);

            // execute the Update SQL query statement
            pstmt.executeUpdate();

            // Close the database connection
            pstmt.close();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        // reload the updated information in the GUI
        getRewardData();
    }

    /**
     * This is the onAddButtonClick method that handles the mouse click event for the Add Button on the Reward GUI.
     * This method opens a new GUI window to allow user to input new Reward data into the database.
     * @param event - mouse click event
     */
    @FXML
    void onAddButtonClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-reward-view.fxml"));
            Stage secondWindow = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            secondWindow.setTitle("Add Reward");
            secondWindow.setScene(scene);
            secondWindow.show();
        } catch (Exception e){
            System.out.println("Can't load new window");
        }

        // Close current window
        Stage stage = (Stage) btnAddReward.getScene().getWindow();
        stage.close();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnSaveReward != null : "fx:id=\"btnSaveReward\" was not injected: check your FXML file 'reward-gui-view.fxml'.";
        assert comboRwdNumber != null : "fx:id=\"comboRwdNumber\" was not injected: check your FXML file 'reward-gui-view.fxml'.";
        assert txtRwdDesc != null : "fx:id=\"txtRwdDesc\" was not injected: check your FXML file 'reward-gui-view.fxml'.";
        assert comboSponsorID != null : "fx:id=\"comboSponsorID\" was not injected: check your FXML file 'reward-gui-view.fxml'.";
        assert txtRwdSponsor != null : "fx:id=\"txtRwdSponsor\" was not injected: check your FXML file 'reward-gui-view.fxml'.";
        assert btnEditReward != null : "fx:id=\"btnEditReward\" was not injected: check your FXML file 'reward-gui-view.fxml'.";
        assert btnAddReward != null : "fx:id=\"btnAddReward\" was not injected: check your FXML file 'reward-gui-view.fxml'.";

        // Setting up the starting state for the Rewards GUI
        btnSaveReward.setDisable(true); // disable the save button

        // Get the Rewards data from the TravelExperts database
        getRewardData();

        // Disable the text fields so the information cannot be changed
        txtRwdDesc.setEditable(false);
        txtRwdSponsor.setEditable(false);

        // Disable the Sponsor ID combo box
        comboSponsorID.setDisable(true);

        // Creating a listener for the Reward Number combo box
        comboRwdNumber.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                rewardChanged(observableValue, oldValue, newValue);
            }
        });

        // Creating a listener for the Sponsor Id combo box
        comboSponsorID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer oldInteger, Integer newInteger) {
                sponsorChanged(observableValue, oldInteger, newInteger);
            }
        });
    }

    // This method deals with the change event for the Sponsor ID combo box
    private void sponsorChanged(ObservableValue<? extends Integer> observableValue, Integer oldInteger, Integer newInteger){
        // Update the Sponsor Name text field
        txtRwdSponsor.setText(getSponsorData(newInteger));
    }

    /*
     * The agentChanged method listens to the Agent ID combo box and reacts when the selected item changes.
     * @param observableValue - the array list integer value
     * @param oldValue - the previously selected item in the combo box
     * @param newValue - the newly selected item in the combo box
     */
    private void rewardChanged(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
        // Update the text fields with the new Reward data
        int i = 0;
        Reward newReward = rewardData.get(i);

        while(i < rewardData.size()){
            Reward temp = rewardData.get(i);
            if (temp.getRwdNumber().equals(newValue)){
                newReward = temp;
                i++;
            }
            else{
                i++;
            }
        }
        // Update the text fields with the new Reward data
        txtRwdDesc.setText(newReward.getRwdDescription());

        // Update the Sponsor ID combo box
        comboSponsorID.setValue(newReward.getRwdSponsorId());

        // Update the Sponsor name text field
        String sponsorName = getSponsorData(newReward.getRwdSponsorId());
        txtRwdSponsor.setText(sponsorName);
    }

    /**
     * This is the getRewardData which loads the Reward data from the Travel Experts database.
     * The RwdNumber values are loaded into the RewardNumber combo box in this method.
     * The RwdSponsorId values are loaded into the SponsorID combo box in this method.
     */
    private void getRewardData(){
        // Setting up the database connection
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/travelexperts-2";

        // Opening the database connection
        try{
            Connection conn = DriverManager.getConnection(url, username, password);

            // SQL Query Statements to get data from the TravelExperts database
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Rewards");

            // Getting the Reward data from the database
            while(rs.next()){
                rewardData.add(new Reward(rs.getString(1), rs.getString(2),
                        rs.getInt(3)));

                // Link the information to the combo boxes
                dataRwdNumber.add(rs.getString(1));
            }

            // Display the data in the Reward Number combo box
            comboRwdNumber.setItems(dataRwdNumber);

            // Getting the Sponsor ID data from the database
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery("select SponsorId from Sponsors");

            while(rs2.next()){
                dataSponsorID.add(rs2.getInt(1));
            }

            // Display the data in the Agency ID combo box
            comboSponsorID.setItems(dataSponsorID);

            // Closing the database connection
            stmt.close();
            stmt2.close();
            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * This is the getSponsorData which loads the Sponsor data from the Travel Experts database.
     * The Sponsor Name values are loaded into the Reward Sponsor text box in this method.
     */
    private String getSponsorData(int sponsorID){
        // Creating a variable to hold the Sponsor Name
        String sponsorName = "";

        // Setting up the database connection
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/travelexperts-2";

        // Opening the database connection
        try{
            Connection conn = DriverManager.getConnection(url, username, password);

            // SQL Query Statements to get data from the TravelExperts database
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select SponsorName from Sponsors where SponsorId = " + sponsorID);

            // Getting the Reward data from the database
            while(rs.next()){
                sponsorName = rs.getString(1);
            }

            // Closing the database connection
            stmt.close();
            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return sponsorName;
    }
}
