package interfaces;

import interfaces.KitchenScreen;

import java.util.ArrayList;

import gameobjects.GameTimer;
import gameobjects.Order;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PostOrderScreen {
	private Pane postOrderScreenLayout = new Pane();
	private Stage primaryStage;
	private Order order;
	private KitchenScreen kitchenScreen;
	private ImageView bgImageView;
	private ImageView bubbleImageView;
	private Text customerResponse;
	private Button backToKitchenBt;
	private ArrayList<String> missedItems;
	
	//These are our animation control buttons
	private Button customerResponseBt = new Button();

	
	public PostOrderScreen(Order order, KitchenScreen kitchenScreen, Stage primaryStage) {
		this.order = order;
		this.kitchenScreen = kitchenScreen;
		this.primaryStage = primaryStage;
		this.missedItems = kitchenScreen.getMissedItems();
		createPostOrderScreen();
		createCustomerSpeechBubble();
		createBackToKitchenBt();
		displayCorrectCustomer();

		addNodesToPostOrderScreenLayout();
		showScreen();
	}
	
	private void createPostOrderScreen(){
		postOrderScreenLayout = new Pane();
		//Create background image
		bgImageView = new ImageView();
		bgImageView.setFitWidth(1200);
	    bgImageView.setFitHeight(800);
	}
	
	private void displayCorrectCustomer() {
		boolean allCorrect = kitchenScreen.getOrderStatus();
		System.out.println("Missed items: " + missedItems);
		this.missedItems = kitchenScreen.getMissedItems();


		if (allCorrect) {
			bgImageView.setImage(new Image("file:images/happy_customer_bg.png"));
			customerResponse.setText("Yay! Everything was perfect. Thank you so much!!");
		
			javafx.animation.PauseTransition delay = new javafx.animation.PauseTransition(Duration.seconds(2.5));
			delay.setOnFinished(e -> {
				try {
					new MainScreen();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
			delay.play();
		
		} else {
			bgImageView.setImage(new Image("file:images/unhappy_customer_bg.png"));
			customerResponse.setText(buildComplaint());
			backToKitchenBt.setVisible(true);
		}
	}
	
	private String buildComplaint() {
		//Chatgpt
		if (missedItems == null || missedItems.isEmpty()) {
			return "Where's my order?!";
		}

		StringBuilder complaint = new StringBuilder("Where's my ");

		for (int i = 0; i < missedItems.size(); i++) {
			complaint.append(missedItems.get(i));
			
			if (i < missedItems.size() - 2) {
				// For all except last two
				complaint.append(", ");
			} else if (i == missedItems.size() - 2) {
				// Before last item
				complaint.append(" and ");
			}
		}

		complaint.append("?!");
		return complaint.toString();
	}
	
	private void createCustomerSpeechBubble() {
		//Create customer speech bubble image
		Image bubble = new Image("file:images/server_bubble.png");
		bubbleImageView = new ImageView(bubble);
		bubbleImageView.setFitWidth(500);
		bubbleImageView.setFitHeight(250);
		bubbleImageView.setLayoutX(600);
		bubbleImageView.setLayoutY(200);
		
		customerResponse = new Text();
		customerResponse.setLayoutX(735);
		customerResponse.setLayoutY(300);
		customerResponse.setWrappingWidth(400);
		customerResponse.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 24));
		customerResponse.setFill(Color.SADDLEBROWN);
	}
	
	private void createBackToKitchenBt(){
		backToKitchenBt = new Button("BACK TO KITCHEN");
		backToKitchenBt.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 36));
		backToKitchenBt.setStyle("-fx-background-color: transparent; -fx-text-fill: Saddlebrown; -fx-font-weight: bold;");
		backToKitchenBt.setPrefSize(200, 75);
		backToKitchenBt.setMinSize(200, 75);
		backToKitchenBt.setMaxSize(200, 75);
		backToKitchenBt.setLayoutX(952);
		backToKitchenBt.setLayoutY(680);
		backToKitchenBt.setContentDisplay(ContentDisplay.CENTER);
		backToKitchenBt.setVisible(false);

		backToKitchenBt.setOnAction(e -> {
	    	try {
	    		kitchenScreen.showScreen();
	    	} catch (Exception ex) {
	    		ex.printStackTrace();
	    	}
	    });  
	}
	
	
	private void addNodesToPostOrderScreenLayout() {
		postOrderScreenLayout.getChildren().addAll(bgImageView, bubbleImageView, customerResponse, customerResponseBt, backToKitchenBt);
	}
	
	private void showBubble(boolean show) {
		bubbleImageView.setVisible(show);
		customerResponse.setVisible(show);
	}
	
	private void showScreen() {
		Scene scene = new Scene(postOrderScreenLayout, 1200, 800);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Kitty Krumbs Café: Order Results");
		primaryStage.show();
	}
	
}
