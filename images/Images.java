package images;

import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Images {
	private HashMap<String, ImageView> imageViews = new HashMap<>();
	
	public Images() {				
		createImageViews();
	}
	
	private void createImageViews() {
		/*
		loadImage("milk", "/images/milk.png");
		loadImage("sugar", "/images/sugar.png");
        loadImage("flour", "/images/flour.png");
        loadImage("salt", "/images/salt.png");
        loadImage("cream", "/images/cream.png");
        loadImage("caramelized sugar", "/images/caramelizedsugar.png");
        loadImage("baking powder", "/images/bakingpowder.png");
        loadImage("cinnamon", "/images/cinnamon.png");
         */
        loadImage("matcha powder", "/images/matchapowder.png");
        loadImage("butter", "/images/butter.png");
        /*

        loadImage("espresso", "/images/espresso.png");
        loadImage("vanilla syrup", "/images/vanillasyrup.png");
        loadImage("chocolate syrup", "/images/chocolatesyrup.png");
        loadImage("white chocolate chips", "/images/whitechocolatechips.png");
        loadImage("apples", "/images/apples.png");
        loadImage("blueberries", "/images/blueberries.png");
        loadImage("strawberries", "/images/strawberries.png");
        loadImage("whipped cream", "/images/whippedcream.png");
        loadImage("ice", "/images/ice.png");
        */
	}
	
	private void loadImage(String key, String path) {
		Image image = new Image(getClass().getResourceAsStream(path));
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(50);
		imageView.setFitHeight(50);
		imageViews.put(key, imageView);
	}
	
	public ImageView getImageView(String key) {
        return imageViews.get(key);
    }
}