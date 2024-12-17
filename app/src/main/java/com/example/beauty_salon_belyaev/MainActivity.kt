package com.example.beauty_salon_belyaev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnServices: Button = findViewById(R.id.btn_services)
        val btnReservation: Button = findViewById(R.id.btn_reservation)
        val btnContact: Button = findViewById(R.id.btn_contact)
        val buttonMasters: Button = findViewById(R.id.buttonMasters)

        btnServices.setOnClickListener {
            val intent = Intent(this, ServicesActivity::class.java)
            startActivity(intent)
        }

        btnReservation.setOnClickListener {
            val intent = Intent(this, ReservationActivity::class.java)
            startActivity(intent)
        }

        btnContact.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }

        buttonMasters.setOnClickListener {
            val intent = Intent(this, MastersActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.buttonShowReservations).setOnClickListener {
            val intent = Intent(this, MyRecordsActivity::class.java)
            startActivity(intent)
        }
    }
}
