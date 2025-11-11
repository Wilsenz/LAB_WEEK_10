package com.example.lab_week_10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView // <-- 1. TAMBAHKAN import ini

// 2. HAPUS import yang salah: import android.provider.Settings.Global.getString

class MainActivity : AppCompatActivity() {
    private var total: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        total = 0
        updateText(total)

        findViewById<Button>(R.id.button_increment).setOnClickListener {
            incrementTotal()
        }
    }

    private fun incrementTotal() {
        total++
        updateText(total)
    }


    private fun updateText(total: Int) {
        // Sekarang findViewById dan getString akan dikenali
        findViewById<TextView>(R.id.text_total).text =
            getString(R.string.text_total, total)
    }
}