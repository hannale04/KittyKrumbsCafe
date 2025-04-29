package interfaces;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartScreen extends Application {
	
	private Runnable onStartGameBtClicked;
	
	 public void setOnStartGameBtClicked(Runnable onStartGameBtClicked) {
	        this.onStartGameBtClicked = onStartGameBtClicked;
	 }
	
	@Override
	public void start(Stage primaryStage) {
		//Create start screen scene
		Text welcomeText = new Text("Welcome to Kitty Krumbs Café!");
		Button startButton = new Button("Start Game");
		startButton.setOnAction(e -> {
			if(onStartGameBtClicked != null) {
				onStartGameBtClicked.run();
			}
		});
		
		StackPane layout = new StackPane();
		layout.getChildren().addAll(welcomeText, startButton);
		startButton.setTranslateY(40);
		
		Scene startScene = new Scene(layout, 1200, 800);
		
		primaryStage.setTitle("Kitty Krumbs Café");
		primaryStage.setScene(startScene);
		primaryStage.show();
	}
	
	public void launchStartScreen() {
		launch();
	}
}

