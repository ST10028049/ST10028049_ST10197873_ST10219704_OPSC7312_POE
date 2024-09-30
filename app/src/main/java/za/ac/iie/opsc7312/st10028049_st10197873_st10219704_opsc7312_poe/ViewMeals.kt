package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class ViewMealsActivity : AppCompatActivity() {

    private lateinit var mealListView: ListView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var mealAdapter: MealAdapter
    private var mealList = mutableListOf<Meal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_meals)

        mealListView = findViewById(R.id.meal_list_view)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE)
        val uid = sharedPreferences.getString("uid", null)

        if (uid != null) {
            loadMealsForUser(uid)
        }

        mealAdapter = MealAdapter(this, mealList)
        mealListView.adapter = mealAdapter

        // Handle click event on the eye icon to view meal details
        mealListView.setOnItemClickListener { parent, view, position, id ->
            val selectedMeal = mealList[position]

            // Create a JSONObject for the selected meal
            val mealDetails = JSONObject().apply {
                put("mealName", selectedMeal.name)
                put("mealDescription", selectedMeal.description)
                put("mealType", selectedMeal.type)
                put("calories", selectedMeal.calories)
                put("date", selectedMeal.date)
                put("time", selectedMeal.time)
            }

            // Pass the meal details as a JSON string
            val intent = Intent(this, MealInfo::class.java)
            intent.putExtra("mealDetails", mealDetails.toString())
            startActivity(intent)
        }

    }

    private fun loadMealsForUser(uid: String) {
        val mealsString = sharedPreferences.getString("meals", "[]") ?: "[]"
        val mealsArray = JSONArray(mealsString)

        for (i in 0 until mealsArray.length()) {
            val mealObject = mealsArray.getJSONObject(i)
            if (mealObject.getString("uid") == uid) {
                val meal = Meal(
                    mealObject.getString("mealName"),
                    mealObject.getString("mealDescription"),
                    mealObject.getString("mealType"),
                    mealObject.getInt("calories"),
                    mealObject.getString("date"),
                    mealObject.getString("time")
                )
                mealList.add(meal)
            }
        }

        // Sort meals by most recent (date + time)
        mealList.sortByDescending { it.dateTime }
    }
}
