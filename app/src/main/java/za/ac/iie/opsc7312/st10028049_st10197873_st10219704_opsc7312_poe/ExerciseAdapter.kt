package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter for displaying a list of exercises in a RecyclerView  (pushpender007.2024)
class ExerciseAdapter(private val exercises: List<Exercise>) :
    RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    // ViewHolder class to hold and manage the view references for each exercise item
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val exerciseImage: ImageView = view.findViewById(R.id.exerciseImage)
        val exerciseName: TextView = view.findViewById(R.id.exerciseName)
        val exerciseDifficulty: TextView = view.findViewById(R.id.exerciseDifficulty)
        val exerciseDescription: TextView = view.findViewById(R.id.exerciseDescription)
    }

    // Creates and inflates the layout for each exercise item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the exercise item layout XML (R.layout.exercise)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise, parent, false)
            return ViewHolder(view)
    }

    // Binds the data (exercise) to the views in the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val exercise = exercises[position]
        holder.exerciseName.text = exercise.name
        holder.exerciseDifficulty.text = exercise.difficulty
        holder.exerciseDescription.text = exercise.description
        holder.exerciseImage.setImageResource(exercise.imageResId)
    }

    // Returns the total number of exercise items
    override fun getItemCount() = exercises.size
}

//Reference List
/*pushpender007.2024.Android RecyclerView in Kotlin, 30 August 2024. [Online]. Available at: https://www.geeksforgeeks.org/android-recyclerview-in-kotlin/[Accessed 25 September 2024].
*/