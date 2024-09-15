package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingsMenu : AppCompatActivity() {

    private lateinit var appearence: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_menu)

        // Find the Appearance TextView
        appearence = findViewById(R.id.appearenceBtn)

        // Set an OnClickListener on the Appearance TextView
        appearence.setOnClickListener {
            // Create an Intent to navigate to SettingsActivity
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)  // Start the new activity
        }
    }
}
