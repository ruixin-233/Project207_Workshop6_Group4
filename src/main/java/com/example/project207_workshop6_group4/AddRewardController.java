

package com.example.project207_workshop6_group4;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/* Proj 207 - Travel Experts Threaded Project
 * Author: Veronica Hoffman
 * Date: October 21, 2021
 *
 * This is the add-reward-view.fxml Controller Class which controls the Add Reward GUI.
 * This controller class also interacts with the Travel Experts Database.
 */

public class AddRewardController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnSaveRewardAdd"
    private Button btnSaveRewardAdd; // Value injected by FXMLLoader

    @FXML // fx:id="txtRwdNumberAdd"
    private TextField txtRwdNumberAdd; // Value injected by FXMLLoader

    @FXML // fx:id="txtRwdDescAdd"c
    private TextField txtRwdDescAdd; // Value injected by FXMLLoader

    @FXML // fx:id="comboRwdSponsorIDAdd"
    private ComboBox<Integer> comboRwdSponsorIDAdd; // Value injected by FXMLLoader

    @FXML // fx:id="txtRwdSponsorAdd"
    private TextField txtRwdSponsorAdd; // Value injected by FXMLLoader

    @FXML // fx:id="btnCancelRewardAdd"
    private Button btnCancelRewardAdd; // Value injected by FXMLLoader

    // Creating an Array List for the Sponsor ID combo box
    private ObservableList<Integer> dataRwdSponsorID = FXCollections.observableArrayList();

    // Creating an Array List for the Reward Number values
    private ObservableList<String> dataRwdNum = FXCollections.observableArrayList();

    /**
     * This method handles the mouse click event for the Cancel Button on the Add reward GUI.
     *
     * @param event - mouse click event
     */
    @FXML
    void onCancelAddButtonClick(MouseEvent event) {
        // Call the Reward GUI Manager again
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reward-gui-view.fxml"));
            Stage secondWindow = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            secondWindow.setTitle("Reward Manager");
            secondWindow.setScene(scene);
            secondWindow.show();
        } catch (Exception e) {
            System.out.println("Can't load new window");
            System.out.println("Error: " + e);
        }

        // Close this window when button is pressed
        Stage stage = (Stage) btnCancelRewardAdd.getScene().getWindow();
        stage.close();
    }

    /**
     * This method handles the mouse click event for the Save Button on the Add reward GUI.
     *
     * @param event - mouse click event
     */
    @FXML
    void onSaveAddButtonClick(MouseEvent event) {
        // Creating variables to check input validation
        boolean isValid = true;
        String rewardNum = "";
        String rewardDesc = "";
        int sponsorID = 0;

        // Check if Sponsor ID has been selected
        if (comboRwdSponsorIDAdd.getSelectionModel().isEmpty()) {
            isValid = false;
        }

        // Get the information from the GUI text fields
        if (isValid == true) {
            rewardNum = txtRwdNumberAdd.getText();
            rewardDesc = txtRwdDescAdd.getText();
            sponsorID = comboRwdSponsorIDAdd.getValue();
        }

        // Check if Reward Number value is empty
        if (rewardNum.equals("")) {
            isValid = false;
        }

        // Setting up the values to open the database connection from file input
        String username = "user";
        String password = "password";
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

            // Need to validate the RewardNumber value to make sure it isn't already in the database
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select RwdNumber from Rewards");

            while (rs.next()) {
                dataRwdNum.add(rs.getString(1));
            }

            // checking the user value against the values already in the database
            if (isValid == true) {
                int i = 0;

                while (i < dataRwdNum.size()) {
                    String test = dataRwdNum.get(i);
                    if (test.equals(rewardNum)) {
                        isValid = false;
                        i++;
                    } else {
                        i++;
                    }
                }
            }

            if (isValid == true) {
                // SQL Query Statements to get data from the TravelExperts database
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Rewards (RwdNumber, RwdDesc, RwdSponsorId)" +
                        " VALUES (?, ?, ?)");

                pstmt.setString(1, rewardNum);
                pstmt.setString(2, rewardDesc);
                pstmt.setInt(3, sponsorID);

                // execute the Update SQL query statement
                pstmt.executeUpdate();

                // Close the database connection
                pstmt.close();
                conn.close();
            } else {
                Alert inputAlert = new Alert(Alert.AlertType.ERROR);
                inputAlert.setTitle("Invaild Reward Input");
                inputAlert.setHeaderText("User input is invalid");
                inputAlert.setContentText("Either Reward Number is already in use or value was not provided, " +
                        "or Sponsor ID was not selected. Please enter a unique Reward Number and select a Sponsor ID");
                inputAlert.show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (isValid == true) {
            // Call the Reward GUI Manager again
            try {
                /*final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();*/

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reward-gui-view.fxml"));
                Stage secondWindow = new Stage();
                Scene scene = new Scene(fxmlLoader.load(), 600, 500);
                secondWindow.setTitle("Reward Manager");
                secondWindow.setScene(scene);
                secondWindow.show();
            } catch (Exception e) {
                System.out.println("Can't load new window");
                System.out.println("Error: " + e);
            }

            // Close the Add Reward GUI
            Stage stage = (Stage) btnSaveRewardAdd.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnSaveRewardAdd != null : "fx:id=\"btnSaveRewardAdd\" was not injected: check your FXML file 'add-reward-view.fxml'.";
        assert txtRwdNumberAdd != null : "fx:id=\"txtRwdNumberAdd\" was not injected: check your FXML file 'add-reward-view.fxml'.";
        assert txtRwdDescAdd != null : "fx:id=\"txtRwdDescAdd\" was not injected: check your FXML file 'add-reward-view.fxml'.";
        assert comboRwdSponsorIDAdd != null : "fx:id=\"comboRwdSponsorIDAdd\" was not injected: check your FXML file 'add-reward-view.fxml'.";
        assert txtRwdSponsorAdd != null : "fx:id=\"txtRwdSponsorAdd\" was not injected: check your FXML file 'add-reward-view.fxml'.";
        assert btnCancelRewardAdd != null : "fx:id=\"btnCancelRewardAdd\" was not injected: check your FXML file 'add-reward-view.fxml'.";

        // Getting the Sponsor data to display in the Sponsor text field and combo box
        getSponsorData();

        // Creating a listener for the Sponsor ID combo box
        comboRwdSponsorIDAdd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer oldInteger, Integer newInteger) {
                sponsorChanged(observableValue, oldInteger, newInteger);
            }
        });
    }

    /**
     * This is the getSponsorData which loads the Sponsor data from the Travel Experts database.
     * The SponsorID values are loaded into the Sponsor ID combo box in this method.
     */
    private void getSponsorData() {
        // Setting up the database connection
        String username = "user";
        String password = "password";
        String url = "jdbc:mysql://localhost:3306/travelexperts-2";

        // Opening the database connection
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            // SQL Query Statements to get data from the TravelExperts database
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select SponsorId from Sponsors");

            while (rs.next()) {
                dataRwdSponsorID.add(rs.getInt(1));
            }

            // Display the data in the Agency ID combo box
            comboRwdSponsorIDAdd.setItems(dataRwdSponsorID);

            // Closing the database connection
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void sponsorChanged(ObservableValue<? extends Integer> observableValue, Integer oldInt, Integer newInt) {
        // Update the Sponsor Name text field
        txtRwdSponsorAdd.setText(getSponsorName(newInt));
    }

    private String getSponsorName(int sponsorID) {
        // Creating a variable to hold the Sponsor Name
        String sponsorName = "";

        // Setting up the values to open the database connection from file input
        String username = "user";
        String password = "password";
        String url = "jdbc:mysql://localhost:3306/travelexperts-2";

        // Opening the database connection
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            // SQL Query Statements to get data from the TravelExperts database
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select SponsorName from Sponsors where SponsorId = " + sponsorID);

            // Getting the Reward data from the database
            while (rs.next()) {
                sponsorName = rs.getString(1);
            }

            // Closing the database connection
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sponsorName;
    }
}