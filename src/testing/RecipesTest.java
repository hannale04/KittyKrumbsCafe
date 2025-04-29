package testing;

import database.Recipe;

public class RecipesTest {
	public static void main(String[] args) {
        Recipe recipe = new Recipe();
        System.out.println("Ingredients for 'Iced Vanilla Latte': " + recipe.getRecipeForMenuItem("Iced Vanilla Latte"));
    }
}

