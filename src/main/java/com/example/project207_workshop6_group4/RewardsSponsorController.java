package com.example.project207_workshop6_group4;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.project207_workshop6_group4.Data.RewardSponsor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
public class RewardsSponsorController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tvSponsors"
    private TableView<RewardSponsor> tvSponsors; // Value injected by FXMLLoader

    @FXML // fx:id="colSponsorsId"
    private TableColumn<RewardSponsor, Integer> colSponsorsId; // Value injected by FXMLLoader

    @FXML // fx:id="colSponsorName"
    private TableColumn<RewardSponsor, String> colSponsorName; // Value injected by FXMLLoader

    private ObservableList <RewardSponsor> data = FXCollections.observableArrayList();

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        assert tvSponsors != null : "fx:id=\"tvSponsors\" was not injected: check your FXML file 'RewardSponsor.fxml'.";
        assert colSponsorsId != null : "fx:id=\"colSponsorsId\" was not injected: check your FXML file 'RewardSponsor.fxml'.";
        assert colSponsorName != null : "fx:id=\"colSponsorName\" was not injected: check your FXML file 'RewardSponsor.fxml'.";
        getSponsors();
    }
    private void getSponsors() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts-2", "root", "");
            //Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select SponsorId, SponsorName from Sponsors");
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rsmd.getColumnCount());
            while (rs.next())
            {
                data.add(new RewardSponsor(rs.getInt(1), rs.getString(2)));
                colSponsorsId.setCellValueFactory(new PropertyValueFactory<RewardSponsor, Integer>("SponsorId"));
                colSponsorName.setCellValueFactory(new PropertyValueFactory<RewardSponsor, String>("SponsorName"));

                tvSponsors.setItems(data);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

