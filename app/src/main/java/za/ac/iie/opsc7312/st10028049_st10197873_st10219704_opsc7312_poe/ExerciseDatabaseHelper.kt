package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ExerciseDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "exercises.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_EXERCISES = "exercises"
        private const val COLUMN_ID = "id"
        private const val COLUMN_UID = "uid"
        private const val COLUMN_EXERCISE_NAME = "exercise_name"
        private const val COLUMN_SETS = "sets"
        private const val COLUMN_REPS = "reps"
        private const val COLUMN_CALORIES_BURNT = "calories_burnt"
        private const val COLUMN_NOTES = "notes"
        private const val COLUMN_CREATED_AT = "created_at"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE $TABLE_EXERCISES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_UID TEXT,
                $COLUMN_EXERCISE_NAME TEXT,
                $COLUMN_SETS TEXT,
                $COLUMN_REPS TEXT,
                $COLUMN_CALORIES_BURNT TEXT,
                $COLUMN_NOTES TEXT,
                $COLUMN_CREATED_AT TEXT
            )
        """.trimIndent()
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_EXERCISES")
        onCreate(db)
    }

    // Method to insert exercise data into the local database
    fun insertExercise(uid: String, exerciseName: String, sets: String, reps: String, caloriesBurnt: String, notes: String, createdAt: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_UID, uid)
            put(COLUMN_EXERCISE_NAME, exerciseName)
            put(COLUMN_SETS, sets)
            put(COLUMN_REPS, reps)
            put(COLUMN_CALORIES_BURNT, caloriesBurnt)
            put(COLUMN_NOTES, notes)
            put(COLUMN_CREATED_AT, createdAt)
        }
        val result = db.insert(TABLE_EXERCISES, null, values)
        db.close()
        return result != -1L
    }

    // Method to get all exercises from the local database
    fun getAllExercises(): List<Map<String, String>> {
        val exercises = mutableListOf<Map<String, String>>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_EXERCISES", null)
        if (cursor.moveToFirst()) {
            do {
                exercises.add(mapOf(
                    "uid" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UID)),
                    "exercise_name" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXERCISE_NAME)),
                    "sets" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SETS)),
                    "reps" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_REPS)),
                    "calories_burnt" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CALORIES_BURNT)),
                    "notes" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTES)),
                    "created_at" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CREATED_AT))
                ))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return exercises
    }

    // Method to clear all exercises from the local database after sync
    fun clearExercises() {
        val db = writableDatabase
        db.delete(TABLE_EXERCISES, null, null)
        db.close()
    }
}
