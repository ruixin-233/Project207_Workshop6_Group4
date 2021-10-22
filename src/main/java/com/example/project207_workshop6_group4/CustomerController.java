/**
 * Sample Skeleton for 'customer-list.fxml' Controller Class
 */

package com.example.project207_workshop6_group4;

import java.net.ConnectException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.*;

public class CustomerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tvCustomer"
    private TableView<Customer> tvCustomer; // Value injected by FXMLLoader

    @FXML // fx:id="colCustomerId"
    private TableColumn<Customer, Integer> colCustomerId; // Value injected by FXMLLoader

    @FXML // fx:id="colCustFirstName"
    private TableColumn<Customer, String> colCustFirstName; // Value injected by FXMLLoader

    @FXML // fx:id="colCustLastName"
    private TableColumn<Customer, String> colCustLastName; // Value injected by FXMLLoader

    @FXML // fx:id="colCustAddress"
    private TableColumn<Customer, String> colCustAddress; // Value injected by FXMLLoader

    @FXML // fx:id="colCustCity"
    private TableColumn<Customer, String> colCustCity; // Value injected by FXMLLoader

    @FXML // fx:id="colCustProv"
    private TableColumn<Customer, String> colCustProv; // Value injected by FXMLLoader

    @FXML // fx:id="colCustPostal"
    private TableColumn<Customer, String> colCustPostal; // Value injected by FXMLLoader

    @FXML // fx:id="colCustCountry"
    private TableColumn<Customer, String> colCustCountry; // Value injected by FXMLLoader

    @FXML // fx:id="colCustBusPhone"
    private TableColumn<Customer, String> colCustBusPhone; // Value injected by FXMLLoader

    @FXML // fx:id="colCustEmail"
    private TableColumn<Customer, String> colCustEmail; // Value injected by FXMLLoader

    @FXML // fx:id="colAgentId"
    private TableColumn<Customer, Integer> colAgentId; // Value injected by FXMLLoader

    private ObservableList<Customer> data = FXCollections.observableArrayList();

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert colCustomerId != null : "fx:id=\"colCustomerId\" was not injected: check your FXML file 'customer-list.fxml'.";
        assert colCustFirstName != null : "fx:id=\"colCustFirstName\" was not injected: check your FXML file 'customer-list.fxml'.";
        assert colCustLastName != null : "fx:id=\"colCustLastName\" was not injected: check your FXML file 'customer-list.fxml'.";
        assert colCustAddress != null : "fx:id=\"colCustAddress\" was not injected: check your FXML file 'customer-list.fxml'.";
        assert colCustCity != null : "fx:id=\"colCustCity\" was not injected: check your FXML file 'customer-list.fxml'.";
        assert colCustProv != null : "fx:id=\"colCustProv\" was not injected: check your FXML file 'customer-list.fxml'.";
        assert colCustPostal != null : "fx:id=\"colCustPostal\" was not injected: check your FXML file 'customer-list.fxml'.";
        assert colCustCountry != null : "fx:id=\"colCustCountry\" was not injected: check your FXML file 'customer-list.fxml'.";
        assert colCustBusPhone != null : "fx:id=\"colCustBusPhone\" was not injected: check your FXML file 'customer-list.fxml'.";
        assert colCustEmail != null : "fx:id=\"colCustEmail\" was not injected: check your FXML file 'customer-list.fxml'.";
        assert colAgentId != null : "fx:id=\"colAgentId\" was not injected: check your FXML file 'customer-list.fxml'.";

        getCustomers();

    }

    private void getCustomers() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "user", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customers");
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rsmd.getColumnCount());

            while (rs.next())
            {
                data.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12)));

                colCustomerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
                colCustFirstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("custFirstName"));
                colCustLastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("custLastName"));
                colCustAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("custAddress"));
                colCustCity.setCellValueFactory(new PropertyValueFactory<Customer, String>("custCity"));
                colCustProv.setCellValueFactory(new PropertyValueFactory<Customer, String>("custProv"));
                colCustPostal.setCellValueFactory(new PropertyValueFactory<Customer, String>("custPostal"));
                colCustCountry.setCellValueFactory(new PropertyValueFactory<Customer, String>("custCountry"));
                colCustBusPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("custBusPhone"));
                colCustEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("custEmail"));
                colAgentId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("AgentId"));
                tvCustomer.setItems(data);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
