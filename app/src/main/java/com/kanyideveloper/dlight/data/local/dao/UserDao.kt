package com.kanyideveloper.dlight.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kanyideveloper.dlight.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table WHERE login = :username")
    fun getUser(username: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("DELETE FROM user_table WHERE login = :username")
    suspend fun deleteUser(username: String)
}