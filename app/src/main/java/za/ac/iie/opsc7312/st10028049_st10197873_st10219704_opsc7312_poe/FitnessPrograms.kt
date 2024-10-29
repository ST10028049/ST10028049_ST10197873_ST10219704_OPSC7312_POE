package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class FitnessPrograms : AppCompatActivity() {
    // Declare a variable for the back button
    lateinit var back: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fitness_programs)

        back = findViewById(R.id.back_button)

        /*
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        */

        // Initialize buttons for each exercise category and set click listeners to navigate
        // to ExerciseListActivity with the corresponding category
        findViewById<TextView>(R.id.strength_pic).setOnClickListener {
            navigateToExerciseList("STRENGTH_TRAINING")
        }

        // Navigate to the list of cardio exercises when cardio button is clicked
        findViewById<TextView>(R.id.cardio_pic).setOnClickListener {
            navigateToExerciseList("CARDIO")
        }

        // Navigate to the list of yoga exercises when yoga button is clicked
        findViewById<TextView>(R.id.yoga_pic).setOnClickListener {
            navigateToExerciseList("YOGA")
        }

        // Navigate to the list of Pilates exercises when Pilates button is clicked
        findViewById<TextView>(R.id.pilates_pic).setOnClickListener {
            navigateToExerciseList("PILATES")
        }

        // Navigate to the list of target area exercises when target area button is clicked
        findViewById<TextView>(R.id.target_area_pic).setOnClickListener {
            navigateToExerciseList("TARGET AREA")
        }

        // Navigate to the list of toning exercises when toning button is clicked
        findViewById<TextView>(R.id.toning_pic).setOnClickListener {
            navigateToExerciseList("TONING")
        }
    }

    // Helper function to navigate to the ExerciseListActivity and pass the selected category
    private fun navigateToExerciseList(category: String) {
        val intent = Intent(this, ExerciseListActivity::class.java)
        // Pass the selected category to the next activity
        intent.putExtra("CATEGORY", category)
        startActivity(intent)
    }
}
