package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {

    private lateinit var darkModeSwitch: Switch
    private lateinit var notificationsCheckbox: CheckBox
    private lateinit var saveButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        darkModeSwitch = findViewById(R.id.switch_dark_mode)
        notificationsCheckbox = findViewById(R.id.checkbox_notifications)
        saveButton = findViewById(R.id.button_save)

        sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE)

        // Load saved settings
        loadSettings()

        saveButton.setOnClickListener {
            saveSettings()
        }
    }

    private fun loadSettings() {
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
        val isNotificationsEnabled = sharedPreferences.getBoolean("notifications", true)

        darkModeSwitch.isChecked = isDarkMode
        notificationsCheckbox.isChecked = isNotificationsEnabled

        // Apply the current theme (light/dark mode)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun saveSettings() {
        val isDarkMode = darkModeSwitch.isChecked
        val isNotificationsEnabled = notificationsCheckbox.isChecked

        val editor = sharedPreferences.edit()
        editor.putBoolean("dark_mode", isDarkMode)
        editor.putBoolean("notifications", isNotificationsEnabled)
        editor.apply()

        // Apply dark or light mode immediately
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Show a message or navigate back
        finish()
    }
}
