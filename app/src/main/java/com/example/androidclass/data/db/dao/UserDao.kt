package com.example.androidclass.data.db.dao

import androidx.room.*
import com.example.androidclass.domain.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Update
    suspend fun update(user: User)

    @Query("UPDATE ${User.TABLE_NAME} SET username = :username WHERE id = :userId")
    suspend fun updateUsername(userId: Long, username: String)

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE username LIKE :username")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE id = :id")
    suspend fun getUserById(id: Long): User?
}
