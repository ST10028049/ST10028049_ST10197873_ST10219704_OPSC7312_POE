package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class SettingsMenu : AppCompatActivity() {

    private lateinit var usernameDisplay: TextView
    private lateinit var profileImageView: ImageView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_menu)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()


        // Find the views
        usernameDisplay = findViewById(R.id.usernameDisplay)
        profileImageView = findViewById(R.id.profileImageView)

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

    private fun navigateToWelcome() {
        val intent = Intent(this, MainActivity::class.java)  // Adjust the target activity
        startActivity(intent)
        finish()  // Close current activity to prevent going back here
    }
}
