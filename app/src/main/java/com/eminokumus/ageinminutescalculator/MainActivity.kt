package com.eminokumus.ageinminutescalculator

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eminokumus.ageinminutescalculator.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectDateBtn.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        //month index starts with zero, so we add one
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            { view, selectedYear, selectedMonth, selectedDay ->
                val selectedDateStr = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.dateText.text = selectedDateStr

                val selectedDate = convertStringToDate(selectedDateStr)

                val selectedDateInMinutes = selectedDate?.time?.div(60000)

                val currentDate = getCurrentDate()

                val currentDateInMinutes = currentDate?.time?.div(60000)

                val differenceInMinutes = currentDateInMinutes?.minus(selectedDateInMinutes ?: currentDateInMinutes)

                binding.minutesText.text = differenceInMinutes.toString()

            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }


    private fun convertStringToDate(dateString: String): Date? {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        return format.parse(dateString)
    }

    private fun getCurrentDate(): Date? {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        return format.parse(format.format(System.currentTimeMillis()))
    }
}