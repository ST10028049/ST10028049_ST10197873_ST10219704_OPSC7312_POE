package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main_Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        // Initialize the TextViews
        val exerciseTracking: TextView = findViewById(R.id.tv_exercise_tracking)
        val foodDiary: TextView = findViewById(R.id.tv_food_diary)
        val mealPlanning: TextView = findViewById(R.id.tv_meal_planning)
        val reportsAnalytics: TextView = findViewById(R.id.tv_reports_analytics)
        val goals: TextView = findViewById(R.id.tv_goals)
        val fitnessPrograms: TextView = findViewById(R.id.tv_fitness_programs)
        val settings: TextView = findViewById(R.id.tv_settings)

        // Set click listeners for each menu option
        exerciseTracking.setOnClickListener {
            startActivity(Intent(this, ExerciseTrackerActivity::class.java))
        }

        foodDiary.setOnClickListener {
            startActivity(Intent(this, FoodDiaryActivity::class.java))
        }

        mealPlanning.setOnClickListener {
            startActivity(Intent(this, MealPlan::class.java))
        }

        /*reportsAnalytics.setOnClickListener {
            startActivity(Intent(this, ReportsAnalyticsActivity::class.java))
        }*/

        goals.setOnClickListener {
            startActivity(Intent(this, Goals::class.java))
        }

        /*fitnessPrograms.setOnClickListener {
            startActivity(Intent(this, FitnessProgramsActivity::class.java))
        }*/

        settings.setOnClickListener {
            startActivity(Intent(this, SettingsMenu::class.java))
        }
    }
}
