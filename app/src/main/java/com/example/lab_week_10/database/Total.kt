package com.example.lab_week_10.database

import androidx.room.ColumnInfo
import androidx.room.Embedded // <- Import ini penting
import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity utama tetap sama [cite: 486]
@Entity(tableName = "total")
data class Total(
    @PrimaryKey(autoGenerate = true) // [cite: 489]
    @ColumnInfo(name = "id") // [cite: 491]
    val id: Long = 0, // [cite: 493]

    // Kolom 'total' sekarang adalah objek @Embedded [cite: 496-497]
    @Embedded
    val total: TotalObject
)

// Ini adalah kelas objek baru yang akan disematkan [cite: 501]
data class TotalObject(
    @ColumnInfo(name = "value") // [cite: 503]
    val value: Int,
    @ColumnInfo(name = "date") // [cite: 504]
    val date: String
)