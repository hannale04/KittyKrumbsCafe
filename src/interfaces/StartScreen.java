package interfaces;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartScreen extends Application {
	@Override
	public void start(Stage primaryStage) {
		Image startImage = new Image("file:images/startscreen.png");
		ImageView backgroundImage = new ImageView(startImage);
		
		Pane startLayout = new Pane();
		backgroundImage.fitWidthProperty().bind(startLayout.widthProperty());
		backgroundImage.fitHeightProperty().bind(startLayout.heightProperty());
		
		Button startButton = new Button();
        startButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
		startButton.setPrefSize(310, 120);
		startButton.setLayoutX(435);
		startButton.setLayoutY(580);
		startButton.setOnAction(e -> {
		
		MainScreen mainScreen = new MainScreen();
		});
		
		startLayout.getChildren().addAll(backgroundImage, startButton);
		startButton.setTranslateY(40);
		
		Scene startScene = new Scene(startLayout, 1200, 800);
		primaryStage.setTitle("Kitty Krumbs Café");
		primaryStage.setScene(startScene);
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}

