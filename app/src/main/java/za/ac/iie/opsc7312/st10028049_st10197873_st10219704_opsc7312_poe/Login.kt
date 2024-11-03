package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import api.ApiClient
import api.ApiService
import api.LoginRequest
import api.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var textViewSignUp: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        buttonLogin = findViewById(R.id.button2)
        textViewSignUp = findViewById(R.id.signup_link)

        sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE)

        buttonLogin.setOnClickListener {
            loginUser()
        }

        textViewSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser() {
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        val apiService = ApiClient.getClient().create(ApiService::class.java)
        val loginRequest = LoginRequest(email, password)
        val call = apiService.loginUser(loginRequest)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val uid = response.body()?.uid
                    Toast.makeText(this@Login, "Login successful", Toast.LENGTH_SHORT).show()

                    // Save UID in SharedPreferences
                    val editor = sharedPreferences.edit()
                    editor.putString("uid", uid)
                    editor.apply()

                    val intent = Intent(this@Login, Main_Menu::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@Login, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@Login, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
