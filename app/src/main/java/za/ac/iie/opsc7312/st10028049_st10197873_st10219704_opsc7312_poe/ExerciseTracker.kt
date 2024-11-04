package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import api.ApiClient
import api.ApiService
import api.ExerciseRequest
import api.ExerciseResponse
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe.NetworkUtils.isNetworkAvailable
import java.util.*

class ExerciseTrackerActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var apiService: ApiService
    private lateinit var dateEditText: EditText
    private lateinit var startTimeEditText: EditText
    private lateinit var endTimeEditText: EditText
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_tracker)

        auth = FirebaseAuth.getInstance()
        apiService = ApiClient.getClient().create(ApiService::class.java)

        dateEditText = findViewById(R.id.date_picker)
        startTimeEditText = findViewById(R.id.start_time_picker)
        endTimeEditText = findViewById(R.id.end_time_picker)
        val logExerciseButton: Button = findViewById(R.id.save_button)
        sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE)

        dateEditText.setOnClickListener { showDatePicker() }
        startTimeEditText.setOnClickListener { showTimePicker(startTimeEditText) }
        endTimeEditText.setOnClickListener { showTimePicker(endTimeEditText) }

        logExerciseButton.setOnClickListener {
            logExercise()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { _, year, month, dayOfMonth ->
            val date = "$year-${month + 1}-$dayOfMonth"
            dateEditText.setText(date)
        }, year, month, day).show()
    }

    private fun showTimePicker(editText: EditText) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(this, { _, hourOfDay, minute ->
            val time = String.format("%02d:%02d", hourOfDay, minute)
            editText.setText(time)
        }, hour, minute, true).show()
    }

    private fun logExercise() {
        val uid = sharedPreferences.getString("uid", null)
        val exerciseName = findViewById<EditText>(R.id.exercise_name).text.toString()
        val sets = findViewById<EditText>(R.id.sets_input).text.toString()
        val reps = findViewById<EditText>(R.id.reps_input).text.toString()
        val caloriesBurnt = findViewById<EditText>(R.id.calories_burnt).text.toString()
        val notes = findViewById<EditText>(R.id.notes_input).text.toString()
        val createdAt = Calendar.getInstance().time.toString()  // Or format as ISO if required by API

        if (uid == null) {
            Toast.makeText(this, "User not logged in.", Toast.LENGTH_SHORT).show()
            return
        }

        val exerciseRequest = ExerciseRequest().apply {
            this.uid = uid
            this.exerciseName = exerciseName
            this.sets = sets
            this.reps = reps
            this.caloriesBurnt = caloriesBurnt
            this.notes = notes
            this.createdAt = createdAt
        }

        if (isNetworkAvailable(this)) {
            // Send data to API
            apiService.trackExercise(exerciseRequest).enqueue(object : Callback<ExerciseResponse> {
                override fun onResponse(call: Call<ExerciseResponse>, response: Response<ExerciseResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@ExerciseTrackerActivity, "Exercise tracked successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@ExerciseTrackerActivity, "Failed to track exercise: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ExerciseResponse>, t: Throwable) {
                    Toast.makeText(this@ExerciseTrackerActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            // Store data locally if offline
            val dbHelper = ExerciseDatabaseHelper(this)
            val isInserted = dbHelper.insertExercise(uid, exerciseName, sets, reps, caloriesBurnt, notes, createdAt)
            if (isInserted) {
                Toast.makeText(this, "Exercise saved locally. Will sync when online.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to save exercise locally.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun syncOfflineExercises() {
        val dbHelper = ExerciseDatabaseHelper(this)
        val exercises = dbHelper.getAllExercises()

        for (exercise in exercises) {
            val exerciseRequest = ExerciseRequest().apply {
                this.uid = exercise["uid"] ?: ""
                this.exerciseName = exercise["exercise_name"] ?: ""
                this.sets = exercise["sets"] ?: ""
                this.reps = exercise["reps"] ?: ""
                this.caloriesBurnt = exercise["calories_burnt"] ?: ""
                this.notes = exercise["notes"] ?: ""
                this.createdAt = exercise["created_at"] ?: ""
            }

            apiService.trackExercise(exerciseRequest).enqueue(object : Callback<ExerciseResponse> {
                override fun onResponse(call: Call<ExerciseResponse>, response: Response<ExerciseResponse>) {
                    if (response.isSuccessful) {
                        // Clear data from the local database after successful sync
                        dbHelper.clearExercises()
                        Toast.makeText(this@ExerciseTrackerActivity, "Offline exercises synced successfully", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ExerciseResponse>, t: Throwable) {
                    Toast.makeText(this@ExerciseTrackerActivity, "Sync error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }




}
