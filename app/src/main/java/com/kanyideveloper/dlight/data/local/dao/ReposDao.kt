package com.kanyideveloper.dlight.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kanyideveloper.dlight.data.local.entity.UserReposEntity

@Dao
interface ReposDao {
    @Query("SELECT * FROM user_repos_table WHERE login = :username")
    fun getUserRepos(username: String): UserReposEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserRepos(repos: UserReposEntity)

    @Query("DELETE FROM user_repos_table WHERE login = :username")
    suspend fun deleteUserRepos(username: String)
}