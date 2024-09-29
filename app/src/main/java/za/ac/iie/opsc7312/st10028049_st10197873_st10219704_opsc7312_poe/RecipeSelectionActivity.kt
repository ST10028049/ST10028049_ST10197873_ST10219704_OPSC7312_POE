package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class RecipeSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_selection)

        val layoutRecipes = findViewById<LinearLayout>(R.id.layoutRecipes)

        // Sample list of recipes
        val recipeList = listOf(
            "Garlic Parmesan Chicken Wings",
            "Miso Ramen",
            "Pasta Salad with Chicken"
        )

        // Dynamically add recipes to the layout
        for (recipe in recipeList) {
            val recipeButton = Button(this)
            recipeButton.text = recipe
            recipeButton.setOnClickListener {
                // Show recipe details when a recipe is clicked
                val intent = Intent(this, RecipeDetailActivity::class.java)
                intent.putExtra("recipe_name", recipe)
                startActivity(intent)
            }
            layoutRecipes.addView(recipeButton)
        }
    }
}