package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class RecipeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        val tvRecipeName = findViewById<TextView>(R.id.tvRecipeName)
        val tvRecipeDetail = findViewById<TextView>(R.id.tvRecipeDetail)

        val recipe = intent.getSerializableExtra("recipe") as Recipe
        tvRecipeName.text = recipe.name

        findViewById<Button>(R.id.btnIngredients).setOnClickListener {
            tvRecipeDetail.text = recipe.ingredients
        }

        findViewById<Button>(R.id.btnMethod).setOnClickListener {
            tvRecipeDetail.text = recipe.method
        }

        findViewById<Button>(R.id.btnCookware).setOnClickListener {
            tvRecipeDetail.text = recipe.cookware
        }
    }
}