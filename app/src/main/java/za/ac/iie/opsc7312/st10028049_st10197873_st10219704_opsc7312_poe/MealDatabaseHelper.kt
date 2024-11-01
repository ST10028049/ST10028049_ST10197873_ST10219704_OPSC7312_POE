// DatabaseHelper.kt
package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "meals.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_MEALS = "meals"
        private const val COLUMN_UID = "uid"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_TYPE = "type"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_MEALS (" +
                "$COLUMN_UID TEXT, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_DESCRIPTION TEXT, " +
                "$COLUMN_TYPE TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MEALS")
        onCreate(db)
    }

    fun insertMeal(uid: String, name: String, description: String, type: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_UID, uid)
            put(COLUMN_NAME, name)
            put(COLUMN_DESCRIPTION, description)
            put(COLUMN_TYPE, type)
        }
        val result = db.insert(TABLE_MEALS, null, values)
        db.close()
        return result != -1L
    }

    fun getAllMeals(): List<Map<String, String>> {
        val meals = mutableListOf<Map<String, String>>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_MEALS", null)
        if (cursor.moveToFirst()) {
            do {
                meals.add(mapOf(
                    "uid" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UID)),
                    "name" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    "description" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                    "type" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE))
                ))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return meals
    }

    fun clearMeals() {
        val db = writableDatabase
        db.delete(TABLE_MEALS, null, null)
        db.close()
    }
}
