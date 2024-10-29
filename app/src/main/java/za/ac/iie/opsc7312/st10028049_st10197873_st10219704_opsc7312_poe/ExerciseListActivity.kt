package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExerciseListActivity : AppCompatActivity() {
    // Declare a variable for the back button
    lateinit var back: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_list_activity)

        // Get the category passed from the previous activity
        val category = intent.getStringExtra("CATEGORY")

        // Retrieve a list of exercises based on the selected category
        val exercises = getExercisesForCategory(category)

        // Initialize the RecyclerView and set its layout manager
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        // Set the adapter to the RecyclerView
        recyclerView.adapter = ExerciseAdapter(exercises)

        // Initialize the back button
        back = findViewById(R.id.back_button)

        // Set an OnClickListener on the back button to navigate back to the MainActivity
        back.setOnClickListener {
            val intent = Intent(this, Main_Menu::class.java)
            startActivity(intent) // Start the MainActivity
        }
    }

    // Function to get exercises based on the selected category
    private fun getExercisesForCategory(category: String?): List<Exercise> {
        return when (category) {
            "STRENGTH_TRAINING" -> getStrengthTrainingExercises() // Return strength training exercises
            "CARDIO" -> getCardioExercises() // Return cardio exercises
            "YOGA" -> getYogaExercises() // Return yoga exercises
            "PILATES" -> getPilatesExercises() // Return Pilates exercises
            "TARGET AREA" -> getTargetAreaExercises() // Return target area exercises
            "TONING" -> getToningExercises() // Return toning exercises
            // If the category doesn't match any known type, return an empty list
            else -> emptyList()
        }
    }

    // Function to define strength training exercises
    private fun getStrengthTrainingExercises(): List<Exercise> {
        return listOf(
            Exercise("Push-up", "Beginner", "A basic push-up exercise.", R.drawable.pushups),
            Exercise("Bench Press", "Intermediate", "Bench press with weights.", R.drawable.bench_press),
            Exercise("Deadlift", "Advanced", "Heavy deadlift for advanced training.", R.drawable.deadlifts)
        )
    }

    // Function to define cardio exercises
    private fun getCardioExercises(): List<Exercise> {
        return listOf(
            Exercise("Jump Rope", "Beginner", "Basic jump rope cardio.", R.drawable.jump_rope),
            Exercise("Running", "Intermediate", "Running at a moderate pace.", R.drawable.running),
            Exercise("HIIT", "Advanced", "High-intensity interval training.", R.drawable.hiit)
        )
    }

    // Function to define yoga exercises
    private fun getYogaExercises(): List<Exercise> {
        return listOf(
            Exercise("Cat-Cow Pose", "Beginner", "A gentle flow between two poses that warms the spine and relieves back tension.", R.drawable.cat_cow),
            Exercise("Crow Pose", "Intermediate", "An arm balance that builds strength in the arms and core.", R.drawable.crow_pose),
            Exercise("Firefly Pose", "Advanced", "An advanced pose that improves flexibility and strength.", R.drawable.firefly_pose)
        )
    }

    // Function to define Pilates exercises
    private fun getPilatesExercises(): List<Exercise> {
        return listOf(
            Exercise("The Hundred", "Beginner", "A classic Pilates exercise that warms up the body and strengthens the core.", R.drawable.the_hundred),
            Exercise("Teaser", "Intermediate", "A challenging core exercise that builds strength and control.", R.drawable.teaser),
            Exercise("Leg Circles", "Advanced", "An exercise that enhances flexibility and stability in the hips.", R.drawable.leg_circles)
        )
    }

    // Function to define target area exercises
    private fun getTargetAreaExercises(): List<Exercise> {
        return listOf(
            Exercise("Plank", "Beginner", "A foundational exercise for building core strength and stability.", R.drawable.planks),
            Exercise("V-Ups", "Intermediate", "A dynamic core exercise that targets the abdominal muscles.", R.drawable.v_ups),
            Exercise("Pistol Squats", "Advanced", "An advanced single-leg squat that builds strength and balance.", R.drawable.pistol_squats)
        )
    }

    // Function to define toning exercises
    private fun getToningExercises(): List<Exercise> {
        return listOf(
            Exercise("Wall Sits", "Beginner", "An effective isometric exercise that strengthens the legs.", R.drawable.wall_sits),
            Exercise("Dumbbell Deadlifts", "Intermediate", "A strength training exercise that targets the posterior chain.", R.drawable.dumbell_deadlifts),
            Exercise("Turkish Get-Ups", "Advanced", "A full-body exercise that enhances strength, stability, and mobility.", R.drawable.turkish_getups)
        )
    }
}
