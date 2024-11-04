package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import api.ApiClient
import api.ApiService
import api.MealRequest
import api.MealResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

// This code was adapted from Solution Code Android and ChatGPT
// YouTube video: how to access data offline in Firebase android studio\Enabline OfflineCapabilitiesAndroid - Firebase
// YouTube link: https://youtu.be/_GqFDFi9NuA?si=PIzLv32X6ysuzZbM
// Solution Code Android: https://www.youtube.com/@SolutionCodeAndroid
// ChatGPT Link: https://chatgpt.com/


class MealDetailsActivity : AppCompatActivity() {

    private lateinit var mealNameEditText: EditText
    private lateinit var mealDescriptionEditText: EditText
    private lateinit var mealTypeSpinner: Spinner
    private lateinit var submitButton: Button
    private lateinit var dateEditText: EditText
    private lateinit var timeEditText: EditText
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var dbHelper: DatabaseHelper

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (NetworkUtils.isNetworkAvailable(context!!)) {
                syncOfflineMeals()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_details)

        mealNameEditText = findViewById(R.id.input_meal_name)
        mealDescriptionEditText = findViewById(R.id.input_meal_description)
        mealTypeSpinner = findViewById(R.id.spinner_meal_type)
        dateEditText = findViewById(R.id.input_date)
        timeEditText = findViewById(R.id.input_time)
        submitButton = findViewById(R.id.button_submit)
        sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE)
        dbHelper = DatabaseHelper(this)

        // Set up Date and Time pickers
        dateEditText.setOnClickListener { showDatePickerDialog() }
        timeEditText.setOnClickListener { showTimePickerDialog() }

        submitButton.setOnClickListener {
            captureAndStoreMealDetails()
        }

        // Register network receiver
        registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        // Initial sync attempt if online
        if (NetworkUtils.isNetworkAvailable(this)) {
            syncOfflineMeals()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            dateEditText.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            timeEditText.setText(String.format("%02d:%02d", selectedHour, selectedMinute))
        }, hour, minute, true)

        timePickerDialog.show()
    }

    private fun captureAndStoreMealDetails() {
        val uid = sharedPreferences.getString("uid", null)
        if (uid == null) {
            Toast.makeText(this, "User not logged in. Please log in to submit meal.", Toast.LENGTH_SHORT).show()
            return
        }

        val mealName = mealNameEditText.text.toString().trim()
        val mealDescription = mealDescriptionEditText.text.toString().trim()
        val mealType = mealTypeSpinner.selectedItem.toString()

        if (mealName.isNotEmpty()) {
            if (NetworkUtils.isNetworkAvailable(this)) {
                sendMealToApi(uid, mealName, mealDescription, mealType)
            } else {
                dbHelper.insertMeal(uid, mealName, mealDescription, mealType)
                Toast.makeText(this, "Meal saved locally.", Toast.LENGTH_SHORT).show()
            }
            clearInputFields()
        } else {
            Toast.makeText(this, "Please enter all required fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendMealToApi(uid: String, name: String, description: String, type: String) {
        val apiService = ApiClient.getClient().create(ApiService::class.java)
        val mealRequest = MealRequest(uid, name, description, type)
        val call = apiService.addMeal(mealRequest)

        call.enqueue(object : Callback<MealResponse> {
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MealDetailsActivity, "Meal submitted successfully.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Toast.makeText(this@MealDetailsActivity, "Failed to submit meal.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun syncOfflineMeals() {
        val offlineMeals = dbHelper.getAllMeals()
        if (offlineMeals.isNotEmpty()) {
            offlineMeals.forEach { meal ->
                sendMealToApi(
                    meal["uid"]!!,
                    meal["name"]!!,
                    meal["description"]!!,
                    meal["type"]!!
                )
            }
            dbHelper.clearMeals()
            Toast.makeText(this, "Offline meals synced.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearInputFields() {
        mealNameEditText.text.clear()
        mealDescriptionEditText.text.clear()
        mealTypeSpinner.setSelection(0)
        dateEditText.text.clear()
        timeEditText.text.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkReceiver)
    }
}
