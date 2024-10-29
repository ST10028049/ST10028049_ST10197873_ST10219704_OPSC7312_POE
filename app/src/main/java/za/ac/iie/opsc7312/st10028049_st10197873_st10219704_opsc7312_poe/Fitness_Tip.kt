package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Fitness_Tip : AppCompatActivity() {
    // Declare a variable for the begin button
    lateinit var begin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fitness_tip)

        begin = findViewById(R.id.begin_btn)

        begin.setOnClickListener {
            val intent = Intent(this, FitnessPrograms::class.java)
            startActivity(intent)
        }

    }
}