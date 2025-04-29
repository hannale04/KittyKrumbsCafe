package main;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class KittyKrumbsCafeGame {
	private BorderPane gameScreen = new BorderPane();
	private double screenWidth;
	private double screenHeight;


private void showGame() {
	Scene scene = new Scene(gameScreen, screenWidth, screenHeight);
	
	//Instantiate a Stage
	Stage primaryStage = new Stage();
	primaryStage.setTitle("Kitty Krumbs Café");
	primaryStage.setScene(scene);
	primaryStage.show();
	}
}