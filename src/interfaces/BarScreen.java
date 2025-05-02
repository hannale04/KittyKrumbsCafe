package interfaces;

import java.util.ArrayList;

import java.util.Map;

import database.Inventory;
import gameobjects.GameTimer;
import gameobjects.MenuItem;
import gameobjects.Order;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BarScreen {
	private Stage primaryStage = new Stage();
	private BorderPane barLayout = new BorderPane();
	private Inventory inventory = new Inventory();
	private int numItems;
	private ImageView barScreenImageView;
	private VBox orderVBox;
	private VBox recipeVBox;
	private HBox ingredientsBar = new HBox(10);
	private ListView<MenuItem> food = new ListView();
	private ListView<MenuItem> drink = new ListView();
	private ListView<MenuItem> special = new ListView();
	private Order order;
	private ArrayList<Integer> orderIds = new ArrayList<>();
	private ArrayList<Integer> selectedIds = new ArrayList<>();	
	private Button timerButton = new Button("Finished");
	private GameTimer gameTimer = new GameTimer(45, timerButton);
	private MainScreen mainScreen;
	
	public BarScreen(Order order, MainScreen mainScreen) {
		this.order = order;
		this.mainScreen = mainScreen;
		orderIds = order.getOrderIds();
		inventory.shuffle();
		food.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		drink.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		special.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		Text header = new Text("Kitty Krumbs Kitchen");
		HBox headerHBox = new HBox(header);
		header.setFont(new Font("Arial Black", 40));
		headerHBox.setAlignment(Pos.CENTER);
		createMenuItems();
		createListeners();
		addNodesToBarLayout(headerHBox);
		showScreen();
		gameTimer.startTimer();
	}
	
	
	private Button createImageButton(String text, String imagePath, double width, double height) {
		Image image = new Image(getClass().getResourceAsStream(imagePath));
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		return new Button(text, imageView);
	}
	
	private void addNodesToBarLayout(HBox headerHBox) {
		HBox items = new HBox(food, drink, special);
		showOrder();
		VBox timerVBox = new VBox(gameTimer, timerButton);
		timerVBox.setAlignment(Pos.CENTER);
		barLayout.setTop(headerHBox);
		barLayout.setCenter(items);
		barLayout.setLeft(timerVBox);
		barLayout.setBottom(orderVBox);
	}
	private void showOrder() {
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
	}
	
	private void createListeners() {
		timerButton.setOnAction(e -> endOrder());
	}
	
	private void endOrder() {
		gameTimer.stopTimer();
		//Capture food items selected
		ObservableList<MenuItem> selectedFoodItems = food.getSelectionModel().getSelectedItems();
        for (MenuItem item : selectedFoodItems) {
        	selectedIds.add(item.getId());
            System.out.println(item.getName());
        }
        
        //Capture drink items selected
        ObservableList<MenuItem> selectedDrinkItems = drink.getSelectionModel().getSelectedItems();
        for (MenuItem item : selectedDrinkItems) {
        	selectedIds.add(item.getId());
            System.out.println(item.getName());
        }
        
        //Capture special items selected
        ObservableList<MenuItem> selectedSpecialItems = special.getSelectionModel().getSelectedItems();
        for (MenuItem item : selectedSpecialItems) {
        	selectedIds.add(item.getId());
            System.out.println(item.getName());
        }
		System.out.println("Items selected: " + selectedIds);
		checkResults();
	}


	private void checkResults() {
		int numCorrect = 0;
		boolean allCorrect = false;
		int coinsEarned = 0;
		ArrayList<String> missedItems = new ArrayList<>();
		
		for (int i = 0; i < orderIds.size(); i++) {
			if(selectedIds.contains(orderIds.get(i))) {
				numCorrect = numCorrect + 1;
				coinsEarned = coinsEarned + order.getOrder().get(i).getPrice();
			}
			else {
				missedItems.add(order.getOrder().get(i).getName());
			}
		}
		if(numCorrect == orderIds.size()) {
			allCorrect = true;
		}
		mainScreen.returnFromBar(missedItems, allCorrect, coinsEarned);
		primaryStage.close();
	}


	private void createMenuItems() {
		food.getItems().addAll(inventory.getFood());
		drink.getItems().addAll(inventory.getDrinks());
		special.getItems().addAll(inventory.getSpecials());
	}

	private void showScreen() {
		Scene scene = new Scene(barLayout, 1200, 800);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bar");
		primaryStage.show();
	}
	
}