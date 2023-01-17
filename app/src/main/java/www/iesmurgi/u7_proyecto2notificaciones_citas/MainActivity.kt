package www.iesmurgi.u7_proyecto2notificaciones_citas

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import www.iesmurgi.u7_proyecto2notificaciones_citas.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Ponemos variables
        var tPicker: Button = binding.btTimePicker
        var selectedTime: TextView = binding.textView


        val mTimePicker: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)

        val minute = mcurrentTime.get(Calendar.MINUTE)

        mTimePicker = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {

            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                if (hour.toInt()>=hourOfDay) {
                    //selectedTime.setText(String.format("%d : %d", hourOfDay, minute))
                    selectedTime.setText(hourOfDay.toString() + ":" + minute.toString())

                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Solo se puede seleccionar desde las 9:00 hasta las 17:00",
                        Toast.LENGTH_SHORT
                    ).show()
                    selectedTime.setText("")
                    //selectedTime.setText(String.format(""))
                }

            }
        }, hour, minute, true)

        tPicker.setOnClickListener({ v ->
            mTimePicker.show()
        })


    }



}