/* ************************************************************************************
 * Copyright (C) Oct 7, 2018, Natnael Alemayehu, All rights Reserved. Unauthorized 
 * copying of this file and/or distributed without, the express permission of 
 * Natnael Alemayehu is strictly prohibited. Written by Natnael Alemayehu 11:44:14 AM.
 * ************************************************************************************
 */

package gui;

import java.io.FileInputStream;

import chart.NetworkLogChart;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
import sender.Client;
import validator.ValidateInput;

public class ClientWindow extends Application {

	private Scene scene;
	private GridPane gridPane1, gridPane2;
	private ListView<String> client_log_listView, error_log_listView = new ListView<>();
	private Text header;
	private BorderPane borderPane;
	private VBox vBox_left, vBox_right, vBox_chart, vBox_command;
	private Tab command_tab, client_tab, chart_tab, error_tab;
	private Label client_ip, client_port_num, server_ip, server_port_num, timeout, packet_size, window_size;
	private TextField client_ip_input, client_port_input, server_ip_input, server_port_input, timeout_input,
			packet_size_input, window_size_input;
	private Button connt_server;
	private ImageView imageView;
	private HBox hBox;
	private TabPane tabPane;
	private ProgressBar progressbar;
	private Group group;
	private ObservableList<String> log_list = null;

	// public static void main(String[] args) {
	// launch(args);
	//
	// }

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Data Link Simulator - Client Window");

		// Creating a AnchorPane object
		borderPane = new BorderPane();
		vBox_left = new VBox();
		vBox_right = new VBox();
		hBox = new HBox();
		tabPane = new TabPane();
		tabPane.autosize();
		vBox_chart = new VBox();
		// .setMaxSize(760, 300);
		vBox_chart.autosize();
		vBox_command = new VBox();
		vBox_command.autosize();
		group = new Group();
		group.autosize();
		gridPane1 = new GridPane();
		gridPane2 = new GridPane();
		// create a progressbar
		progressbar = new ProgressBar();

		// Creating and setting the properties of Gridpane for buttons
		gridPane1.setAlignment(Pos.TOP_LEFT);
		gridPane1.setMaxSize(400, 270);
		gridPane1.setPadding(new Insets(20, 10, 10, 10));
		gridPane1.setHgap(15);
		gridPane1.setVgap(15);

		gridPane2.setAlignment(Pos.TOP_LEFT);
		gridPane2.setPadding(new Insets(40, 10, 10, 10));
		gridPane2.setHgap(15);
		gridPane2.setVgap(15);

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
		// **************************************************************************************

