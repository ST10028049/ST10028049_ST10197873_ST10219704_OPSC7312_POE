package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe.R
import java.util.*

// This code was adapted using ChatGPT
// Link: https://chatgpt.com/
class MealDetailsActivity : AppCompatActivity() {

    private lateinit var mealNameEditText: EditText
    private lateinit var mealDescriptionEditText: EditText
    private lateinit var mealTypeSpinner: Spinner
    private lateinit var caloriesEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var timeEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_details)

        mealNameEditText = findViewById(R.id.input_meal_name)
        mealDescriptionEditText = findViewById(R.id.input_meal_description)
        mealTypeSpinner = findViewById(R.id.spinner_meal_type)
        caloriesEditText = findViewById(R.id.input_calories)
        dateEditText = findViewById(R.id.input_date)
        timeEditText = findViewById(R.id.input_time)
        submitButton = findViewById(R.id.button_submit)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE)

        // Date picker
        dateEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                dateEditText.setText(selectedDate)
            }, year, month, day).show()
        }

        // Time picker
        timeEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                timeEditText.setText(selectedTime)
            }, hour, minute, true).show()
        }

        // Submit button logic
        submitButton.setOnClickListener {
            captureAndStoreMealDetails()
        }
    }

    private fun captureAndStoreMealDetails() {
        // Retrieve uid from SharedPreferences
        val uid = sharedPreferences.getString("uid", null)

        if (uid == null) {
            Toast.makeText(this, "User not logged in. Please log in to submit meal.", Toast.LENGTH_SHORT).show()
            return
        }

        // Capture meal details from UI inputs
        val mealName = mealNameEditText.text.toString().trim()
        val mealDescription = mealDescriptionEditText.text.toString().trim()
        val mealType = mealTypeSpinner.selectedItem.toString()
        val calories = caloriesEditText.text.toString().toIntOrNull() ?: 0
        val date = dateEditText.text.toString().trim()
        val time = timeEditText.text.toString().trim()

        // Validate input
        if (mealName.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty()) {
            // Create a new Meal object
            val newMeal = Meal(
                name = mealName,
                description = mealDescription,
                type = mealType,
                calories = calories,
                date = date,
                time = time
            )

            // Retrieve the existing meal list as a JSON array
            val mealsString = sharedPreferences.getString("meals", "[]")
            val mealsArray = JSONArray(mealsString)

            // Add new meal to the JSON array
            val mealJson = JSONObject().apply {
                put("uid", uid)
                put("mealName", newMeal.name)
                put("mealDescription", newMeal.description)
                put("mealType", newMeal.type)
                put("calories", newMeal.calories)
                put("date", newMeal.date)
                put("time", newMeal.time)
            }
            mealsArray.put(mealJson)

            // Save the updated meal list back to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("meals", mealsArray.toString())
            editor.apply()

            Toast.makeText(this, "Meal details saved successfully", Toast.LENGTH_SHORT).show()
            clearInputFields()
        } else {
            Toast.makeText(this, "Please enter all required fields", Toast.LENGTH_SHORT).show()
        }
    }


    private fun clearInputFields() {
        mealNameEditText.text.clear()
        mealDescriptionEditText.text.clear()
        caloriesEditText.text.clear()
        dateEditText.text.clear()
        timeEditText.text.clear()
        mealTypeSpinner.setSelection(0)  // Reset spinner to first item
    }
}
