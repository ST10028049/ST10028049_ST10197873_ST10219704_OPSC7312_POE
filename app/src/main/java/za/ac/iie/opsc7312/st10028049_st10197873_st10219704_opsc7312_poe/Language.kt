package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import java.util.*

class Language : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set saved language before setting the content view
        setLocale(getSavedLanguage())
        setContentView(R.layout.activity_language)

        // Set up button for English
        val buttonEnglish: Button = findViewById(R.id.button_english)
        buttonEnglish.setOnClickListener {
            changeLanguage("en")
        }

        // Set up button for Afrikaans
        val buttonAfrikaans: Button = findViewById(R.id.button_afrikaans)
        buttonAfrikaans.setOnClickListener {
            changeLanguage("af")
        }

        // Set up button for Zulu
        val buttonZulu: Button = findViewById(R.id.button_zulu)
        buttonZulu.setOnClickListener {
            changeLanguage("zu")
        }
    }

    private fun changeLanguage(languageCode: String) {
        setLocale(languageCode)
        saveLanguage(languageCode)
        setResult(RESULT_OK)  // Notify that language change has occurred
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

    private fun getSavedLanguage(): String {
        val sharedPreferences: SharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        return sharedPreferences.getString("Language", "en") ?: "en"
    }
}
