package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import api.ApiClient
import api.ApiService
import api.ExerciseRequest
import api.ExerciseResponse
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class ExerciseTrackerActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var apiService: ApiService
    private lateinit var dateEditText: EditText
    private lateinit var startTimeEditText: EditText
    private lateinit var endTimeEditText: EditText
    private lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_tracker)

        uid = intent.getStringExtra("UID").toString()

        auth = FirebaseAuth.getInstance()
        apiService = ApiClient.getClient().create(ApiService::class.java)

        dateEditText = findViewById(R.id.date_picker)
        startTimeEditText = findViewById(R.id.start_time_picker)
        endTimeEditText = findViewById(R.id.end_time_picker)
        val logExerciseButton: Button = findViewById(R.id.log_exercise_button)

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
        val exerciseName: String = findViewById<EditText>(R.id.exercise_name).text.toString()
        val sets: Int = findViewById<EditText>(R.id.sets_input).text.toString().toIntOrNull() ?: 0
        val reps: Int = findViewById<EditText>(R.id.reps_input).text.toString().toIntOrNull() ?: 0
        val caloriesBurnt: Int = findViewById<EditText>(R.id.calories_burnt).text.toString().toIntOrNull() ?: 0
        val date: String = dateEditText.text.toString()
        val startTime: String = startTimeEditText.text.toString()
        val endTime: String = endTimeEditText.text.toString()
        val notes: String = findViewById<EditText>(R.id.notes).text.toString()

        if (uid.isEmpty()) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
            return
        }

        val startDateTime = getDateTime(date, startTime)
        val endDateTime = getDateTime(date, endTime)
        val duration = calculateDuration(startDateTime, endDateTime)

        val exerciseRequest = ExerciseRequest().apply {
            this.uid = uid
            this.exerciseName = exerciseName
            this.sets = sets
            this.reps = reps
            this.caloriesBurnt = caloriesBurnt
            this.date = startDateTime  // Set Date object
            this.startTime = startDateTime  // Set Date object
            this.endTime = endDateTime  // Set Date object
            this.duration = duration
            this.notes = notes
        }

        apiService.addExercise(exerciseRequest).enqueue(object : Callback<ExerciseResponse> {
            override fun onResponse(call: Call<ExerciseResponse>, response: Response<ExerciseResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ExerciseTrackerActivity, "Exercise logged successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ExerciseTrackerActivity, "Failed to log exercise", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ExerciseResponse>, t: Throwable) {
                Toast.makeText(this@ExerciseTrackerActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getDateTime(date: String, time: String): Date {
        return try {
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            val dateTimeString = "$date $time"
            format.parse(dateTimeString) ?: Date()
        } catch (e: Exception) {
            Date()  // fallback to current date and time
        }
    }

    private fun calculateDuration(startTime: Date, endTime: Date): Int {
        val durationMillis = endTime.time - startTime.time
        return (durationMillis / 60000).toInt()  // Duration in minutes
    }
}
