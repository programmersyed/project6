package com.example.project5

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "article_table")
data class SleepEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "hours slept") val sleepNumber: String?,
    @ColumnInfo(name = "sleep rating") val sleepRate: String?,
    @ColumnInfo(name = "more sleep?") val moreSleep: String?,
)