package main;

import interfaces.MainScreen;
import interfaces.StartScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class PlayKittyKrumbsCafe extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		StartScreen startScreen = new StartScreen();
//		startScreen.setOnStartGameBtClicked(() -> {
//			MainScreen mainScreen = new MainScreen();
//			mainScreen.start(primaryStage);
//		});
//		
		startScreen.start(primaryStage);
	}
	
    public static void main(String[] args) {
        StartScreen.launch(args);
    }
}

