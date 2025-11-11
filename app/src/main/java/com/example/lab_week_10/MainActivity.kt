package com.example.lab_week_10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import kotlin.getValue
import com.example.lab_week_10.viewmodels.TotalViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_10.database.TotalDatabase
import androidx.room.Room
import com.example.lab_week_10.database.Total
import java.util.Date // Diperlukan untuk mendapatkan tanggal
import android.widget.Toast
import com.example.lab_week_10.database.TotalObject
class MainActivity : AppCompatActivity() {

    private val db by lazy { prepareDatabase() }
    private val viewModel by lazy {
        ViewModelProvider(this)[TotalViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeValueFromDatabase()
        prepareViewModel()
    }

    override fun onPause() {
        super.onPause() // [cite: 473]

        // Dapatkan nilai total saat ini dari ViewModel
        val currentValue = viewModel.total.value ?: 0
        // Dapatkan tanggal dan waktu saat ini
        val currentDate = Date().toString()

        // Buat TotalObject baru
        val totalObject = TotalObject(value = currentValue, date = currentDate)

        // Update database dengan objek Total yang lengkap
        db.totalDao().update(Total(id = ID, total = totalObject)) // [cite: 474]
    }

    override fun onStart() {
        super.onStart()

        // Baca dari database untuk mendapatkan data tanggal
        val totalList = db.totalDao().getTotal(ID) // [cite: 440]
        if (totalList.isNotEmpty()) {
            // Ambil string tanggal dari TotalObject
            val lastUpdateDate = totalList.first().total.date

            // Tampilkan Toast
            Toast.makeText(this, lastUpdateDate, Toast.LENGTH_LONG).show()
        }
    }
    private fun updateText(total: Int) {
        findViewById<TextView>(R.id.text_total).text =
            getString(R.string.text_total, total)
    }

    private fun prepareViewModel(){
        viewModel.total.observe(this) { total ->
            updateText(total)
        }

        findViewById<Button>(R.id.button_increment).setOnClickListener {
            viewModel.incrementTotal()
        }
    }

    private fun prepareDatabase(): TotalDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TotalDatabase::class.java, "total-database"
        ).allowMainThreadQueries().build()
    }

    private fun initializeValueFromDatabase() {
        val totalList = db.totalDao().getTotal(ID) // [cite: 440]
        if (totalList.isEmpty()) { // [cite: 441]
            // Buat objek default baru dengan nilai 0 dan tanggal saat ini
            val defaultObject = TotalObject(value = 0, date = Date().toString())
            // Masukkan Total baru yang berisi defaultObject
            db.totalDao().insert(Total(id = ID, total = defaultObject)) // [cite: 442]
            // viewModel.setTotal(0) akan otomatis terpanggil dari init di ViewModel
        } else {
            // Ambil HANYA 'value' dari 'TotalObject'
            val totalValue = totalList.first().total.value
            viewModel.setTotal(totalValue) // [cite: 444]
        }
    }

    companion object {
        const val ID: Long = 1
    }
}