package com.example.beauty_salon_belyaev

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Spinner
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class ReservationActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var spinnerServices: Spinner
    private lateinit var buttonReserve: Button
    private lateinit var textViewSelectDate: TextView
    private var selectedDate: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        editTextName = findViewById(R.id.editTextName)
        editTextPhone = findViewById(R.id.editTextPhone)
        spinnerServices = findViewById(R.id.spinnerServices)
        buttonReserve = findViewById(R.id.buttonReserve)
        val btnBack: Button = findViewById(R.id.btnBack)
        textViewSelectDate = findViewById(R.id.textViewSelectDate)


        editTextPhone.setText("+7(___)___-__-__") // Установите текст по умолчанию


        val masters = listOf(
            "Любой мастер",
            "Лебедев Тимур Георгиевич",
            "Образумова Дарья Дмитриевна",
            "Мельников Сергей Павлович",
            "Иванова Анастасия Кирилловна"
        )

        // Инициализация адаптера для выборки мастера
        val mastersAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, masters)
        mastersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinnerMasters = findViewById<Spinner>(R.id.spinnerMasters)
        spinnerMasters.adapter = mastersAdapter


        // Наполните Spinner
        val services = arrayOf(
            "Стрижка волос",
            "Укладка волос",
            "Маникюр",
            "Педикюр",
            "Массаж",
            "Наращивание ресниц",
            "Перманентный макияж",
            "Мелирование"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, services)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerServices.adapter = adapter

        // Обработчик нажатия поля выбора даты
        textViewSelectDate.setOnClickListener {
            showDatePickerDialog()
        }

        // Обработчик нажатия на кнопку резервирования
        buttonReserve.setOnClickListener {
            val name = editTextName.text.toString()
            val phone = editTextPhone.text.toString()
            val selectedService = spinnerServices.selectedItem.toString()
            val selectedMaster = spinnerMasters.selectedItem.toString()

            // Проверка на пустые поля
            if (name.isEmpty() || phone.isEmpty() || selectedDate == null) {
                Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Проверка формата телефона
            if (!phone.matches(Regex("^\\+7\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$"))) {
                Toast.makeText(
                    this,
                    "Введите корректный номер телефона в формате +7(647)642-72-92",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            // Отображение диалогового окна подтверждения
            showConfirmationDialog(
                name,
                phone,
                selectedService,
                selectedDate,
                selectedMaster
            )
        }

        btnBack.setOnClickListener {
            finish()
        }

        editTextPhone.addTextChangedListener(object : TextWatcher {
            private var isFormatting = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (!isFormatting) {
                    isFormatting = true
                    val rawInput = editTextPhone.text.toString().replace(Regex("[^\\d]"), "")
                    val formatted = StringBuilder()

                    if (rawInput.isNotEmpty()) {
                        formatted.append("+7")
                        if (rawInput.length >= 1) formatted.append("(")
                        if (rawInput.length >= 2) formatted.append(rawInput.substring(1, minOf(4, rawInput.length)))
                        if (rawInput.length >= 4) formatted.append(")")
                        if (rawInput.length >= 4) formatted.append(rawInput.substring(4, minOf(7, rawInput.length)))
                        if (rawInput.length >= 7) formatted.append("-")
                        if (rawInput.length >= 8) formatted.append(rawInput.substring(7, minOf(9, rawInput.length)))
                        if (rawInput.length >= 9) formatted.append("-")
                        if (rawInput.length >= 10) formatted.append(rawInput.substring(9, minOf(11, rawInput.length)))
                    }

                    // Устанавливаем отформатированный текст обратно
                    editTextPhone.setText(formatted.toString())
                    editTextPhone.setSelection(formatted.length)
                    isFormatting = false
                }
            }
        })
    }

    private fun showConfirmationDialog(
        name: String,
        phone: String,
        service: String,
        date: String,
        master: String
    ) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_confirmation, null)
        val textViewMessage: TextView = dialogView.findViewById(R.id.textViewConfirmationMessage)

        textViewMessage.text = "Вы уверены, что заполнили данные правильно?\n\n" +
                "Имя: $name\n" +
                "Телефон: $phone\n" +
                "Услуга: $service\n" +
                "Дата: $date\n" +
                "Мастер: $master"

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)

        val dialog = dialogBuilder.create()

        dialogView.findViewById<Button>(R.id.buttonYes).setOnClickListener {
            dialog.dismiss()
            textViewSuccessMessage(service, date, name, phone, master)
            resetFields()
        }

        dialogView.findViewById<Button>(R.id.buttonNo).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun resetFields() {
        editTextName.text.clear()
        editTextPhone.setText("+7(___)___-__-__")
        spinnerServices.setSelection(0)
        textViewSelectDate.text = ""
        selectedDate = ""
    }

    private fun textViewSuccessMessage(service: String, date: String, name: String, master: String, phone: String) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_success, null)
        val textViewMessage: TextView = dialogView.findViewById(R.id.textViewSuccessMessage)

        textViewMessage.text =
            "Вы записаны на услугу: $service на $date. Мы свяжемся с вами, $name."

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)

        val dialog = dialogBuilder.create()
        Toast.makeText(this, "Запись успешна!", Toast.LENGTH_SHORT).show()
        dialogView.findViewById<Button>(R.id.buttonOk).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
        saveRecord(service, date, name, master, phone)
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            textViewSelectDate.text = selectedDate
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun saveRecord(service: String, date: String, name: String, phone: String, master: String) {
        val sharedPreferences = getSharedPreferences("Records", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Получаем текущие записи
        val currentRecords = sharedPreferences.getString("myRecords", "")
        val newRecord = "Имя: $name " +
                        "Телефон: $phone " +
                        "Дата: $date " +
                        "Мастер: $master " +
                        "Услуга: $service "

        // Сохраняем новые записи
        val updatedRecords = if (currentRecords.isNullOrEmpty()) {
            newRecord
        } else {
            "$currentRecords\n$newRecord"
        }

        editor.putString("myRecords", updatedRecords)
        editor.apply()

    }
}


