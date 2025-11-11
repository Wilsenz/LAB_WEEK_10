package com.example.lab_week_10.viewmodels

// 1. TAMBAHKAN DUA IMPORT INI
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TotalViewModel : ViewModel() {

    // 2. UBAH 'var total: Int' MENJADI KODE DI BAWAH INI
    // Mendeklarasikan objek LiveData privat yang bisa diubah
    private val _total = MutableLiveData<Int>() // [cite: 232]

    // Mendeklarasikan objek LiveData publik yang hanya bisa dibaca (diamati)
    val total: LiveData<Int> = _total // [cite: 233]

    // Inisialisasi nilainya
    init { // [cite: 235]
        _total.postValue(0) // [cite: 240]
    }

    // 3. UBAH FUNGSI incrementTotal()
    // Fungsi ini tidak lagi mengembalikan Int
    fun incrementTotal() { // [cite: 243]
        // Perbarui nilai LiveData, yang akan memicu observer
        _total.postValue(_total.value?.plus(1)) // [cite: 244]
    }
}