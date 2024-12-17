package com.example.beauty_salon_belyaev

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val textViewPhone: TextView = findViewById(R.id.textViewPhone)
        val textViewEmail: TextView = findViewById(R.id.textViewEmail)
        val textViewAddress: TextView = findViewById(R.id.textViewAddress)

        textViewPhone.text = "Телефон: +7(123)456-78-90"
        textViewEmail.text = "Email: example@exam15.com"
        textViewAddress.text = "Адрес: г.Москва, ул.Примерная, д.1"

        val buttonBack: Button = findViewById(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish()
        }
    }
}