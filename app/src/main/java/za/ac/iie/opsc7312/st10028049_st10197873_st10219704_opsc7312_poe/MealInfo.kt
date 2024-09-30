package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class MealInfo : AppCompatActivity() {

    private lateinit var mealNameTextView: TextView
    private lateinit var mealDescriptionTextView: TextView
    private lateinit var mealTypeTextView: TextView
    private lateinit var mealCaloriesTextView: TextView
    private lateinit var mealDateTextView: TextView
    private lateinit var mealTimeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_info)

        mealNameTextView = findViewById(R.id.meal_name_text_view)
        mealDescriptionTextView = findViewById(R.id.meal_description_text_view)
        mealTypeTextView = findViewById(R.id.meal_type_text_view)
        mealCaloriesTextView = findViewById(R.id.meal_calories_text_view)
        mealDateTextView = findViewById(R.id.meal_date_text_view)
        mealTimeTextView = findViewById(R.id.meal_time_text_view)

        // Get meal details passed from previous activity
        val mealDetailsString = intent.getStringExtra("mealDetails")
        if (mealDetailsString != null) {
            try {
                val mealDetails = JSONObject(mealDetailsString)

                mealNameTextView.text = mealDetails.getString("mealName")
                mealDescriptionTextView.text = mealDetails.getString("mealDescription")
                mealTypeTextView.text = mealDetails.getString("mealType")
                mealCaloriesTextView.text = mealDetails.getInt("calories").toString()
                mealDateTextView.text = mealDetails.getString("date")
                mealTimeTextView.text = mealDetails.getString("time")
            } catch (e: Exception) {
                e.printStackTrace()  // This will help debug any potential crashes
            }
        }
    }

}
