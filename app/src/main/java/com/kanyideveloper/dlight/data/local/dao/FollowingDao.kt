package com.kanyideveloper.dlight.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kanyideveloper.dlight.data.local.entity.UserFollowingsEntity

@Dao
interface FollowingDao {

    @Query("SELECT * FROM user_followings_table WHERE login = :username")
    fun getUserFollowings(username: String): UserFollowingsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserFollowings(followings: UserFollowingsEntity)

    @Query("DELETE FROM user_followings_table WHERE login = :username")
    suspend fun deleteUserFollowings(username: String)
}