package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MealPlan : AppCompatActivity() {

    private val currentMealPlan = mutableListOf<Recipe>()
    private val previousMealPlans = mutableListOf<Pair<String, List<Recipe>>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_plan)

        val btnStartMealPlan = findViewById<Button>(R.id.btnStartMealPlan)
        val llCurrentMealPlan = findViewById<LinearLayout>(R.id.llCurrentMealPlan)
        val llPreviousMealPlans = findViewById<LinearLayout>(R.id.llPreviousMealPlans)

        btnStartMealPlan.setOnClickListener {
            if (currentMealPlan.isNotEmpty()) {
                val currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
                previousMealPlans.add(Pair(currentDate, currentMealPlan.toList()))
                currentMealPlan.clear()
                updatePreviousMealPlans(llPreviousMealPlans)
            }
            val intent = Intent(this, RecipeListActivity::class.java)
            startActivityForResult(intent, 100)
        }

        updateMealPlanDisplay(llCurrentMealPlan)
    }

    private fun updateMealPlanDisplay(llCurrentMealPlan: LinearLayout) {
        llCurrentMealPlan.removeAllViews()
        if (currentMealPlan.isNotEmpty()) {
            currentMealPlan.forEach { recipe ->
                val recipeView = TextView(this).apply {
                    text = recipe.name
                    textSize = 18f
                    setOnClickListener {
                        val intent = Intent(this@MealPlan, RecipeDetailActivity::class.java)
                        intent.putExtra("recipe", recipe)
                        startActivity(intent)
                    }
                }
                llCurrentMealPlan.addView(recipeView)
            }
        } else {
            val emptyView = TextView(this).apply {
                text = "No current meal plan"
                textSize = 16f
            }
            llCurrentMealPlan.addView(emptyView)
        }
    }

    private fun updatePreviousMealPlans(llPreviousMealPlans: LinearLayout) {
        llPreviousMealPlans.removeAllViews()
        previousMealPlans.forEach { (date, mealPlan) ->
            val planHeader = TextView(this).apply {
                text = "Meal Plan from $date:"
                textSize = 16f
                setPadding(0, 16, 0, 0)
            }
            llPreviousMealPlans.addView(planHeader)

            mealPlan.forEach { recipe ->
                val recipeView = TextView(this).apply {
                    text = recipe.name
                    textSize = 14f
                    setOnClickListener {
                        val intent = Intent(this@MealPlan, RecipeDetailActivity::class.java)
                        intent.putExtra("recipe", recipe)
                        startActivity(intent)
                    }
                }
                llPreviousMealPlans.addView(recipeView)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val selectedRecipes = data?.getSerializableExtra("selectedRecipes") as? List<Recipe>
            if (selectedRecipes != null) {
                currentMealPlan.addAll(selectedRecipes)
                updateMealPlanDisplay(findViewById(R.id.llCurrentMealPlan))
            }
        }
    }
}