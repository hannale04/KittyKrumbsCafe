package interfaces;

import java.util.ArrayList;

import database.Inventory;
import gameobjects.GameTimer;
import gameobjects.MenuItem;
import gameobjects.Order;
import interfaces.PostOrderScreen;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class KitchenScreen {
	private Scene kitchenScene;

	private Stage primaryStage;
	private BorderPane kitchenLayout = new BorderPane();
	private Inventory inventory = new Inventory();
	private VBox orderVBox;
	private ListView<MenuItem> food = new ListView();
	private ListView<MenuItem> drink = new ListView();
	private ListView<MenuItem> special = new ListView();
	private Image orderNote;
	private Order order;
	private ArrayList<Integer> orderIds = new ArrayList<>();
	private ArrayList<Integer> selectedIds = new ArrayList<>();	
	private int coinsEarned = 0;
	private boolean allCorrect = false;
	private int orderTime;
	private Button timerButton = new Button("SERVE");
	private GameTimer gameTimer;
	private ImageView timer;
	private MainScreen mainScreen;
	private ArrayList<String> missedItems = new ArrayList<>();
	
	public KitchenScreen(Order order, MainScreen mainScreen, Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.order = order;
		this.missedItems = missedItems;
		this.mainScreen = mainScreen;
		createKitchenScreen();
		orderIds = order.getOrderIds();
		inventory.shuffle();
		food.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		drink.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		special.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		Text header = new Text("Kitty Krumbs Kitchen");
		HBox headerHBox = new HBox(header);
		header.setFont(Font.font("Consolas", 40));
		header.setFill(javafx.scene.paint.Color.SADDLEBROWN);
		headerHBox.setAlignment(Pos.TOP_CENTER);
		headerHBox.setTranslateY(80);
		createMenuItems();
		createListeners();
		addNodesToKitchenLayout(headerHBox);
		showScreen();
		gameTimer.startTimer();
	}
	
	private void createKitchenScreen() {
		Image kitchen = new Image("file:images/kitchenscreen.png");
		
		BackgroundSize kitchenSize = new BackgroundSize(100, 100, true, true, true, true);
		BackgroundImage kitchenImage = new BackgroundImage(kitchen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, kitchenSize);
		kitchenLayout.setBackground(new Background(kitchenImage));
		kitchenScene = new Scene(kitchenLayout, 1200, 800);
	}
	
	private void addNodesToKitchenLayout(HBox headerHBox) {
		showOrder();
		
		HBox items = new HBox(food, drink, special);
		items.setSpacing(0);
		items.setAlignment(Pos.CENTER);

		kitchenLayout.setTop(headerHBox);
		kitchenLayout.setLeft(orderVBox);
		kitchenLayout.setCenter(items);
	}
	
	private void showOrder() {		
		orderVBox = new VBox();
		orderVBox.setSpacing(20);
		orderVBox.setAlignment(Pos.TOP_CENTER);
		orderVBox.setPadding(new javafx.geometry.Insets(80, 0, 0, 20));
		
		orderNote = new Image("file:images/order_note.png");
		ImageView orderNoteImageView = new ImageView(orderNote);
		orderNoteImageView.setFitHeight(375);
		orderNoteImageView.setPreserveRatio(true);
			
		orderTime = calcOrderTime(order.getNumItems());
		Label orderLabel = new Label(order.toStringKitchen(orderTime));
		orderLabel.setFont(Font.font("Comic Sans MS", 20));
		orderLabel.setTextFill(javafx.scene.paint.Color.SADDLEBROWN);
	    orderLabel.setWrapText(true);
	    orderLabel.setAlignment(Pos.CENTER);
	    orderLabel.setMaxWidth(350);
	    
	    StackPane orderStack = new StackPane(orderNoteImageView, orderLabel);
	    orderStack.setAlignment(Pos.CENTER);
	    
	    ImageView timerImageView = createTimerImage();
		animateTimer();
		
		System.out.println("Total order time: " + orderTime + " seconds");
		gameTimer = new GameTimer(orderTime, timerButton);
		
		VBox timerVBox = new VBox(timerImageView, gameTimer, timerButton);
		timerVBox.setSpacing(10);
		timerVBox.setAlignment(Pos.CENTER);
		timerVBox.setPadding(new javafx.geometry.Insets(20, 0, 0, 0)); // space below order note

		orderVBox.getChildren().addAll(orderStack, timerVBox);

		orderTime = calcOrderTime(order.getNumItems());
		System.out.println("Total order time: " + orderTime + " seconds");

		
	}
	
	private int calcOrderTime(int randomNumItems) {
		return randomNumItems * 7; //7 seconds for each item
	}
	
	public int getOrderTime() {
		return orderTime;
	}
	
	private void createListeners() {
		timerButton.setOnAction(e -> {
			endOrder();
		});
	}	
	
	private ImageView createTimerImage() {
		Image timerImage = new Image("file:images/timer.png");
		timer = new ImageView(timerImage);
		timer.setPreserveRatio(true);  
		timer.setFitWidth(100);
		timer.setSmooth(true);
		timer.setLayoutX(150);
		timer.setLayoutY(100);
		return timer;
	}
	
	private void animateTimer() {
		ScaleTransition pulse = new ScaleTransition(Duration.seconds(1), timer);
		pulse.setFromX(1.0);
		pulse.setFromY(1.0);
		pulse.setToX(1.1);
		pulse.setToY(1.1);
		pulse.setCycleCount(ScaleTransition.INDEFINITE);
		pulse.setAutoReverse(true);
		pulse.play();
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
		new PostOrderScreen(order, this, primaryStage);
	}

	private void checkResults() {
		int numCorrect = 0;
		allCorrect = false;
		missedItems.clear();
		
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
		
		mainScreen.returnFromKitchen(missedItems, allCorrect, coinsEarned);
	}

	public int getCoinsEarned() {
		return coinsEarned;
	}

	public boolean getOrderStatus() {
		return allCorrect;
	}
	
	public ArrayList<String> getMissedItems(){
		return missedItems;
	}
	
	private void createMenuItems() {
		food.getItems().addAll(inventory.getFood());
		drink.getItems().addAll(inventory.getDrinks());
		special.getItems().addAll(inventory.getSpecials());
		
		food.getSelectionModel().clearSelection();
		drink.getSelectionModel().clearSelection();
		special.getSelectionModel().clearSelection();
		
		cropListView(food);
		cropListView(drink);
		cropListView(special);
	}
	
	private void cropListView(ListView<MenuItem> listView) {
		int fixedCellSize = 40;
		listView.setFixedCellSize(fixedCellSize);
	    int itemCount = listView.getItems().size();
		double height = itemCount * fixedCellSize + 2;
		
		listView.setPrefHeight(height);
		listView.setMinHeight(height);
		listView.setMaxHeight(height);
		listView.setStyle("""
		        -fx-background-color: mistyrose;
		        -fx-control-inner-background: mistyrose;
				-fx-border-color: transparent;
		        -fx-overflow: hidden;
		        -fx-skin: "javafx.scene.control.skin.ListViewSkin";
		    """);
		listView.skinProperty().addListener((obs, oldSkin, newSkin) -> {
		    listView.lookupAll(".scroll-bar").forEach(bar -> {
		    	bar.setManaged(false);
		    	bar.setVisible(false);		    	
		    });
		});
	}
	
	public void showScreen() {
		primaryStage.setScene(kitchenScene);
		primaryStage.setTitle("kitchen");
		primaryStage.show();
		kitchenLayout.requestFocus();
	}
}