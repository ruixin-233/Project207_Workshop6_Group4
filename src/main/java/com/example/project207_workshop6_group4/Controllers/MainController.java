/**
 * Sample Skeleton for 'main-menu.fxml' Controller Class
 */

package com.example.project207_workshop6_group4.Controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class MainController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCustomers"
    private JFXButton btnCustomers; // Value injected by FXMLLoader

    @FXML // fx:id="btnSponsors"
    private JFXButton btnSponsors; // Value injected by FXMLLoader

    @FXML // fx:id="btnRewards"
    private JFXButton btnRewards; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCustomers != null : "fx:id=\"btnCustomers\" was not injected: check your FXML file 'main-menu.fxml'.";
        assert btnSponsors != null : "fx:id=\"btnSponsors\" was not injected: check your FXML file 'main-menu.fxml'.";
        assert btnRewards != null : "fx:id=\"btnRewards\" was not injected: check your FXML file 'main-menu.fxml'.";

    }
}
