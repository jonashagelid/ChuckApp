package com.example.chuckapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(joke: JokeEntity)

    @Query("SELECT * FROM jokes")
    suspend fun getAllJokes(): List<JokeEntity>
}