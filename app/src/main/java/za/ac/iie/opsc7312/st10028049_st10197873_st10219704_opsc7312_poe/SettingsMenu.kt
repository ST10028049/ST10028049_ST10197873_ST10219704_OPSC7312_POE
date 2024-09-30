package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class SettingsMenu : AppCompatActivity() {

    private lateinit var usernameDisplay: TextView
    private lateinit var profileImageView: ImageView
    private lateinit var nightModeSwitch: Switch
    private lateinit var backbutton: ImageView
    private lateinit var preferences: SharedPreferences
    private lateinit var logoutFunction: LinearLayout
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_menu)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

// Initialize views
        usernameDisplay = findViewById(R.id.usernameDisplay)
        profileImageView = findViewById(R.id.profileImageView)
        nightModeSwitch = findViewById(R.id.nightModeSwitch)


        // Initialize SharedPreferences
        preferences = PreferenceManager.getDefaultSharedPreferences(this)

        val isNightMode = preferences.getBoolean("night_mode", false)
        nightModeSwitch.isChecked = isNightMode

        // Apply the correct theme
        setNightMode(isNightMode)

        // Listen for switch changes
        nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Save the night mode preference
            preferences.edit().putBoolean("night_mode", isChecked).apply()
            setNightMode(isChecked)
        }

        logoutFunction = findViewById(R.id.logoutFunction)

        // Set an OnClickListener on the logout layout
        logoutFunction.setOnClickListener {
            logout()
        }

        // Check if the user is signed in
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Display user's name
            usernameDisplay.text = currentUser.displayName ?: "Jane Salis"

            // Get user's profile image URL
            val photoUrl = currentUser.photoUrl

            if (photoUrl != null) {
                // Load the Google user's profile image as a circle using Glide
                Glide.with(this)
                    .load(photoUrl)
                    .transform(CircleCrop()) // Apply the CircleCrop transformation
                    .into(profileImageView)
            } else {
                // No Google profile image, use default profile icon (circle version)
                Glide.with(this)
                    .load(R.drawable.profile_icon)
                    .transform(CircleCrop()) // Apply CircleCrop to default icon as well
                    .into(profileImageView)
            }
        } else {
            // No user is signed in, set default name and profile image
            usernameDisplay.text = "Jane Salis"
            Glide.with(this)
                .load(R.drawable.profile_icon)
                .transform(CircleCrop()) // CircleCrop for default icon
                .into(profileImageView)
        }
    }

    private fun logout() {
        // Sign out from Firebase
        auth.signOut()

        // Clear any other session data if needed
        // SharedPreferences or any session management code here

        // Navigate to Login Activity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Close the current activity
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
    }
    private fun logoutUser() {
        // Sign out from Firebase
        auth.signOut()

        // Sign out from Google
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        val googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        googleSignInClient.signOut().addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Successfully signed out, now navigate to the welcome screen
                navigateToWelcome()
            } else {
                // Sign out failed, show a message
                Toast.makeText(this, "Failed to log out. Try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setNightMode(isNightMode: Boolean) {
        val settingsText = findViewById<TextView>(R.id.settingsText) // Add this line to reference the Settings TextView

        if (isNightMode) {
            // Set dark mode
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            // Update UI for night mode
            usernameDisplay.setTextColor(resources.getColor(android.R.color.white))
            settingsText.setTextColor(resources.getColor(android.R.color.white)) // Update settingsText color for dark mode

            // Set back button drawable to dark
            findViewById<ImageView>(R.id.back).setColorFilter(resources.getColor(android.R.color.white))
        } else {
            // Set light mode
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            // Reset UI for light mode
            usernameDisplay.setTextColor(resources.getColor(android.R.color.black))
            settingsText.setTextColor(resources.getColor(android.R.color.black)) // Update settingsText color for light mode

            // Set back button drawable to light
            findViewById<ImageView>(R.id.back).setColorFilter(resources.getColor(android.R.color.black))
        }
    }


    private fun navigateToWelcome() {
        val intent = Intent(this, MainActivity::class.java)  // Adjust the target activity
        startActivity(intent)
        finish()  // Close current activity to prevent going back here
    }
}
