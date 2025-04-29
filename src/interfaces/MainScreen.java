package interfaces;

import database.Inventory;
import gameobjects.Order;
import interfaces.BarScreen;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScreen extends Application {
	private Stage primaryStage;
	private Pane mainLayout = new Pane();
	private Inventory inventory = new Inventory();
	private int numItems;
	private ImageView mainScreenImageView;
	private Button btCustomer;
	private Button btTakeOrder;
	private VBox orderVBox;
		
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		createMainScreen();
		createOtherScreenObjects();
		//createListeners();
		addNodesToMainLayout();
		showScreen();
	}

	private void createMainScreen() {
		Image mainscreenImage = new Image("file:C:\\Users\\hanna\\Downloads\\temp_mainscreen_image.jpg");
		mainScreenImageView = new ImageView(mainscreenImage);
		
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(mainscreenImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, backgroundSize);
		mainLayout.setBackground(new Background(backgroundImage));	
	}	

	private void createOtherScreenObjects() {
		//createResourceBar();
		numItems = 3;
		createCustomer();
		createBtTakeOrder();
	}
	
	private void createCustomer() {
		btCustomer = new Button("Customer");
		btCustomer.setPrefSize(200,50);
		btCustomer.setLayoutX(800);
		btCustomer.setLayoutY(600);	
	}
	
	private void createBtTakeOrder() {
		btTakeOrder = new Button("Take order");
		btTakeOrder.setPrefSize(350, 200);
		btTakeOrder.setLayoutX(700);
		btTakeOrder.setLayoutY(400);
		
		btTakeOrder.setOnAction(e -> {
			createOrder();
			mainLayout.getChildren().remove(btTakeOrder);
			mainLayout.getChildren().add(orderVBox);
		});
	}
	 
	private void createOrder() {
		Order order = new Order(numItems, inventory);
		orderVBox = new VBox();
		orderVBox.setPrefWidth(400);
		orderVBox.setPrefHeight(250);
		orderVBox.setLayoutX(700);
		orderVBox.setLayoutY(400);
		orderVBox.setSpacing(20);
		orderVBox.setStyle("-fx-background-color: beige; -fx-padding: 30; -fx-border-color: white;");
		
		Label orderLabel = new Label(order.toString());
		orderLabel.setStyle("-fx-font-size: 20px;");
		
		orderVBox.getChildren().add(orderLabel);
		createGoToBarButton();
	}
	
	private void createGoToBarButton() {
		Button btGoToBar = new Button("Go to Bar");
	    btGoToBar.setPrefSize(200, 50);
	    btGoToBar.setOnAction(e -> {
	    	BarScreen barScreen = new BarScreen();
	    	try {
	    		barScreen.start(primaryStage);
	    	} catch (Exception ex) {
	    		ex.printStackTrace();
	    	}
	    });
	    
	    orderVBox.getChildren().add(btGoToBar);
	}

	private void addNodesToMainLayout() {
		mainLayout.getChildren().addAll(btCustomer, btTakeOrder);
	}
	
	/*
	private void createListeners() {
	
	}
	 */
	
	private void showScreen() {
		Scene scene = new Scene(mainLayout, 1200, 800);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Kitty Krumbs Café");
		primaryStage.show();
	}
	
}