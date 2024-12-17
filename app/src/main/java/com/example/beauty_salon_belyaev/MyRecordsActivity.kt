package com.example.beauty_salon_belyaev

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyRecordsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recordsAdapter: RecordsAdapter
    private val recordsList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_records)

        recyclerView = findViewById(R.id.recyclerViewMyRecords)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recordsAdapter = RecordsAdapter(recordsList)
        recyclerView.adapter = recordsAdapter

        loadRecords()

        val buttonClear: Button = findViewById(R.id.buttonClear)
        buttonClear.setOnClickListener {
            clearRecords()
        }

        val buttonBack: Button = findViewById(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun loadRecords() {
        val sharedPreferences = getSharedPreferences("Records", MODE_PRIVATE)
        val records = sharedPreferences.getString("myRecords", "")

        if (!records.isNullOrEmpty()) {
            recordsList.addAll(records.split("\n"))
        }
        recordsAdapter.notifyDataSetChanged()
    }

    private fun clearRecords() {
        recordsList.clear()
        recordsAdapter.notifyDataSetChanged()

        // Удаляем записи из SharedPreferences
        val sharedPreferences = getSharedPreferences("Records", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("myRecords") // Удаляем сохраненные записи
        editor.apply()
    }
}