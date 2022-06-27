package com.kanyideveloper.dlight.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kanyideveloper.dlight.data.local.converters.Converters
import com.kanyideveloper.dlight.data.local.dao.FollowersDao
import com.kanyideveloper.dlight.data.local.dao.FollowingDao
import com.kanyideveloper.dlight.data.local.dao.ReposDao
import com.kanyideveloper.dlight.data.local.dao.UserDao
import com.kanyideveloper.dlight.data.local.entity.UserEntity
import com.kanyideveloper.dlight.data.local.entity.UserFollowersEntity
import com.kanyideveloper.dlight.data.local.entity.UserFollowingsEntity
import com.kanyideveloper.dlight.data.local.entity.UserReposEntity

@TypeConverters(Converters::class)
@Database(
    entities = [UserEntity::class, UserFollowingsEntity::class, UserFollowersEntity::class, UserReposEntity::class],
    version = 2,
    exportSchema = false
)
abstract class GithubUserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun followingDao(): FollowingDao
    abstract fun followersDao(): FollowersDao
    abstract fun reposDao(): ReposDao
}