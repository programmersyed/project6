package com.example.project5

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow




@Dao
interface SleepDAO {
    @Query("SELECT * FROM article_table")
    fun getAll(): Flow<List<SleepEntity>>

    @Insert
    fun insert(sleepData: SleepEntity)

    @Query("DELETE FROM article_table")
    fun deleteAll()
}