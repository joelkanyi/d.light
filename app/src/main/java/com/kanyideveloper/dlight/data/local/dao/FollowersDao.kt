package com.kanyideveloper.dlight.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kanyideveloper.dlight.data.local.entity.UserFollowersEntity

@Dao
interface FollowersDao {
    @Query("SELECT * FROM user_followers_table WHERE login = :username")
    fun getUserFollowers(username: String): UserFollowersEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserFollowers(followers: UserFollowersEntity)

    @Query("DELETE FROM user_followers_table WHERE login = :username")
    suspend fun deleteUserFollowers(username: String)
}