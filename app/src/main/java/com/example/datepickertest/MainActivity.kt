package com.example.datepickertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.datepickertest.databinding.ActivityMainBinding
import com.wdullaer.materialdatetimepicker.date.DatePickerController
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.time.Year

import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val button1 = binding.button1;

        val tmro = Calendar.getInstance()
        tmro.set(tmro.get(Calendar.YEAR), tmro.get(Calendar.MONTH), tmro.get(Calendar.DATE)+1)
        val max = Calendar.getInstance()
        max.set(tmro.get(Calendar.YEAR), max.get(Calendar.MONTH)+12, max.get(Calendar.DATE))

        val dpd = DatePickerDialog.newInstance { view, year, monthOfYear, dayOfMonth ->
            val date:  String = "You picked the following date: "+year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
            Toast.makeText(this@MainActivity, date, Toast.LENGTH_SHORT).show()
        }

        dpd.minDate = tmro
        dpd.maxDate = max ;

        //disable Weekends
        var loopdate: Calendar = tmro
        while (tmro.before(max)) {
            val dayOfWeek = loopdate[Calendar.DAY_OF_WEEK]
            if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
                val disabledDays = arrayOfNulls<Calendar>(1)
                disabledDays[0] = loopdate
                dpd.disabledDays = disabledDays
            }
            tmro.add(Calendar.DATE, 1)
            loopdate = tmro
        }
        
        button1.setOnClickListener{
            //// If you're calling this from a support Fragment
            //dpd.show(getFragmentManager(), "Datepickerdialog");
            // If you're calling this from an AppCompatActivity
            dpd.show(getSupportFragmentManager(), "Datepickerdialog");
        }
    }
}