		timeout = new Label("Timeout interval : ");
		timeout.setStyle("-fx-font-size: 16px; " + "-fx-font-weight: bold;" + "-fx-text-alignment: center; "
				+ "-fx-font-family: \"Times New Roman\"; " + "-fx-fill: #817A68; "
				+ "-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
		packet_size = new Label("Packet size: ");
		packet_size.setStyle("-fx-font-size: 16px; " + "-fx-font-weight: bold;" + "-fx-text-alignment: center; "
				+ "-fx-font-family: \"Times New Roman\"; " + "-fx-fill: #817A68; "
				+ "-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
		window_size = new Label("Sliding window size: ");
		window_size.setStyle("-fx-font-size: 16px; " + "-fx-font-weight: bold;" + "-fx-text-alignment: center; "
				+ "-fx-font-family: \"Times New Roman\"; " + "-fx-fill: #817A68; "
				+ "-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
		connt_server = new Button("Connect");
		connt_server.setStyle("-fx-background-color: rgba(0, 148, 0, 0.9); "
				+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
				+ "-fx-text-alignment: center;" + "-fx-font-size: 16px; " + "-fx-font-weight: bold; "
				+ "-fx-fill: #806341; " + "-fx-pref-width: 200px; " + "-fx-pref-height: 30px;");

		// Add some action (in Java 8 lambda syntax style).
		connt_server.setOnAction(event -> {
			// Validate all inputs
			ValidateInput validation = new ValidateInput();
			if (validation.isValidate(client_ip_input, client_port_input, server_ip_input, server_port_input,
					timeout_input, packet_size_input, window_size_input)) {

				window_size_input.setStyle("-fx-border-color: red;");
				packet_size_input.setStyle("-fx-border-color: red;");
				timeout_input.setStyle("-fx-border-color: red;");
				server_port_input.setStyle("-fx-border-color: red;");
				server_ip_input.setStyle("-fx-border-color: red;");
				client_port_input.setStyle("-fx-border-color: red;");
				client_ip_input.setStyle("-fx-border-color: red;");

			} else {
				client_ip_input.setDisable(true);
				client_port_input.setDisable(true);
				server_ip_input.setDisable(true);
				server_port_input.setDisable(true);
				timeout_input.setDisable(true);
				packet_size_input.setDisable(true);
				window_size_input.setDisable(true);

				// Server server = new Server();
				// server.receivedData();
				Client client = new Client();
				client.sendData();
				client_ip_input.getText();
				client_ip_input.setDisable(true);
				client_port_input.getText();
				client_port_input.setDisable(true);
				server_ip_input.getText();
				server_ip_input.setDisable(true);
				server_port_input.getText();
				server_port_input.setDisable(true);
				/*
				 * Platform.runLater(new Runnable() { public void run() {
				 * client_log_listView.setItems(log_list); } });
				 */
				Thread thread = new Thread() {

					public void run() {
						
						while (true) {
							Client client = new Client();
							client.appendfListView(client_log_listView);
							
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				};
				thread.start();

				connt_server.setText("CONNECTED TO SERVER 127.0.0.1");
				connt_server.setStyle("-fx-background-color: green; "
						+ " -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 ); "
						+ " -fx-text-alignment: center; " + "-fx-font-size: 12px; " + "-fx-font-weight: bold; "
						+ "-fx-fill: #806341; " + "-fx-pref-width: 200px; " + "-fx-pref-height: 25px;");
				connt_server.setDisable(true);
				System.out.println("It works");
			}

		});

		client_ip_input = new TextField();
		client_ip_input.setStyle("-fx-border-color: grey; " + "-fx-background-color: white; " + "-fx-padding: 5px;");

		client_port_input = new TextField();
		client_port_input.setStyle("-fx-border-color: grey; " + "-fx-background-color: white; " + "-fx-padding: 5px;");

		server_ip_input = new TextField();
		server_ip_input.setPromptText(" ");
		server_ip_input.setStyle("-fx-border-color: grey; " + "-fx-background-color: white; " + "-fx-padding: 5px;");

		server_port_input = new TextField();
		server_port_input.setPromptText(" ");
		server_port_input
				.setStyle("-fx-border-color: grey; " + "-fx-background-color: transparent; " + "-fx-padding: 5px;");

		// *******************************************
		timeout_input = new TextField();
		timeout_input.setPromptText(" ");
		timeout_input
				.setStyle("-fx-border-color: grey; " + "-fx-background-color: transparent; " + "-fx-padding: 5px;");
		packet_size_input = new TextField();
		packet_size_input.setPromptText(" ");
		packet_size_input
				.setStyle("-fx-border-color: grey; " + "-fx-background-color: transparent; " + "-fx-padding: 5px;");
		window_size_input = new TextField();
		window_size_input.setPromptText(" ");
		window_size_input
				.setStyle("-fx-border-color: grey; " + "-fx-background-color: transparent; " + "-fx-padding: 5px;");

		GridPane.setConstraints(client_ip, 0, 0);
		GridPane.setConstraints(client_ip_input, 1, 0);
		GridPane.setConstraints(client_port_num, 0, 1);
		GridPane.setConstraints(client_port_input, 1, 1);

		GridPane.setConstraints(server_ip, 0, 2);
		GridPane.setConstraints(server_ip_input, 1, 2);
		GridPane.setConstraints(server_port_num, 0, 3);
		GridPane.setConstraints(server_port_input, 1, 3);
		GridPane.setConstraints(connt_server, 1, 5);

		gridPane1.getChildren().addAll(client_ip, client_ip_input, client_port_num, client_port_input, server_ip,
				server_ip_input, server_port_num, server_port_input, connt_server);
		vBox_right.getChildren().addAll(gridPane1);

		GridPane.setConstraints(timeout, 0, 0);
		GridPane.setConstraints(timeout_input, 1, 0);
		GridPane.setConstraints(packet_size, 0, 1);
		GridPane.setConstraints(packet_size_input, 1, 1);
		GridPane.setConstraints(window_size, 0, 2);
		GridPane.setConstraints(window_size_input, 1, 2);
		gridPane2.getChildren().addAll(timeout, timeout_input, packet_size, packet_size_input, window_size,
				window_size_input);
		vBox_command.getChildren().addAll(gridPane2);

		// Create the ListView for the Output
		client_log_listView = new ListView<String>();
		error_log_listView = new ListView<String>();
		client_log_listView.autosize();
		error_log_listView.autosize();

		command_tab = new Tab();
		client_tab = new Tab();
		chart_tab = new Tab();
		error_tab = new Tab();
		command_tab.setStyle("-fx-padding: 0.083333em 0.5em 0.083333em 0.5em; " + "-fx-font-size: 18px; "
				+ "-fx-text-alignment: center; " + "-fx-font-family: \"Times New Roman\"; " + "-fx-fill: #817A68;");
		client_tab.setStyle("-fx-padding: 0.083333em 0.5em 0.083333em 0.5em; " + "-fx-font-size: 18px; "
				+ "-fx-text-alignment: center; " + "-fx-font-family: \"Times New Roman\"; " + "-fx-fill: #817A68;");
		chart_tab.setStyle("-fx-padding: 0.083333em 0.5em 0.083333em 0.5em; " + "-fx-font-size: 18px; "
				+ "-fx-text-alignment: center; " + "-fx-font-family: \"Times New Roman\"; " + "-fx-fill: #817A68;");
		error_tab.setStyle("-fx-padding: 0.083333em 0.5em 0.083333em 0.5em; " + "-fx-font-size: 18px; "
				+ "-fx-text-alignment: center; " + "-fx-font-family: \"Times New Roman\"; " + "-fx-fill: #817A68;");

		command_tab.setClosable(false);
		client_tab.setClosable(false);
		chart_tab.setClosable(false);
		error_tab.setClosable(false);

		command_tab.setText("Command Prompt");
		client_tab.setText("Client File Transfer Log");
		client_tab.setContent(client_log_listView);
		tabPane.prefWidthProperty().bind(borderPane.widthProperty());
		tabPane.getSelectionModel().select(0);
		tabPane.setStyle("-fx-background-insets: 0, 1, 2; " + "-fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0; "
				+ "-fx-padding: 0.083333em 0.5em 0.083333em 0.5em; ");

		NetworkLogChart log_chart = new NetworkLogChart();

		command_tab.setContent(vBox_command);
		chart_tab.setText("Chart Data Report");
		chart_tab.setContent(log_chart.createChart(vBox_chart));
		error_tab.setText("Network Error Reports");
		error_tab.setContent(error_log_listView);

		hBox.setAlignment(Pos.CENTER);
		tabPane.getTabs().addAll(command_tab, client_tab, error_tab, chart_tab);

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
		// stage.setOnCloseRequest(e -> Platform.exit());
		// stage.setOnCloseRequest(e -> System.exit(0));
	}

	/**
	 * @return the client_log_listView
	 */
	public ListView<String> getClient_log_listView() {
		return client_log_listView;
	}

	/**
	 * @param client_log_listView
	 *            the client_log_listView to set
	 */
	public void setClient_log_listView(ListView<String> client_log_listView) {
		this.client_log_listView = client_log_listView;
	}

	/**
	 * @return the client_ip_input
	 */
	public TextField getClient_ip_input() {
		return client_ip_input;
	}

	/**
	 * @param client_ip_input
	 *            the client_ip_input to set
	 */
	public void setClient_ip_input(TextField client_ip_input) {
		this.client_ip_input = client_ip_input;
	}

	/**
	 * @return the client_port_input
	 */
	public TextField getClient_port_input() {
		return client_port_input;
	}

	/**
	 * @param client_port_input
	 *            the client_port_input to set
	 */
	public void setClient_port_input(TextField client_port_input) {
		this.client_port_input = client_port_input;
	}

	public static void main(String[] args) {

		launch(args);

	}
	


	// public void appendfListView(String logs) {
	// log_list = FXCollections.<String>observableArrayList();
	// log_list.addAll(logs);
	// }

}
