package interfaces;

import java.util.ArrayList;
import java.util.Map;

import database.Inventory;
import database.Recipe;
import gameobjects.Order;
import images.Images;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//Comment
//Bar is where player fulfills order
public class BarScreen extends Application {
	private Stage primaryStage;
	private BorderPane barLayout = new BorderPane();
	private Inventory inventory = new Inventory();
	private int numItems;
	private ImageView barScreenImageView;
	private VBox orderVBox;
	private VBox recipeVBox;
	private HBox ingredientsBar = new HBox(10);
	
	private Button milkBt;
	private Button sugarBt;
	private Button flourBt;
	private Button eggBt;
	private Button saltBt;
	private Button creamBt;
	private Button caramelizedSugarBt;
	private Button bakingPowderBt;
	private Button cinnamonBt;
	private Button matchaPowderBt;
	private Button butterBt;
	private Button espressoBt;
	private Button vanillaSyrupBt;
	private Button chocolateSyrupBt;
	private Button whiteChocolateChipsBt;
	private Button applesBt;
	private Button blueberriesBt;
	private Button strawberriesBt;
	private Button whippedCreamBt;
	private Button iceBt;
	
	private Images images = new Images();
		
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		//createBarScreen();
		createIngredientButtons();
		createListeners();
		addNodesToBarLayout();
		showScreen();
	}
	
	private void createIngredientButtons() {
		milkBt = new Button("Milk");
		sugarBt = new Button("Sugar");
		flourBt = new Button("Flour");
		eggBt = new Button("Egg");
		saltBt = new Button("Salt");
		creamBt = new Button("Cream");
		caramelizedSugarBt = new Button("Caramelized Sugar");
		bakingPowderBt = new Button("Baking Powder");
		cinnamonBt = new Button("Cinnamon");
		
		matchaPowderBt = createImageButton("Matcha Powder", "/images/matchapowder.png", 150, 150);
		
		Image butterImage = new Image(getClass().getResourceAsStream("/images/butter.png"));
		ImageView butterImageView = new ImageView(butterImage);
		butterBt = new Button("Butter", butterImageView);
		butterImageView.setFitWidth(200);
		butterImageView.setFitHeight(200);
		
		espressoBt = new Button("Espresso");
		vanillaSyrupBt = new Button("Vanilla Syrup");
		chocolateSyrupBt = new Button("Chocolate Syrup");
		whiteChocolateChipsBt = new Button("White Chocolate Chips");
		applesBt = new Button("Apples");
		blueberriesBt = new Button("Blueberries");
		strawberriesBt = new Button("Strawberries");
		whippedCreamBt = new Button("Whipped Cream");
		iceBt = new Button("Ice");
		
		ingredientsBar.getChildren().addAll(milkBt, sugarBt, flourBt, eggBt, saltBt, creamBt, caramelizedSugarBt, bakingPowderBt, cinnamonBt, matchaPowderBt, butterBt, espressoBt, vanillaSyrupBt, chocolateSyrupBt, whiteChocolateChipsBt, applesBt, blueberriesBt, strawberriesBt, whippedCreamBt, iceBt);
		ingredientsBar.setAlignment(Pos.CENTER_LEFT);
		ingredientsBar.setSpacing(10);
		ingredientsBar.setStyle("-fx-alignment: center;");
		ingredientsBar.setStyle("-fx-padding: 10");
		
	}
	
	private Button createImageButton(String text, String imagePath, double width, double height) {
		Image image = new Image(getClass().getResourceAsStream(imagePath));
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		return new Button(text, imageView);
	}
	
	private void addNodesToBarLayout() {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefViewportHeight(200);
		scrollPane.setContent(ingredientsBar);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setPannable(true);
		scrollPane.setFitToHeight(true);
	    scrollPane.setStyle("-fx-background: white; -fx-border-color: black;");
		
		barLayout.setBottom(scrollPane);
	}
	
	private void createListeners() {
		for(Node node : ingredientsBar.getChildren()) {
			if(node instanceof Button button) {
				button.setPrefWidth(300);
				button.setPrefHeight(300);
	            button.setStyle("-fx-background-color: burlywood;");
				button.setOnAction(e -> toggleButtonSelection(button));
			}
	    }
	}
	
	private void toggleButtonSelection(Button button) {
		if(button.getStyle().contains("bisque")) {
			button.setStyle("-fx-background-color: burlywood;");
		} else {
            button.setStyle("-fx-background-color: bisque;");
		}
	}
	
	private void showRecipe(VBox recipeVBox) {
        ArrayList<String> ingredients = Recipe.getRecipeForMenuItem(menuItemName);
		recipeVBox.setPrefWidth(400);
		recipeVBox.setPrefHeight(250);
		recipeVBox.setLayoutX(700);
		recipeVBox.setLayoutY(400);
		recipeVBox.setSpacing(20);
		recipeVBox.setStyle("-fx-background-color: beige; -fx-padding: 30; -fx-border-color: white;");
		
		StringBuilder sb = new StringBuilder();
			
		for(Map.Entry<String, ArrayList<String>> entry : recipeMap.entrySet()) {
			recipeLabel.append("Ingredients for ").append(menuItemName).append(":\n");
		        for (String ingredient : ingredients) {
		            recipeText.append(ingredient).append("\n");
		        }
		        
		        Label recipeLabel = new Label(recipeText.toString());
		        recipeLabel.setStyle("-fx-font-size: 20px;");
		        
		        recipeVBox.getChildren().add(recipeLabel);
		    }
		Label recipeLabel = new Label(recipe.toString());
		recipeLabel.setStyle("-fx-font-size: 20px;");
		
		recipeVBox.getChildren().add(recipeLabel);
	
	}
	
	private void showScreen() {
		Scene scene = new Scene(barLayout, 1200, 800);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bar");
		primaryStage.show();
	}
	
}