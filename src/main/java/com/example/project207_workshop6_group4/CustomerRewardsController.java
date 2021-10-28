package com.example.project207_workshop6_group4;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import com.example.project207_workshop6_group4.Data.CustomerRewards;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CustomerRewardsController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAddReward"
    private Button btnAddReward; // Value injected by FXMLLoader


    @FXML // fx:id="tbvCustomerRewards"
    private TableView<CustomerRewards> tbvCustomerRewards; // Value injected by FXMLLoader

    @FXML // fx:id="colCustomerId"
    private TableColumn<CustomerRewards, Integer> colCustomerId; // Value injected by FXMLLoader

    @FXML // fx:id="colRewardId"
    private TableColumn<CustomerRewards, String> colRewardId; // Value injected by FXMLLoader

    @FXML // fx:id="colRwdNumber"
    private TableColumn<CustomerRewards, String> colRwdNumber; // Value injected by FXMLLoader

    @FXML // fx:id="btnSponsorsTable"
    private Button btnSponsorsTable; // Value injected by FXMLLoader

    @FXML // fx:id="btnCustomersInfo"
    private Button btnCustomersTable; // Value injected by FXMLLoader

    @FXML // fx:id="btnCustomerDetails"
    private Button btnCustomerDetails; // Value injected by FXMLLoader

    @FXML // fx:id="lblAlert"
    private Label lblAlert; // Value injected by FXMLLoader

    private ObservableList<CustomerRewards> data = FXCollections.observableArrayList();

    @FXML // fx:id="cbCustomerId"
    private ComboBox<?> cbCustomerId; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAddReward != null : "fx:id=\"btnAddReward\" was not injected: check your FXML file 'CustomerRewards.fxml'.";
        assert tbvCustomerRewards != null : "fx:id=\"tbvCustomerRewards\" was not injected: check your FXML file 'CustomerRewards.fxml'.";
        assert colCustomerId != null : "fx:id=\"colCustomerId\" was not injected: check your FXML file 'CustomerRewards.fxml'.";
        assert colRewardId != null : "fx:id=\"colRewardId\" was not injected: check your FXML file 'CustomerRewards.fxml'.";
        assert colRwdNumber != null : "fx:id=\"colRwdNumber\" was not injected: check your FXML file 'CustomerRewards.fxml'.";
        assert cbCustomerId != null : "fx:id=\"cbCustomerId\" was not injected: check your FXML file 'CustomerRewards.fxml'.";
        assert btnCustomerDetails != null : "fx:id=\"btnCustomerDetails\" was not injected: check your FXML file 'customer-rewards.fxml'.";
        assert btnCustomersTable != null : "fx:id=\"btnCustomersInfo\" was not injected: check your FXML file 'customer-rewards.fxml'.";
        assert lblAlert != null : "fx:id=\"lblAlert\" was not injected: check your FXML file 'customer-rewards.fxml'.";

        //loadCustomer((Integer) CustomerRewardsController.this.cbCustomerId.getValue());

        this.getCustomerIDs();

        this.cbCustomerId.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                CustomerRewardsController.this.loadCustomer();
            }
        });

        this.btnAddReward.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //Parent root;
                if (cbCustomerId.getSelectionModel().isEmpty() == true || cbCustomerId.getSelectionModel().getSelectedItem().equals("All"))
                {
                    // Add alert message when "All" or nothing is selected - Laura
                    lblAlert.setText("Please select a customer");
                }
                else {
                    lblAlert.setText("");
                    try {
                        onOpenDialog((int) cbCustomerId.getValue());
                    /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddReward.fxml"));
                    AddRewardController addRewardController = fxmlLoader.<AddRewardController>getController();
                    addRewardController.setMainObservableList(data);
                    addRewardController.setMainSelectedIndex(selectedIndex);
                    Stage stage = new Stage();
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setTitle("Add Reward");
                    stage.setScene(scene);
                    stage.show();
                    // Hide this current window (if this is what you want)
                    //((Node)(event.getSource())).getScene().getWindow().hide();*/
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    // Laura
    @FXML
    void btnCustomerDetails(MouseEvent event) {
        if (cbCustomerId.getSelectionModel().isEmpty() == true || cbCustomerId.getSelectionModel().getSelectedItem().equals("All"))
        {
            // Add alert message when "All" or nothing is selected - Laura
            lblAlert.setText("Please select a customer");
        }
        else {
            lblAlert.setText("");
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer-details.fxml"));
                Parent parent = fxmlLoader.load();
                CustomerDetailController dialogController = fxmlLoader.<CustomerDetailController>getController();
                dialogController.setCboMainSelectedIndex((int) cbCustomerId.getValue());
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Laura
    @FXML
    void btnCustomersTableClicked(MouseEvent event) {
        try {
            FXMLLoader fmxLoader = new FXMLLoader(getClass().getResource("customer-list.fxml"));
            Parent parent = null;
            parent = fmxLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSponsorsTableClicked(MouseEvent event) {
        try {
            FXMLLoader fmxLoader = new FXMLLoader(getClass().getResource(""));
            Parent parent = null;
            parent = fmxLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomer() {
/*        String username = "";
        String password = "";
        String url = "";
        try {
            FileInputStream fis = new FileInputStream("c:\\connection.properties");
            Properties p = new Properties();
            p.load(fis);
            username = (String) p.get("user");
            password = (String) p.get("password");
            url = (String) p.get("URL");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            //change name of database to travelexperts!!!
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts-2", "user", "password");
            //Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs;
            if (cbCustomerId.getSelectionModel().getSelectedItem().equals("All")) {
                rs = stmt.executeQuery("select CustomerID, r.RwdNumber, RwdDesc  from Customers_Rewards cr JOIN Rewards r ON cr.RwdNumber = r.RwdNumber");
            }
            else{
                rs = stmt.executeQuery("select CustomerID, r.RwdNumber, RwdDesc  from Customers_Rewards cr JOIN Rewards r ON cr.RwdNumber = r.RwdNumber where customerId = " + cbCustomerId.getSelectionModel().getSelectedItem());
            }
            //ResultSetMetaData rsmd = rs.getMetaData();
            tbvCustomerRewards.getItems().clear();
            while (rs.next()) {
                data.add(new CustomerRewards(rs.getInt(1), rs.getString(2), rs.getString(3)));
                colCustomerId.setCellValueFactory(new PropertyValueFactory<CustomerRewards, Integer>("customerId"));
                colRewardId.setCellValueFactory((new PropertyValueFactory<CustomerRewards, String>("rwdDesc")));    // Rewards Table updated: RewardID field is removed - Laura
                colRwdNumber.setCellValueFactory(new PropertyValueFactory<CustomerRewards, String>("rwdNumber"));
                tbvCustomerRewards.setItems(data);

            }
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void getCustomerIDs() {
        ArrayList custData = new ArrayList();
        ArrayList all = new ArrayList();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts-2", "user", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select CustomerId from customers"); // Add all existing customer id to combobox - Laura
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                custData.add(rs.getInt("CustomerId"));
            }

            all.add("All");
            Collections.sort(custData);
            this.cbCustomerId.getItems().addAll(all);
            this.cbCustomerId.getItems().addAll(custData);
        } catch (SQLException var6) {
            var6.printStackTrace();
        }
    }


    private void onOpenDialog(int selectedIndex) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-reward-to-customer.fxml"));
        Parent parent = fxmlLoader.load();
        AddRewardtoCustomerController dialogController = fxmlLoader.<AddRewardtoCustomerController>getController();
        //dialogController.setMainObservableList(data);
        dialogController.setMainSelectedIndex(selectedIndex);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}


