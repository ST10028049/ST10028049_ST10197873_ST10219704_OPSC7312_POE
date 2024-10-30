package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import android.content.res.Configuration
import java.util.Locale

class Main_Menu : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var exerciseTracking: TextView
    private lateinit var foodDiary: TextView
    private lateinit var mealPlanning: TextView
    private lateinit var reportsAnalytics: TextView
    private lateinit var goals: TextView
    private lateinit var fitnessPrograms: TextView
    private lateinit var settings: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE)

        // Initialize the TextViews
        exerciseTracking = findViewById(R.id.tv_exercise_tracking)
        foodDiary = findViewById(R.id.tv_food_diary)
        mealPlanning = findViewById(R.id.tv_meal_planning)
        reportsAnalytics = findViewById(R.id.tv_reports_analytics)
        goals = findViewById(R.id.tv_goals)
        fitnessPrograms = findViewById(R.id.tv_fitness_programs)
        settings = findViewById(R.id.tv_settings)

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

        goals.setOnClickListener {
            startActivity(Intent(this, Goals::class.java))
        }

        fitnessPrograms.setOnClickListener {
            startActivity(Intent(this, Fitness_Tip::class.java))
        }

        settings.setOnClickListener {
            startActivity(Intent(this, SettingsMenu::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        // Apply the saved language each time the main menu resumes
        val savedLanguage = getSavedLanguage()
        setLocale(savedLanguage)
        updateTexts(savedLanguage) // Update UI texts based on the saved language
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun getSavedLanguage(): String {
        return sharedPreferences.getString("Language", "en") ?: "en"
    }

    // Function to update texts on all views based on the selected language
    private fun updateTexts(languageCode: String) {
        // Update the TextViews with the appropriate strings based on the current language
        exerciseTracking.text = getString(R.string.exercise_tracking)
        foodDiary.text = getString(R.string.food_diary)
        mealPlanning.text = getString(R.string.meal_planning)
        reportsAnalytics.text = getString(R.string.reports)
        goals.text = getString(R.string.goals)
        fitnessPrograms.text = getString(R.string.programs)
        settings.text = getString(R.string.settings)
    }
}

