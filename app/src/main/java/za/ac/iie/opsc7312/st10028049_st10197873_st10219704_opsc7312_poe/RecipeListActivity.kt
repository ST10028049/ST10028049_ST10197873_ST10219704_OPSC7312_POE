package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RecipeListActivity : AppCompatActivity() {

    private val recipes = listOf(
        Recipe("Garlic Parmesan Chicken Wings",
            "Ingredients: 1 lb chicken wings, 3 cloves garlic (minced), 1/2 cup grated Parmesan cheese, 1/4 cup melted butter, salt and pepper to taste.",
            "Method: Preheat oven to 400°F (200°C). Toss the chicken wings in melted butter, garlic, and Parmesan. Bake for 25-30 minutes until crispy. Serve with extra Parmesan.",
            "Cookware: Baking sheet, mixing bowl, oven"),

        Recipe("Miso Hainan Broth Noodles",
            "Ingredients: 200g rice noodles, 2 tbsp miso paste, 1 tbsp soy sauce, 1 tbsp sesame oil, 2 cups chicken broth, sliced chicken breast, green onions for garnish.",
            "Method: Cook noodles as per package instructions. In a pot, mix broth, miso paste, and soy sauce, bringing to a simmer. Add chicken and cook until done. Serve noodles in broth, topped with green onions.",
            "Cookware: Pot, saucepan, mixing spoon"),

        Recipe("Spaghetti Aglio e Olio",
            "Ingredients: 200g spaghetti, 5 cloves garlic (thinly sliced), 1/4 cup olive oil, red pepper flakes, parsley, Parmesan cheese for garnish.",
            "Method: Cook spaghetti until al dente. In a pan, heat olive oil and sauté garlic until golden. Toss cooked pasta with garlic oil and pepper flakes. Garnish with parsley and Parmesan.",
            "Cookware: Pot, pan, colander"),

        Recipe("Grilled Cheese Sandwich",
            "Ingredients: 2 slices of bread, 2 slices of cheese, 2 tbsp butter.",
            "Method: Heat a pan over medium heat. Butter one side of each bread slice. Place one slice butter-side-down in the pan, add cheese, then the second slice butter-side-up. Grill until golden on both sides.",
            "Cookware: Skillet, spatula"),

        Recipe("Chicken Caesar Salad",
            "Ingredients: 1 chicken breast, romaine lettuce, Caesar dressing, croutons, Parmesan cheese.",
            "Method: Grill or pan-sear the chicken until fully cooked, then slice. Toss lettuce with Caesar dressing, add chicken slices, croutons, and Parmesan.",
            "Cookware: Grill pan or skillet, salad bowl"),

        Recipe("Lemon Butter Salmon",
            "Ingredients: 2 salmon fillets, 2 tbsp butter, juice of 1 lemon, salt and pepper, fresh herbs (optional).",
            "Method: Heat a pan over medium-high heat, melt butter, and cook salmon fillets skin-side-down until crispy. Flip and cook for another 3-4 minutes. Squeeze lemon juice over before serving.",
            "Cookware: Skillet, spatula"),

        Recipe("Vegetable Stir-Fry",
            "Ingredients: 1 bell pepper, 1 zucchini, 1 carrot, 2 tbsp soy sauce, 1 tbsp sesame oil, garlic, ginger.",
            "Method: Slice vegetables into strips. Heat sesame oil in a wok, add garlic and ginger. Stir-fry the vegetables until tender. Add soy sauce and cook for another 2 minutes.",
            "Cookware: Wok or skillet, spatula"),

        Recipe("Pancakes",
            "Ingredients: 1 cup flour, 1 tbsp sugar, 1 tsp baking powder, 1 egg, 1 cup milk, 2 tbsp butter (melted).",
            "Method: Mix dry ingredients in one bowl, wet ingredients in another. Combine and mix until smooth. Heat a pan over medium heat and cook pancakes until bubbles form on the surface, then flip and cook until golden.",
            "Cookware: Mixing bowl, skillet, whisk, spatula"),

        Recipe("Beef Tacos",
            "Ingredients: 200g ground beef, taco seasoning, taco shells, lettuce, tomato, cheese, sour cream.",
            "Method: Cook ground beef in a skillet, add taco seasoning. Assemble tacos with beef, lettuce, tomato, cheese, and sour cream.",
            "Cookware: Skillet, spatula"),

        Recipe("Chocolate Mug Cake",
            "Ingredients: 4 tbsp flour, 2 tbsp cocoa powder, 3 tbsp sugar, 1/4 tsp baking powder, 3 tbsp milk, 1 tbsp vegetable oil, 1/2 tsp vanilla extract.",
            "Method: Mix all ingredients in a microwave-safe mug. Microwave on high for 1 minute 30 seconds. Let cool slightly before eating.",
            "Cookware: Mug, microwave, spoon")
    )


    private val selectedRecipes = mutableListOf<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        val recipeContainer = findViewById<LinearLayout>(R.id.recipeContainer)
        val btnConfirmSelection = findViewById<Button>(R.id.btnConfirmSelection)

        // Dynamically add recipes to the list
        recipes.forEach { recipe ->
            val recipeView = TextView(this).apply {
                text = recipe.name
                textSize = 18f
                setOnClickListener {
                    val intent = Intent(this@RecipeListActivity, RecipeDetailActivity::class.java)
                    intent.putExtra("recipe", recipe)
                    startActivity(intent)
                }
            }
            recipeContainer.addView(recipeView)

            // Mark recipes as selected on click
            recipeView.setOnClickListener {
                if (!selectedRecipes.contains(recipe)) {
                    selectedRecipes.add(recipe)
                    recipeView.setBackgroundColor(Color.LTGRAY)
                } else {
                    selectedRecipes.remove(recipe)
                    recipeView.setBackgroundColor(Color.TRANSPARENT)
                }
            }
        }

        btnConfirmSelection.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("selectedRecipes", ArrayList(selectedRecipes))
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}