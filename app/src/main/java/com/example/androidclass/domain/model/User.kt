package com.example.androidclass.domain.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.androidclass.domain.model.User.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME, indices = [Index("username", unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val username: String,
    val password: String,
    val salt: String
) {
    companion object {
        const val TABLE_NAME = "users"
    }
}
