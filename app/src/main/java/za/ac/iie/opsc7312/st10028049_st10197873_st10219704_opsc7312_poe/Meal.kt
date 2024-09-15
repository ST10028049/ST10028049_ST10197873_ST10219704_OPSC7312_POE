package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.SharedPreferences
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

class MealDetailsActivity : AppCompatActivity() {

    private lateinit var mealNameEditText: EditText
    private lateinit var mealDescriptionEditText: EditText
    private lateinit var mealTypeSpinner: Spinner
    private lateinit var submitButton: Button
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_details)

        mealNameEditText = findViewById(R.id.input_meal_name)
        mealDescriptionEditText = findViewById(R.id.input_meal_description)
        mealTypeSpinner = findViewById(R.id.spinner_meal_type)
        submitButton = findViewById(R.id.button_submit)

        sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE)
        apiService = ApiClient.getClient().create(ApiService::class.java)

        submitButton.setOnClickListener {
            captureAndSendMealDetails()
        }
    }

    private fun captureAndSendMealDetails() {
        val mealName = mealNameEditText.text.toString().trim()
        val mealDescription = mealDescriptionEditText.text.toString().trim()
        val mealType = mealTypeSpinner.selectedItem.toString()
        val uid = sharedPreferences.getString("uid", null)

        if (mealName.isNotEmpty() && uid != null) {
            // Prepare meal details
            val mealDetails = MealRequest(uid, mealName, mealDescription, mealType)

            // Send meal details to API
            apiService.addMeal(mealDetails).enqueue(object : Callback<MealResponse> {
                override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@MealDetailsActivity, "Meal added successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MealDetailsActivity, "Failed to add meal", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                    Toast.makeText(this@MealDetailsActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "Please enter all required fields", Toast.LENGTH_SHORT).show()
        }
    }
}
