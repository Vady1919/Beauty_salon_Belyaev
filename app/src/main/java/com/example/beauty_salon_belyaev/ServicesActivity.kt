package com.example.beauty_salon_belyaev

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ServicesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)

        val servicesList: ListView = findViewById(R.id.services_list)
        val btnBack: Button = findViewById(R.id.btnBack)

        val services = listOf(
            Service("Стрижка волос", 1200.0),
            Service("Укладка волос", 1700.0),
            Service("Маникюр", 2000.0),
            Service("Педикюр", 2800.0),
            Service("Массаж", 3000.0),
            Service("Наращивание ресниц", 1400.0),
            Service("Перманентный макияж", 2600.0),
            Service("Мелирование", 2500.0)
        )

        // Установка пользовательского адаптера
        val adapter = ServiceAdapter(this, services)
        servicesList.adapter = adapter

        btnBack.setOnClickListener {
            finish()
        }
    }

    // Адаптер для отображения различных услуг с стоимостью
    private class ServiceAdapter(private val context: ServicesActivity, private val services: List<Service>) : ArrayAdapter<Service>(context, 0, services) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val service = getItem(position)

            val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)

            val text1: TextView = view.findViewById(android.R.id.text1)
            val text2: TextView = view.findViewById(android.R.id.text2)

            text1.text = service?.name
            text2.text = "Цена: ${service?.price} руб."

            return view
        }
    }
}