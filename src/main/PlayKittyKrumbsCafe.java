package main;

import interfaces.StartScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class PlayKittyKrumbsCafe extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		StartScreen startScreen = new StartScreen();
		startScreen.start(primaryStage);
	}
	
    public static void main(String[] args) {
        StartScreen.launch(args);
    }
}

