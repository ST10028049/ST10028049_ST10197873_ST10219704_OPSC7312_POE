package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class AddGoals : AppCompatActivity() {

    private lateinit var editTextGoalName: EditText
    private lateinit var editTextGoalDescription: EditText
    private lateinit var spinnerGoalType: Spinner
    private lateinit var editTextCalories: EditText
    private lateinit var editTextDate: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addgoals)

        // Initialize UI components
        editTextGoalName = findViewById(R.id.input_goal_name)
        editTextGoalDescription = findViewById(R.id.input_goal_description)
        spinnerGoalType = findViewById(R.id.spinner_goal_type)
        editTextCalories = findViewById(R.id.input_calories)
        editTextDate = findViewById(R.id.input_date)
        buttonSubmit = findViewById(R.id.button_submit)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE)

        // Date picker
        editTextDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                editTextDate.setText(selectedDate)
            }, year, month, day).show()
        }

        buttonSubmit.setOnClickListener {
            captureAndStoreGoalDetails()
        }
    }

    private fun captureAndStoreGoalDetails() {
        val uid = sharedPreferences.getString("uid", null)

        if (uid == null) {
            Toast.makeText(this, "User is not logged in!", Toast.LENGTH_SHORT).show()
            return
        }

        // Capture goal details from UI inputs
        val goalName = editTextGoalName.text.toString().trim()
        val goalDescription = editTextGoalDescription.text.toString().trim()
        val goalType = spinnerGoalType.selectedItem.toString()
        val calories = editTextCalories.text.toString().toIntOrNull() ?: 0
        val date = editTextDate.text.toString().trim()

        // Validate input
        if (goalName.isNotEmpty() && date.isNotEmpty()) {
            // Create a new Goal object (you might want to define this class if not already done)
            val newGoal = Goal(
                name = goalName,
                description = goalDescription,
                type = goalType,
                calories = calories,
                date = date
            )

            // Retrieve the existing goals list as a JSON array
            val goalsString = sharedPreferences.getString("goals", "[]")
            val goalsArray = JSONArray(goalsString)

            // Add new goal to the JSON array
            val goalJson = JSONObject().apply {
                put("uid", uid)
                put("goalName", newGoal.name)
                put("goalDescription", newGoal.description)
                put("goalType", newGoal.type)
                put("calories", newGoal.calories)
                put("date", newGoal.date)
            }
            goalsArray.put(goalJson)

            // Save the updated goals list back to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("goals", goalsArray.toString())
            editor.apply()

            Toast.makeText(this, "Goal saved successfully!", Toast.LENGTH_SHORT).show()
            clearInputFields()
        } else {
            Toast.makeText(this, "Please enter all required fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearInputFields() {
        editTextGoalName.text.clear()
        editTextGoalDescription.text.clear()
        editTextCalories.text.clear()
        editTextDate.text.clear()
        spinnerGoalType.setSelection(0)  // Reset spinner to first item
    }
}
