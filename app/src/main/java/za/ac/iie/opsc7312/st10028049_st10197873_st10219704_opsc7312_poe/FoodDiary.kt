package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FoodDiaryActivity : AppCompatActivity() {

    private lateinit var addMealButton: ImageView
    private lateinit var logMealText: TextView
    private lateinit var todaysKcal: TextView
    private lateinit var todaysWater: TextView
    private lateinit var yesterdaysWater: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_diary)

        // Initialize views
        addMealButton = findViewById(R.id.add_meal_button)
        logMealText = findViewById(R.id.log_meal_text)
        todaysKcal = findViewById(R.id.todays_kcal)
        todaysWater = findViewById(R.id.todays_water)
        yesterdaysWater = findViewById(R.id.yesterdays_water)

        // Set up click listener for the add meal button
        addMealButton.setOnClickListener {
            // Start AddMealActivity
            val intent = Intent(this, MealDetailsActivity::class.java)
            startActivity(intent)
        }

        // Optionally set some sample data
        todaysKcal.text = "Today: 833 kcal"
        todaysWater.text = "Today: 1400ml"
        yesterdaysWater.text = "Yesterday: 2300ml"
    }
}
