package interfaces;

import java.util.ArrayList;

import database.Inventory;
import gameobjects.GameTimer;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainScreen {
	private Stage primaryStage = new Stage();
	private Pane mainLayout = new Pane();
	private Inventory inventory = new Inventory();
	private int numItems;
	private ImageView mainScreenImageView;
	private ImageView customerBubble;
	private Text customerTalk;
	private ImageView serverBubble;
	private Text serverTalk;
	private Button btCustomer;
	private Button btTakeOrder;
	private VBox orderVBox;
	private Order order;
	Button btGoToBar = new Button("Go to Bar");
	
	//These are our animation control buttons
	private Button serverWelcome = new Button(); //Server welcomes customer upon game start
	
	
	public MainScreen() {		
		createMainScreen();
		createOtherScreenObjects();
		//createListeners();
		addNodesToMainLayout();
		showScreen();
		
		createActionListeners();
		GameTimer gameTimer = new GameTimer(2, serverWelcome);
		gameTimer.startTimer();
	}

	private void createMainScreen() {
		Image mainscreenImage = new Image("file:images/background.png");
		mainScreenImageView = new ImageView(mainscreenImage);
		
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(mainscreenImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, backgroundSize);
		mainLayout.setBackground(new Background(backgroundImage));	
		
	}	

	private void createOtherScreenObjects() {
		//createDisplayedBank();
		numItems = 3;
		createCustomer();
		createBtTakeOrder();
		createSpeechBubbles();
	}
	
	private void createCustomer() {
		btCustomer = new Button("Customer");
		btCustomer.setPrefSize(200,50);
		btCustomer.setLayoutX(800);
		btCustomer.setLayoutY(600);	
	}
	
	private void createBtTakeOrder() {
		btTakeOrder = new Button("Take order");
		btTakeOrder.setPrefSize(200, 75);
		btTakeOrder.setLayoutX(75);
		btTakeOrder.setLayoutY(150);
		btTakeOrder.setOnAction(e -> {
			createOrder();
			btTakeOrder.setVisible(false);
			btGoToBar.setVisible(true);
		});
	}
	 
	private void createOrder() {
		showServerBubble(false);
		order = new Order(numItems, inventory);
		customerTalk.setText("I would like\n" + order.toString());
		showCustomerBubble(true);
		
//		Label orderLabel = new Label(order.toString());
//		orderLabel.setStyle("-fx-font-size: 20px;");
		
		//orderVBox.getChildren().add(orderLabel);
		createGoToBarButton();
	}
	
	private void createSpeechBubbles() {
		Image customerBubbleImage = new Image("file:images/customer_bubble.png");
		customerBubble = new ImageView(customerBubbleImage);
		  customerBubble.setLayoutX(50);
		  customerBubble.setLayoutY(300);
		  customerBubble.setFitWidth(400);
		  customerBubble.setFitHeight(300);
		  customerTalk = new Text("Customer talking");
		  customerTalk.setFont(new Font("Arial Black", 24));
		  customerTalk.setLayoutX(50);
		  customerTalk.setLayoutY(300);
		  
		//Server bubble  
		Image serverBubbleImage = new Image("file:images/server_bubble.png");
		serverBubble = new ImageView(serverBubbleImage);
		  serverBubble.setLayoutX(750);
		  serverBubble.setLayoutY(40);
		  serverBubble.setFitWidth(350);
		  serverBubble.setFitHeight(250);
		  serverTalk = new Text("Server talking");
		  serverTalk.setFont(new Font("Arial Black", 24));
		  serverTalk.setLayoutX(780);
		  serverTalk.setLayoutY(110);
		  
		  showServerBubble(false);
		  showCustomerBubble(false);
	}
	
	private void showServerBubble(boolean show) {
		serverBubble.setVisible(show);
		serverTalk.setVisible(show);
	}
	
	private void showCustomerBubble(boolean show) {
		customerBubble.setVisible(show);
		customerTalk.setVisible(show);
	}
	
	private void createActionListeners() {
		serverWelcome.setOnAction( e -> {
			serverTalk.setText("Hi! I'm Kitty.\nMay I help you?");
			showServerBubble(true);
			GameTimer gameTimer = new GameTimer(2, btTakeOrder);
			gameTimer.startTimer();
		});
	}
	
	private void createGoToBarButton() {
	    btGoToBar.setPrefSize(200, 50);
	    btGoToBar.setOnAction(e -> {
	    	try {
	    		new BarScreen(order, this);
	    	} catch (Exception ex) {
	    		ex.printStackTrace();
	    	}
	    });
	    btGoToBar.setVisible(false);
	}
	
	public void returnFromBar(ArrayList<String> missedItems, boolean allCorrect, int coinsEarned) {
		System.out.println("All correct: " + allCorrect + "Coins earned: " + coinsEarned);
	}

	private void addNodesToMainLayout() {
		mainLayout.getChildren().addAll(btTakeOrder, customerBubble, customerTalk, serverBubble, serverTalk, btGoToBar);
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