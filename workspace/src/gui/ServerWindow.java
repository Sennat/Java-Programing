/* ************************************************************************************
 * Copyright (C) Oct 11, 2018, Natnael Alemayehu, All rights Reserved. Unauthorized 
 * copying of this file and/or distributed without, the express permission of 
 * Natnael Alemayehu is strictly prohibited. Written by Natnael Alemayehu 1:41:22 AM.
 * ************************************************************************************
 */

package gui;

import java.io.FileInputStream;

import chart.NetworkLogChart;
import driver.Simulator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ServerWindow extends Application {

	private Scene scene;
	private GridPane gridPane;
	private TextArea client_log_textArea, server_log_textArea, error_log_textArea;
	private Text header;
	private BorderPane borderPane;
	private VBox vBox_left, vBox_right, chart_vBox;
	private Tab command_tab, client_tab, server_tab, chart_tab, error_tab;
	private Label client_ip, client_port_num, server_ip, server_port_num;
	private TextField client_ip_input, client_port_input, server_ip_input, server_port_input;
	private Button connt_server;
	private ImageView imageView;
	private HBox hBox;
	private TabPane tabPane;
	private Group group;
	private ProgressBar progressbar;

	// public static void main(String[] args) {
	// launch(args);
	//
	// }

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Data Link Simulator - Server Window");

		// Creating a AnchorPane object
		borderPane = new BorderPane();
		vBox_left = new VBox();
		vBox_right = new VBox();
		hBox = new HBox();
		tabPane = new TabPane();
		tabPane.autosize();
		chart_vBox = new VBox();
		chart_vBox.autosize();
		group = new Group();
		group.autosize();
		gridPane = new GridPane();
		progressbar = new ProgressBar();
		
		progressbar.autosize();
		progressbar.setPadding(new Insets(10, 10, 10, 10));

		// Creating and setting the properties of Gridpane for buttons
		gridPane.setAlignment(Pos.TOP_LEFT);
		gridPane.setMaxSize(400, 270);
		gridPane.setPadding(new Insets(20, 10, 10, 10));
		gridPane.setHgap(15);
		gridPane.setVgap(15);
		// gridPane.setStyle("-fx-background-color: #D2D2D1; -fx-border-radius: 10;
		// -fx-background-insets: 50.0 50.0 50.0 50.0;");

		// Set the Style-properties of the BorderPane
		borderPane.setStyle("-fx-padding: 10; " + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-radius: 5; " + "-fx-border-color: blue;");

		FileInputStream inputstream = new FileInputStream("images/server.png");
		imageView = new ImageView(new Image(inputstream));
		imageView.setSmooth(true);
		vBox_left.getChildren().add(imageView);

		vBox_left.setSpacing(10);
		vBox_left.setMinSize(400, 300);
		vBox_left.setPadding(new Insets(30, 10, 10, 30));
		vBox_left.setStyle("-fx-background-color: transparent; " + "-fx-border-radius: 10; "
				+ "-fx-background-insets: 50.0 50.0 50.0 50.0;");

		vBox_right.setSpacing(10);
		vBox_right.setMaxSize(400, 290);
		vBox_right.setPadding(new Insets(20, 10, 10, 10));
		vBox_right.setStyle("-fx-background-color: #ffffff; " + "-fx-border-radius: 10; "
				+ "-fx-border-style: solid inside;" + "-fx-border-width: 0;");
		// + "-fx-effect: innershadow(three-pass-box, gray, 1 , 0.5, 1, 1);
		// + "-fx-background-insets: 40.0 15.0 65.0 10.0;"

		// hBox.setOnShowing(10);
		hBox.setMinSize(400, 230);
		hBox.setStyle("-fx-background-color: #ffffff; " + "-fx-border-radius: 10; "
				+ "-fx-background-insets: 50.0 50.0 50.0 50.0; ");

		// Creating a Text object
		header = new Text("Data-Link Protocol File Transfer Simulation");
		header.setStyle("-fx-font-size: 25px; " + "-fx-fill: #818181; " + "-fx-font-weight: bold; "
				+ "-fx-font-family: \"Times New Roman\"; " + "-fx-text-alignment: center; "
				+ "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 )");
		client_ip = new Label("Client Name/IP: ");
		client_ip.setStyle("-fx-font-size: 16px; " + "-fx-font-weight: bold;" + "-fx-text-alignment: center; "
				+ "-fx-font-family: \"Times New Roman\"; " + "-fx-fill: #817A68; "
				+ "-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
		client_port_num = new Label("Client Port #: ");
		client_port_num.setStyle("-fx-font-size: 16px; " + "-fx-font-weight: bold;" + "-fx-text-alignment: center; "
				+ "-fx-font-family: \"Times New Roman\"; " + "-fx-fill: #817A68; "
				+ "-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
		server_ip = new Label("Server Name/IP: ");
		server_ip.setStyle("-fx-font-size: 16px; " + "-fx-font-weight: bold;" + "-fx-text-alignment: center; "
				+ "-fx-font-family: \"Times New Roman\"; " + "-fx-fill: #817A68; "
				+ "-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
		server_port_num = new Label("Server Port #: ");
		server_port_num.setStyle("-fx-font-size: 16px; " + "-fx-font-weight: bold;" + "-fx-text-alignment: center; "
				+ "-fx-font-family: \"Times New Roman\"; " + "-fx-fill: #817A68; "
				+ "-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
		connt_server = new Button("Connect");
		connt_server.setStyle("-fx-background-color: rgba(0, 148, 0, 0.9); "
				+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
				+ "-fx-text-alignment: center;" + "-fx-font-size: 16px; " + "-fx-font-weight: bold; "
				+ "-fx-fill: #806341; " + "-fx-pref-width: 200px; " + "-fx-pref-height: 30px;");

		// Add some action (in Java 8 lambda syntax style).
		connt_server.setOnAction(event -> {
			server_ip_input.getText();
			server_port_input.getText();
			client_log_textArea.setText(server_ip_input.getText());
			server_log_textArea.setText(server_port_input.getText());
			server_ip_input.setDisable(true);
			server_port_input.setDisable(true);
			connt_server.setText("DISCONNECT");
			connt_server.setStyle("-fx-background-color: rgba(209, 0, 0, 0.9); "
					+ " -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 ); "
					+ " -fx-text-alignment: center; " + "-fx-font-size: 16px; " + "-fx-font-weight: bold; "
					+ "-fx-fill: #806341; " + "-fx-pref-width: 200px; " + "-fx-pref-height: 25px;");
			connt_server.setDisable(true);
			System.out.println("It works");

		});

		client_ip_input = new TextField("127.0.0.1");
		client_ip_input.setDisable(true);
		client_ip_input.setStyle("-fx-border-color: grey; " + "-fx-background-color: white; " + "-fx-padding: 5px;");

		client_port_input = new TextField("421");
		client_port_input.setDisable(true);
		client_port_input.setStyle("-fx-border-color: grey; " + "-fx-background-color: white; " + "-fx-padding: 5px;");

		server_ip_input = new TextField();
		server_ip_input.setPromptText("IP address");
		server_ip_input.setStyle("-fx-border-color: grey; " + "-fx-background-color: white; " + "-fx-padding: 5px;");

		server_port_input = new TextField();
		server_port_input.setPromptText("Port number");
		server_port_input
				.setStyle("-fx-border-color: grey; " + "-fx-background-color: transparent; " + "-fx-padding: 5px;");

		GridPane.setConstraints(client_ip, 0, 0);
		GridPane.setConstraints(client_ip_input, 1, 0);
		GridPane.setConstraints(client_port_num, 0, 1);
		GridPane.setConstraints(client_port_input, 1, 1);

		GridPane.setConstraints(server_ip, 0, 2);
		GridPane.setConstraints(server_ip_input, 1, 2);
		GridPane.setConstraints(server_port_num, 0, 3);
		GridPane.setConstraints(server_port_input, 1, 3);
		
		Simulator simulater = new Simulator();
		GridPane.setConstraints(simulater.updateStatusBar(), 1, 4);
		GridPane.setConstraints(connt_server, 1, 5);

		gridPane.getChildren().addAll(client_ip, client_ip_input, client_port_num, client_port_input, server_ip,
				server_ip_input, server_port_num, server_port_input, simulater.updateStatusBar(), connt_server);
		vBox_right.getChildren().addAll(gridPane);

		// Create the TextArea for the Output
		client_log_textArea = new TextArea();
		server_log_textArea = new TextArea();
		error_log_textArea = new TextArea();
		client_log_textArea.autosize();
		server_log_textArea.autosize();
		error_log_textArea.autosize();

		command_tab = new Tab();
		client_tab = new Tab();
		server_tab = new Tab();
		chart_tab = new Tab();
		error_tab = new Tab();
		command_tab.setStyle("-fx-padding: 0.083333em 0.5em 0.083333em 0.5em; " + "-fx-font-size: 18px; "
				+ "-fx-text-alignment: center; " + "-fx-font-family: \"Times New Roman\"; " + "-fx-fill: #817A68;");
		client_tab.setStyle("-fx-padding: 0.083333em 0.5em 0.083333em 0.5em; " + "-fx-font-size: 18px; "
				+ "-fx-text-alignment: center; " + "-fx-font-family: \"Times New Roman\"; " + "-fx-fill: #817A68;");
		server_tab.setStyle("-fx-padding: 0.083333em 0.5em 0.083333em 0.5em; " + "-fx-font-size: 18px; "
				+ "-fx-text-alignment: center; " + "-fx-font-family: \"Times New Roman\"; " + "-fx-fill: #817A68;");
		chart_tab.setStyle("-fx-padding: 0.083333em 0.5em 0.083333em 0.5em; " + "-fx-font-size: 18px; "
				+ "-fx-text-alignment: center; " + "-fx-font-family: \"Times New Roman\"; " + "-fx-fill: #817A68;");
		error_tab.setStyle("-fx-padding: 0.083333em 0.5em 0.083333em 0.5em; " + "-fx-font-size: 18px; "
				+ "-fx-text-alignment: center; " + "-fx-font-family: \"Times New Roman\"; " + "-fx-fill: #817A68;");

		command_tab.setClosable(false);
		client_tab.setClosable(false);
		server_tab.setClosable(false);
		chart_tab.setClosable(false);
		error_tab.setClosable(false);

		command_tab.setText("Command Prompt");
		client_tab.setText("Client File Transfer Log");
		client_tab.setContent(client_log_textArea);
		tabPane.prefWidthProperty().bind(borderPane.widthProperty());
		tabPane.getSelectionModel().select(0);
		tabPane.setStyle("-fx-background-insets: 0, 1, 2; " + "-fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0; "
				+ "-fx-padding: 0.083333em 0.5em 0.083333em 0.5em; ");

		server_tab.setText("Server File Transfer Log");
		server_tab.setContent(server_log_textArea);

		NetworkLogChart log_chart = new NetworkLogChart();

		chart_tab.setText("Chart Data Report");
		chart_tab.setContent(log_chart.createChart(chart_vBox));
		error_tab.setText("Network Error Reports");
		error_tab.setContent(error_log_textArea);

		hBox.setAlignment(Pos.CENTER);
		tabPane.getTabs().addAll(command_tab, client_tab, server_tab, error_tab, chart_tab);

		borderPane.setTop(header);
		borderPane.setLeft(vBox_left);
		borderPane.setRight(vBox_right);
		borderPane.setBottom(tabPane);

		// ObservableList<BorderPane> contents = FXCollections.observableArrayList();

		// Set the header to the center of the top
		BorderPane.setAlignment(header, Pos.TOP_CENTER);
		// Set the vBox_left to the left
		BorderPane.setAlignment(vBox_left, Pos.CENTER_LEFT);
		// Set the vBox_right to the right
		BorderPane.setAlignment(vBox_right, Pos.CENTER_RIGHT);
		// Set the textArea the bottom center
		BorderPane.setAlignment(hBox, Pos.BOTTOM_LEFT);
		// Set the textArea the bottom center
		// BorderPane.setAlignment(group, Pos.BOTTOM_RIGHT);

		stage.setScene(new Scene(borderPane, 1000, 620));
		stage.centerOnScreen();
		stage.setResizable(false);
		stage.show();
		stage.setOnCloseRequest((WindowEvent e) -> {
			Platform.exit();
			System.exit(0);
		});
	}

	public static void main(String[] args) {

		launch(args);

	}

}