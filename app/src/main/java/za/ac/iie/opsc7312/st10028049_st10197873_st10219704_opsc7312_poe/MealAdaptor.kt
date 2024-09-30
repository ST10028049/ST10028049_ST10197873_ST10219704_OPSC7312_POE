package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MealAdapter(private val context: Context, private val mealList: List<Meal>) : BaseAdapter() {

    override fun getCount(): Int = mealList.size

    override fun getItem(position: Int): Any = mealList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.meal_list_item, parent, false)

        val mealNameTextView: TextView = view.findViewById(R.id.meal_name_text_view)
        val mealDateTimeTextView: TextView = view.findViewById(R.id.meal_date_time_text_view)
        val viewMealDetailsIcon: ImageView = view.findViewById(R.id.view_meal_details_icon)

        val meal = mealList[position]

        mealNameTextView.text = meal.name
        mealDateTimeTextView.text = "${meal.date} ${meal.time}"

        // Set click listener for eye icon (handled in the activity)

        return view
    }
}
