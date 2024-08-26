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
    private fun clickDatePicker(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        //month index starts with zero, so we add one
        val month = calendar.get(Calendar.MONTH)+1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            { view, selectedYear, selectedMonth, selectedDay ->
                val selectedDateStr = "$selectedDay/$selectedMonth/$selectedYear"
                binding.dateText.text = selectedDateStr


            },
            year,
            month,
            day
        ).show()
    }


}