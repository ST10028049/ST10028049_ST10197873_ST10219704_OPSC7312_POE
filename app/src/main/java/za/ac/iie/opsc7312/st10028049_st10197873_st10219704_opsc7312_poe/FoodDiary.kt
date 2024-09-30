package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class FoodDiaryActivity : AppCompatActivity() {

    private lateinit var addMealButton: ImageView
    private lateinit var logMealText: TextView
    private lateinit var todaysKcal: TextView
    private lateinit var todaysWater: TextView
    private lateinit var yesterdaysWater: TextView
    private lateinit var viewMeal: ImageView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_diary)

        sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE)

        // Initialize views
        addMealButton = findViewById(R.id.add_meal_button)
        logMealText = findViewById(R.id.log_meal_text)
        todaysKcal = findViewById(R.id.todays_kcal)
        todaysWater = findViewById(R.id.todays_water)
        yesterdaysWater = findViewById(R.id.yesterdays_water)
        viewMeal = findViewById(R.id.more_options_button)

        // Set up click listener for the add meal button
        addMealButton.setOnClickListener {
            // Start MealDetailsActivity
            val intent = Intent(this, MealDetailsActivity::class.java)
            startActivity(intent)
        }

        viewMeal.setOnClickListener {
            // Start ViewMealsActivity
            val intent = Intent(this, ViewMealsActivity::class.java)
            startActivity(intent)
        }

        // Calculate and display total calories for all meals
        displayTotalCalories()
    }

    private fun displayTotalCalories() {
        val uid = sharedPreferences.getString("uid", null)

        if (uid != null) {
            val mealsString = sharedPreferences.getString("meals", "[]")
            val mealsArray = JSONArray(mealsString)
            var totalCalories = 0

            // Loop through meal logs and sum up all calories for this user
            for (i in 0 until mealsArray.length()) {
                val meal = mealsArray.getJSONObject(i)
                val mealUid = meal.getString("uid")
                val mealCalories = meal.getString("calories") // Assuming calories are stored as a string

                // Only add calories if the meal belongs to the current user
                if (mealUid == uid) {
                    totalCalories += mealCalories.toIntOrNull() ?: 0 // Convert string to int, handle non-numeric safely
                }
            }

            // Update the TextView with the total calories
            todaysKcal.text = "Today: $totalCalories kcal"
        } else {
            todaysKcal.text = "Total: 0 kcal"
        }
    }
}
