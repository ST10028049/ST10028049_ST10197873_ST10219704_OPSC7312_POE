package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import java.util.*

// This code was adapted from Philipp Lackner on YouTube
// YouTube video: How to Translate Your Android App to Any Language (SO EASY!) - Android Studio Tutorial
// Link: https://youtu.be/LXbpsBtIIeM?si=M6UrtusUd_x0SS_H
// YouTube channel: https://www.youtube.com/@PhilippLackner
class Language : AppCompatActivity() {

    private lateinit var buttonEnglish: Button
    private lateinit var buttonAfrikaans: Button
    private lateinit var buttonZulu: Button
    private lateinit var textHello: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set saved language before setting the content view
        setLocale(getSavedLanguage())
        setContentView(R.layout.activity_language)

        // Initialize UI components
        textHello = findViewById(R.id.text_hello)
        buttonEnglish = findViewById(R.id.button_english)
        buttonAfrikaans = findViewById(R.id.button_afrikaans)
        buttonZulu = findViewById(R.id.button_zulu)

        // Set click listeners for buttons
        buttonEnglish.setOnClickListener {
            changeLanguage("en")
        }
        buttonAfrikaans.setOnClickListener {
            changeLanguage("af")
        }
        buttonZulu.setOnClickListener {
            changeLanguage("zu")
        }

        // Update texts for all views on the page
        updateTexts()
    }

    private fun changeLanguage(languageCode: String) {
        setLocale(languageCode)
        saveLanguage(languageCode)
        setResult(RESULT_OK)  // Notify that language change has occurred

        // Update texts immediately on the current page
        updateTexts()
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun saveLanguage(languageCode: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Language", languageCode)
        editor.apply()
    }

    // To be used in other classes to get the saved language
    private fun getSavedLanguage(): String {
        val sharedPreferences: SharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        return sharedPreferences.getString("Language", "en") ?: "en"
    }

    // Function to update texts on all views
    private fun updateTexts() {
        textHello.text = getString(R.string.hello_message)
        buttonEnglish.text = getString(R.string.english)
        buttonAfrikaans.text = getString(R.string.afrikaans)
        buttonZulu.text = getString(R.string.zulu)
    }
}
