package com.example.androidclass.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.androidclass.domain.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE username LIKE :username")
    suspend fun getUserByUsername(username: String): User?

    @Update
    suspend fun update(user: User)
}
