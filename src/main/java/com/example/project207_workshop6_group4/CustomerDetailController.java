package com.example.project207_workshop6_group4;

import com.example.project207_workshop6_group4.Data.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CustomerDetailController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    @FXML // fx:id="btnEdit"
    private Button btnEdit; // Value injected by FXMLLoader

    @FXML // fx:id="cbCustomerID"
    private ComboBox<Integer> cbCustomerID; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustFirstName"
    private TextField tfCustFirstName; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustLastName"
    private TextField tfCustLastName; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustAddress"
    private TextField tfCustAddress; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustCity"
    private TextField tfCustCity; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustProv"
    private TextField tfCustProv; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustPostal"
    private TextField tfCustPostal; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustCountry"
    private TextField tfCustCountry; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustHomePhone"
    private TextField tfCustHomePhone; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustBusPhone"
    private TextField tfCustBusPhone; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustEmail"
    private TextField tfCustEmail; // Value injected by FXMLLoader

    @FXML // fx:id="tfAgentId"
    private TextField tfAgentId; // Value injected by FXMLLoader

    private ObservableList<Customer> mainData;
    private ObservableList customerId;
    private int selectedIndex;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'editagentdialog.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'editagentdialog.fxml'.";
        assert cbCustomerID != null : "fx:id=\"cbCustomerID\" was not injected: check your FXML file 'editagentdialog.fxml'.";
        assert tfCustFirstName != null : "fx:id=\"tfCustFirstName\" was not injected: check your FXML file 'editagentdialog.fxml'.";
        assert tfCustLastName != null : "fx:id=\"tfCustLastName\" was not injected: check your FXML file 'editagentdialog.fxml'.";
        assert tfCustAddress != null : "fx:id=\"tfCustAddress\" was not injected: check your FXML file 'editagentdialog.fxml'.";
        assert tfCustCity != null : "fx:id=\"tfCustCity\" was not injected: check your FXML file 'editagentdialog.fxml'.";
        assert tfCustProv != null : "fx:id=\"tfCustProv\" was not injected: check your FXML file 'editagentdialog.fxml'.";
        assert tfCustPostal != null : "fx:id=\"tfCustPostal\" was not injected: check your FXML file 'editagentdialog.fxml'.";
        assert tfCustCountry != null : "fx:id=\"tfCustCountry\" was not injected: check your FXML file 'editagentdialog.fxml'.";
        assert tfCustHomePhone != null : "fx:id=\"tfCustHomePhone\" was not injected: check your FXML file 'editagentdialog.fxml'.";
        assert tfCustBusPhone != null : "fx:id=\"tfCustBusPhone\" was not injected: check your FXML file 'editagentdialog.fxml'.";
        assert tfCustEmail != null : "fx:id=\"tfCustEmail\" was not injected: check your FXML file 'editagentdialog.fxml'.";
        assert tfAgentId != null : "fx:id=\"tfAgentId\" was not injected: check your FXML file 'editagentdialog.fxml'.";

        //textFields = new ArrayList<>(Arrays.asList(tfCustFirstName, tfCustLastName, tfCustAddress, tfCustCity, tfCustProv, tfCustPostal, tfCustCountry ,tfCustHomePhone, tfCustBusPhone, tfCustEmail, tfAgentId));
        displayCustomerId();
    }

    private void displayCustomerInfo() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "user", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Customers where CustomerId = " + cbCustomerID.getSelectionModel().getSelectedItem());
            if (rs.next()){
                tfCustFirstName.setText(rs.getString(("CustFirstName")));
                tfCustLastName.setText(rs.getString(("CustLastName")));
                tfCustAddress.setText(rs.getString(("CustAddress")));
                tfCustCity.setText(rs.getString(("CustCity")));
                tfCustProv.setText(rs.getString(("CustProv")));
                tfCustPostal.setText(rs.getString(("CustPostal")));
                tfCustCountry.setText(rs.getString(("CustCountry")));
                tfCustHomePhone.setText(rs.getString(("CustHomePhone")));
                tfCustBusPhone.setText(rs.getString(("CustBusPhone")));
                tfCustEmail.setText(rs.getString(("CustEmail")));
                tfAgentId.setText(rs.getInt("AgentId") + "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onCbCustomerClicked(ActionEvent event) {
        displayCustomerInfo();
    }

    private void displayCustomerId() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "user", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select CustomerId from Customers order by CustomerId asc");

            customerId = FXCollections.observableArrayList();

            while (rs.next()){
                customerId.add(rs.getInt(1));
            }

            cbCustomerID.setItems(customerId);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMainObservableList(ObservableList<Customer> data) {
        this.mainData = data;
    }

    public void setMainSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        Customer c = mainData.get(selectedIndex);

        cbCustomerID.setValue(c.getCustomerId());
        tfCustFirstName.setText(c.getCustFirstName());
        tfCustLastName.setText(c.getCustLastName());
        tfCustAddress.setText(c.getCustAddress());
        tfCustCity.setText(c.getCustCity());
        tfCustProv.setText(c.getCustProv());
        tfCustPostal.setText(c.getCustPostal());
        tfCustCountry.setText(c.getCustCountry());
        tfCustHomePhone.setText(c.getCustHomePhone());
        tfCustBusPhone.setText(c.getCustBusPhone());
        tfCustEmail.setText(c.getCustEmail());
        tfAgentId.setText(c.getAgentId() + "");
    }

    // Retrieve selected index from customer-rewards.fxml drop down list (Customer Reward Controller)
    public void setCboMainSelectedIndex(int value) {
        this.selectedIndex = value;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "user", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Customers where CustomerId = " + selectedIndex);
            if (rs.next()){
                cbCustomerID.setValue(selectedIndex);
                tfCustFirstName.setText(rs.getString(("CustFirstName")));
                tfCustLastName.setText(rs.getString(("CustLastName")));
                tfCustAddress.setText(rs.getString(("CustAddress")));
                tfCustCity.setText(rs.getString(("CustCity")));
                tfCustProv.setText(rs.getString(("CustProv")));
                tfCustPostal.setText(rs.getString(("CustPostal")));
                tfCustCountry.setText(rs.getString(("CustCountry")));
                tfCustHomePhone.setText(rs.getString(("CustHomePhone")));
                tfCustBusPhone.setText(rs.getString(("CustBusPhone")));
                tfCustEmail.setText(rs.getString(("CustEmail")));
                tfAgentId.setText(rs.getInt("AgentId") + "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
