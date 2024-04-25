package com.example.reservation0418

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.Chronometer
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import android.widget.TimePicker

class MainActivity : AppCompatActivity() {
    lateinit var chrono : Chronometer
    lateinit var rg : RadioGroup
    lateinit var calendar : DatePicker
    lateinit var timePick : TimePicker
    lateinit var textResult : TextView
    var selectedYear : Int = 0
    var selectedMonth : Int = 0
    var selectedDay : Int = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chrono = findViewById<Chronometer>(R.id.chrono)
        rg = findViewById<RadioGroup>(R.id.rg)
        calendar = findViewById<DatePicker>(R.id.calendar)
        timePick = findViewById<TimePicker>(R.id.timePick)
        textResult = findViewById<TextView>(R.id.textResult)

        rg.visibility = View.INVISIBLE
        calendar.visibility = View.INVISIBLE
        timePick.visibility = View.INVISIBLE

        chrono.setOnClickListener{
            chrono.base = SystemClock.elapsedRealtime()
            chrono.start()
            chrono.setTextColor(Color.MAGENTA)
            rg.visibility = View.VISIBLE
        }

        rg.setOnCheckedChangeListener(rgListener)

        textResult.setOnLongClickListener {
            chrono.stop()
            chrono.setTextColor(Color.CYAN)
            selectedYear = calendar.year
            selectedMonth = calendar.month
            selectedDay = calendar.dayOfMonth

            textResult.setText("" + selectedYear + "년" +(selectedMonth + 1) + "월" + selectedDay + "일")
            textResult.append("" + timePick.currentHour + "시")
            textResult.append("" + timePick.currentMinute + "분")
            textResult.append(" 예약 완료됨")

            rg.visibility = View.INVISIBLE
            calendar.visibility = View.INVISIBLE
            timePick.visibility = View.INVISIBLE

            return@setOnLongClickListener true
        }

//        calender.setOnDateChangeListener{ view, year, month, dayOfMonth ->
//            selectedYear = year
//            selectedMonth = month
//            selectedDay = dayOfMonth
//        }
    }

    var rgListener = OnCheckedChangeListener{ group, checked ->
        calendar.visibility = View.INVISIBLE
        timePick.visibility = View.INVISIBLE
        when(rg.checkedRadioButtonId){
            R.id.rbDate -> calendar.visibility=View.VISIBLE
            R.id.rbTime -> timePick.visibility = View.VISIBLE
        }
    }
}