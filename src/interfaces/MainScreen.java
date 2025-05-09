package interfaces;

import java.util.ArrayList;
import database.Inventory;
import gameobjects.GameTimer;
import gameobjects.Order;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class MainScreen {
	private Stage primaryStage = new Stage();
	private Pane mainLayout = new Pane();
	private Inventory inventory = new Inventory();
	private ImageView serverBubble;
	private Text serverTalk;
	private TextFlow serverTalkBox; //Talk box
	private ImageView customerBubble;
	private Text customerTalk;
	private TextFlow customerTalkBox; //Talk box
	private Button btTakeOrder;
	private Order order;
	private int numItems;
	private ImageView kitchenBtImageView; //ImageView for "Go to Kitchen"
	private Button btGoToKitchen; //Button for "Go to Kitchen"
	
	//These are our animation control buttons
	private Button serverWelcome = new Button(); //Server welcomes customer upon game start
	private Button customerOrder = new Button(); //Customer places an order
	private Button serverResponse = new Button(); //Server responds to customer order
	
	public MainScreen() {		
		createMainScreen();
		createOtherScreenObjects();
		createActionListeners();
		addNodesToMainLayout();
		showScreen();
		
		showBtTakeOrder(false);
		showBtGoToKitchen(false);
		
		GameTimer gameTimer = new GameTimer(1, serverWelcome);
		gameTimer.startTimer();
	}

	private void createMainScreen() {
		Image mainscreenImage = new Image("file:images/mainscreen.png");
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(mainscreenImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, backgroundSize);
		mainLayout.setBackground(new Background(backgroundImage));	
	}	

	private void createOtherScreenObjects() {
		createBtTakeOrder();
		createSpeechBubbles();
		createGoToKitchenButton();
	}
	
	private void createBtTakeOrder() {
		btTakeOrder = new Button("Take order");
		btTakeOrder.setPrefSize(200, 75);
		btTakeOrder.setLayoutX(75);
		btTakeOrder.setLayoutY(150);
		btTakeOrder.setOnAction(e -> {
			createOrder();
			btTakeOrder.setVisible(false);
		});
	}
	
	private void createOrder() {
		showServerBubble(false);
		order = new Order(inventory);
		customerTalk.setText("Hi! May I please get...\n" + "\n" + order.toString());
		showCustomerBubble(true);
	}
	
	private void createSpeechBubbles() {
		//Server bubble  
		Image serverBubbleImage = new Image("file:images/server_bubble.png");
			serverBubble = new ImageView(serverBubbleImage);
			serverBubble.setLayoutX(350);
			serverBubble.setLayoutY(40);
			serverBubble.setFitWidth(550);
			serverBubble.setFitHeight(250);
			
			serverTalk = new Text("Server talking");
			serverTalk.setFont(Font.font("Comic Sans MS",  FontWeight.BOLD, 24));
			serverTalk.setFill(Color.web("Saddlebrown"));
			serverTalk.setLayoutX(470);
			serverTalk.setLayoutY(140);
			
		//Customer bubble
		Image customerBubbleImage = new Image("file:images/customer_bubble.png");
			customerBubble = new ImageView(customerBubbleImage);
			customerBubble.setLayoutX(7);
			customerBubble.setLayoutY(250);
			customerBubble.setFitWidth(500);
			customerBubble.setFitHeight(450);
			
			customerTalk = new Text("Customer talking");
			customerTalk.setFont(Font.font("Comic Sans MS",  FontWeight.BOLD, 24));
			customerTalk.setFill(Color.web("Saddlebrown"));
			
			customerTalk.setLayoutX(100);
			customerTalk.setLayoutY(365);
			
			showServerBubble(false);
			showCustomerBubble(false);
	}
	
	private void createActionListeners() {
		serverWelcome.setOnAction( e -> {
			serverTalk.setText("Hi, I'm Kitty! \nWhat can I get for you today?");
			showServerBubble(true);
			showBtTakeOrder(false);
			GameTimer gameTimer = new GameTimer(3, customerOrder);
			gameTimer.startTimer();
		});
		
		customerOrder.setOnAction(e -> {
			showServerBubble(false);
			createOrder();
			
			GameTimer timer = new GameTimer(3, serverResponse);
			timer.startTimer();
		});
		
		serverResponse.setOnAction( e -> {
			showServerBubble(true);
			serverTalk.setText("        Coming right up!");
			showBtGoToKitchen(true);
			showBtTakeOrder(false);
		});
	}
	
	private void createGoToKitchenButton() {
		//Create image
		Image kitchenBtImage = new Image("file:images/button2.png");
	    kitchenBtImageView = new ImageView(kitchenBtImage);
		kitchenBtImageView.setFitWidth(200);
		kitchenBtImageView.setPreserveRatio(true);  
		kitchenBtImageView.setLayoutX(950);
		kitchenBtImageView.setLayoutY(595);
		kitchenBtImageView.setVisible(false);
		
		DropShadow glow = new DropShadow();
		glow.setColor(Color.WHITE);
		glow.setOffsetX(0);
		glow.setOffsetY(0);
		glow.setRadius(30);
		glow.setSpread(0.6); 
		
		kitchenBtImageView.setEffect(glow);
		
		//Create button that goes on top of image
		btGoToKitchen = new Button("GO TO KITCHEN");
		btGoToKitchen.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 36));
		btGoToKitchen.setStyle("-fx-background-color: transparent; -fx-text-fill: Saddlebrown; -fx-font-weight: bold;");
		btGoToKitchen.setPrefSize(200, 75);
		btGoToKitchen.setMinSize(200, 75);
		btGoToKitchen.setMaxSize(200, 75);
		btGoToKitchen.setLayoutX(952);
		btGoToKitchen.setLayoutY(680);
		btGoToKitchen.setContentDisplay(ContentDisplay.CENTER);
		btGoToKitchen.setVisible(false);

	    btGoToKitchen.setOnAction(e -> {
	    	try {
	    		new KitchenScreen(order, this, primaryStage);
	    	} catch (Exception ex) {
	    		ex.printStackTrace();
	    	}
	    });  
	    btGoToKitchen.setVisible(false);	
	}
	
	public void returnFromKitchen(ArrayList<String> missedItems, boolean allCorrect, int coinsEarned) {
		System.out.println("All correct: " + allCorrect + "\nCoins earned: " + coinsEarned);
	}
	
	//Speech bubbles / button visibility
	private void showServerBubble(boolean show) {
		serverBubble.setVisible(show);
		serverTalk.setVisible(show);
	}
	
	private void showCustomerBubble(boolean show) {
		customerBubble.setVisible(show);
		customerTalk.setVisible(show);
	}
	
	private void showBtTakeOrder(boolean show) {
		btTakeOrder.setVisible(show);
	}
	
	private void showBtGoToKitchen(boolean show) {
		btGoToKitchen.setVisible(show);
		kitchenBtImageView.setVisible(show);
	}

	private void addNodesToMainLayout() {
		mainLayout.getChildren().addAll(serverBubble, serverTalk, customerBubble, customerTalk, btTakeOrder, kitchenBtImageView, btGoToKitchen);
	}
	
	private void showScreen() {
		Scene scene = new Scene(mainLayout, 1200, 800);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Kitty Krumbs Café");
		primaryStage.show();
	}

}