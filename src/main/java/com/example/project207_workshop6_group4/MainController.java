/**
 * Author: Laura Luo
 * Date: Oct 18, 2021
 * Project 207 - WorkShop6
 */

package com.example.project207_workshop6_group4;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    @FXML
    void btnCustomersClicked(MouseEvent event) {
        try {
            OpenDialog("customer-list.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnCustomersPressed(KeyEvent event) {
        try {
            OpenDialog("customer-list.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnRewardsClicked(MouseEvent event) {
        try {
            OpenDialog("reward-gui-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void btnRewardsPressed(KeyEvent event) {
        try {
            OpenDialog("reward-gui-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSponsorsClicked(MouseEvent event) {
        try {
            OpenDialog("customer-rewards.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSponsorsPressed(KeyEvent event) {
        try {
            OpenDialog("customer-rewards.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void OpenDialog(String fxml) throws IOException {
        FXMLLoader fmxLoader = new FXMLLoader(getClass().getResource(fxml));
        Parent parent = fmxLoader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
