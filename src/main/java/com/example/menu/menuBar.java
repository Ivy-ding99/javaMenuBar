package com.example.menu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.*;

import java.io.IOException;

public class menuBar extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBox vPane = new VBox();
        Scene scene = new Scene(vPane, 300, 100);
        Menu fileMenu=new Menu("_File");
        Menu editMenu=new Menu("_Edit");
        Menu settingMenu=new Menu("_Setting");
        // Add the menus to a menubar and then add the menubar to the pane
        MenuBar menuBar=new MenuBar();
        menuBar.getMenus().addAll(fileMenu,editMenu,settingMenu);
        vPane.getChildren().add(menuBar);
        //set file menu
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open...");
        MenuItem closeItem = new MenuItem("Close");
        MenuItem saveAsItem = new MenuItem("Save As...");
        fileMenu.getItems().addAll(newItem, new SeparatorMenuItem(), openItem,
                closeItem, saveAsItem);
        //add accelerate
        newItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        openItem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        closeItem.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        saveAsItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        closeItem.setDisable(true);
        //add toggle group
        ToggleGroup settingGroup = new ToggleGroup();
        RadioMenuItem smallItem = new RadioMenuItem("Small");
        smallItem.setToggleGroup(settingGroup);
        RadioMenuItem mediumItem = new RadioMenuItem("Medium");
        mediumItem.setToggleGroup(settingGroup);
        RadioMenuItem largeItem = new RadioMenuItem("Large");
        largeItem.setToggleGroup(settingGroup);
        settingMenu.getItems().addAll(smallItem, mediumItem, largeItem);
    //nest menu in the edit menu
        Menu searchMenu = new Menu("Search");
        MenuItem findItem = new MenuItem("Find");
        MenuItem replaceItem = new MenuItem("Replace");
        searchMenu.getItems().addAll(findItem, replaceItem);
        CheckMenuItem gridItem = new CheckMenuItem("Use GridLines");
        MenuItem copyItem = new MenuItem("Copy");
        editMenu.getItems().addAll(gridItem, new SeparatorMenuItem(), copyItem, searchMenu);
        //add actions for menu items
        newItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                closeItem.setDisable(false);
                newItem.setDisable(true);
                openItem.setDisable(true);
                System.out.println("NEW has been pressed");
            }
        });
        openItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                closeItem.setDisable(false);
                openItem.setDisable(true);
                newItem.setDisable(true);
                System.out.println("OPEN has been pressed");
            }
        });
        closeItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                openItem.setDisable(false); newItem.setDisable(false);
                closeItem.setDisable(true);
                System.out.println("CLOSE has been pressed");
            }
        });
        stage.setTitle("Menu Example!");
        stage.setScene(scene);
        stage.show();
        ContextMenu popupMenu = new ContextMenu();
        MenuItem helpItem = new MenuItem("Help");
        MenuItem inspectItem = new MenuItem("Inspect");
        popupMenu.getItems().addAll(helpItem, inspectItem);
        vPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.SECONDARY) popupMenu.show(vPane,e.getScreenX()-50,e.getScreenY()-25);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}