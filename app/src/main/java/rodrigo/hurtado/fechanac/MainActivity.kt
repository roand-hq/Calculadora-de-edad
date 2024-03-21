package rodrigo.hurtado.fechanac

import android.os.Bundle
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Date
import java.text.DateFormatSymbols
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.LocalDateTime
import java.time.Period

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val ahora = System.currentTimeMillis()
        val dtpFecha = findViewById<CalendarView>(R.id.dtpFecha)
        val lbEdad = findViewById<TextView>(R.id.lbEdad)


        dtpFecha.maxDate = ahora
        println(dtpFecha.date)

        dtpFecha.setOnDateChangeListener { view, year, month, dayOfMonth ->

            val mes = month + 1
            val monthName = DateFormatSymbols().months[mes]
            val fechaNac = LocalDate.of(year,mes,dayOfMonth)
            val fechaAct = LocalDateTime.ofInstant(Instant.ofEpochMilli(ahora),ZoneId.systemDefault()).toLocalDate()
            val periodo = Period.between(fechaNac,fechaAct)
            val años = periodo.years
            val meses = periodo.months
            val dias = periodo.days
            lbEdad.text = "Edad: $años años, $meses meses y $dias días"
        }

    }
}