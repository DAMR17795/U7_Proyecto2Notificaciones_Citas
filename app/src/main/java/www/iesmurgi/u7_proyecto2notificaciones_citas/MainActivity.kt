package www.iesmurgi.u7_proyecto2notificaciones_citas


import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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
        var dPicker: Button = binding.btData
        var dPicker2: Button = binding.btCitaMed
        var notificacion: Button = binding.btNoti



        //Cita horaria
        tPicker.setOnClickListener{timePicker()}

        //Fecha Nacimiento
        dPicker.setOnClickListener{datePicker()}

        //Cita médica
        dPicker2.setOnClickListener{datePicker2()}

        //Notificacion
        notificacion.setOnClickListener{createNotificationChannel()}






    }

    private fun timePicker() {
        val c = Calendar.getInstance()
        val startHour = c.get(Calendar.HOUR_OF_DAY)
        val startMin = c.get(Calendar.MINUTE)

        //Creamos el RangeTimePickerDialog
        val tpd = RangeTimePickerDialog(this, {_,hour,min ->
            Toast.makeText(
                this, String.format(resources.getString(R.string.citahoraria), hour, min), Toast.LENGTH_SHORT).show()
        }, startHour +1, startMin, true)

        //Establecemos el minimo
        tpd.setMin(startHour, startMin)

        //Mostramos el TimePickerDialog
        tpd.show()
    }


    private fun datePicker () {
        val c = Calendar.getInstance()
        val startYear = c.get(Calendar.YEAR)
        val startMonth = c.get(Calendar.MONTH)
        val startDay = c.get(Calendar.DAY_OF_MONTH)

        //Creamos el DatePickerDialog
        val dpd = DatePickerDialog(this, {_, year, month, day ->
            Toast.makeText(
                this, String.format(resources.getString(R.string.cumple), day, month+1, year), Toast.LENGTH_SHORT).show()
        }, startYear, startMonth, startDay)

        //Mostramos el DatePickerDialog
        c.set(startYear, startMonth, startDay-1)
        dpd.datePicker.setMaxDate(c.timeInMillis)
        dpd.show()

    }

    private fun datePicker2 () {
        val c = Calendar.getInstance()
        val startYear = c.get(Calendar.YEAR)
        val startMonth = c.get(Calendar.MONTH)
        val startDay = c.get(Calendar.DAY_OF_MONTH)

        //Creamos el DatePickerDialog
        val dpd = DatePickerDialog(this, {_, year, month, day ->
            Toast.makeText(
                this, String.format(resources.getString(R.string.citamedica), day, month+1, year), Toast.LENGTH_SHORT).show()
        }, startYear, startMonth, startDay)

        //Mostramos el DatePickerDialog
        c.set(startYear, startMonth, startDay+14)
        dpd.datePicker.setMinDate(c.timeInMillis)
        dpd.show()

    }

    private fun notificacion() {
        val notificacionID =0
        val canalId = getString(R.string.canal)
        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.simbol)


        val notificacion = NotificationCompat.Builder(this, canalId)
            .setSmallIcon(R.drawable.activo)
            .setLargeIcon(largeIcon)
            .setContentTitle(resources.getString(R.string.notificacion))
            .setContentText(resources.getString(R.string.botonnoti))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        with(NotificationManagerCompat.from(this)) {
            notify(notificacionID, notificacion)
        }



    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.nombreCanal)
            val canalID = getString(R.string.canal)
            val descriptionText = getString(R.string.descripcion)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(canalID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }



}