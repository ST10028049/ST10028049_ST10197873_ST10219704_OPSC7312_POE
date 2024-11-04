package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Bundle
import android.provider.Settings
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

class Biometric : AppCompatActivity() {

    private lateinit var fingerprint: ImageView
    private lateinit var backbutton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biometric)

        fingerprint = findViewById(R.id.fingerprint_img)
        backbutton = findViewById(R.id.back_button)

    }

        // Method to check if biometric authentication is available and if fingerprints are enrolled
        private fun checkBiometricEnrollment() { //this method was adapted by (Lackner.2024)
            val biometricManager = BiometricManager.from(this)
            // Determine the state of biometric authentication
            when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
                BiometricManager.BIOMETRIC_SUCCESS -> {
                    // Device has fingerprint capability and fingerprints are enrolled; proceed with authentication
                    setupFingerprintAuthentication()
                }
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                    // Inform the user that no fingerprint hardware is available
                    Toast.makeText(this, "No fingerprint hardware available", Toast.LENGTH_LONG).show()
                }
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                    // Inform the user that fingerprint hardware is unavailable
                    Toast.makeText(this, "Fingerprint hardware is unavailable", Toast.LENGTH_LONG).show()
                }
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    // No fingerprints are enrolled; prompt the user to enroll a fingerprint
                    Toast.makeText(this, "Please enroll at least one fingerprint", Toast.LENGTH_LONG).show()
                    val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL)
                    // Specify that only strong biometric authenticators are allowed
                    enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED, BiometricManager.Authenticators.BIOMETRIC_STRONG)
                    startActivity(enrollIntent) // Start the biometric enrollment activity
                }
            }
        }

        // Method to set up fingerprint authentication when the fingerprint image is clicked
        private fun setupFingerprintAuthentication() {
            fingerprint.setOnClickListener {
                // Build the prompt information for the biometric authentication
                val promptInfo = BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Cover the entire sensor with your finger")
                    .setDescription("User Authentication")
                    .setNegativeButtonText("Cancel")
                    .build()
                // Authenticate using the built prompt information
                getPrompt().authenticate(promptInfo)
            }

            // navigation back to the login page
            backbutton.setOnClickListener{
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
        }

        // Method to create and return a BiometricPrompt for authentication
        private fun getPrompt(): BiometricPrompt { //this method was adapter by (Code with Coffee.2022)
            // Create an executor to run tasks on the main thread
            val executor: Executor = ContextCompat.getMainExecutor(this)
            // Create a callback to handle authentication results
            val callback = object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int, @NonNull errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    // Show an error message if authentication fails
                    Toast.makeText(this@Biometric, errString, Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationSucceeded(@NonNull result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    // On successful authentication, start the main menu activity
                    val intent = Intent(this@Biometric, Main_Menu::class.java)
                    startActivity(intent)
                    // Show a confirmation message
                    Toast.makeText(this@Biometric, "Fingerprint confirmed. Successful Login", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    // Show a message if authentication fails
                    Toast.makeText(this@Biometric, "Authentication Failed", Toast.LENGTH_SHORT).show()
                }
            }
            // Return a new BiometricPrompt instance with the specified callback
            return BiometricPrompt(this, executor, callback)
        }
    }
// Reference List
    /*How to Make a FingerPrint Authentication System in Android Studio And Java.2022.YouTube video , added by Code with Coffee, 3 May 2022.[Online].Available at:https://www.youtube.com/watch?v=oHKcq1PrYQc .[Accessed 29 October 2024] */
    /*How to Implement Biometric Auth in Your Android App.2024.YouTube video , added by Philipp Lackner, 20 March 2024.[Online].Available at:https://www.youtube.com/watch?v=_dCRQ9wta-I&t=11s .[Accessed 29 October 2024] */

}
