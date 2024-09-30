package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Goals : AppCompatActivity() {

    private lateinit var addGoalButton: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)

        addGoalButton = findViewById(R.id.add_goal)

        addGoalButton.setOnClickListener {
            // Start AddMealActivity
            val intent = Intent(this, AddGoals::class.java)
            startActivity(intent)
        }

    }
}