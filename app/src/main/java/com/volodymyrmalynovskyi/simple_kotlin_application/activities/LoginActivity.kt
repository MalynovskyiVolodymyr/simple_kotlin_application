package com.volodymyrmalynovskyi.simple_kotlin_application.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.volodymyrmalynovskyi.simple_kotlin_application.MainActivity
import com.volodymyrmalynovskyi.simple_kotlin_application.R
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passEditText: EditText

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_login)

        emailEditText = findViewById<EditText>(R.id.username)
        passEditText = findViewById<EditText>(R.id.password)
    }

    fun checkLogin(view: View){
        val email: String = emailEditText.text.toString()

        if(!isValidEmail(email)){
            emailEditText.error = "Invalid Email"
        }

        val pass = passEditText.text.toString()

        if(!isValidPassword(pass)){
            passEditText.error = "Password cannot be empty"
        }

        if(isValidEmail(email) && isValidPassword(pass)){
            //start new Activity
            val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
            this@LoginActivity.startActivity(mainIntent)
            finish()
        }
    }

    private fun isValidEmail(email: String): Boolean{
        val EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

        val pattern: Pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private fun isValidPassword(pass: String?): Boolean{
        if(pass != null && pass.length >= 4){
            return true
        }
        return false
    }
}