package com.kanyideveloper.dlight.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kanyideveloper.dlight.domain.model.Follow
import com.kanyideveloper.dlight.domain.model.Repo

@ProvidedTypeConverter
class Converters(
    private val gson: Gson
) {

    // Following and Followers
    @TypeConverter
    fun fromFollowJson(json: String): List<Follow> {
        return gson.fromJson<ArrayList<Follow>>(
            json,
            object : TypeToken<ArrayList<Follow>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toFollowJson(follow: List<Follow>): String {
        return gson.toJson(
            follow,
            object : TypeToken<ArrayList<Follow>>() {}.type
        ) ?: "[]"
    }

    // Repos
    @TypeConverter
    fun fromReposJson(json: String): List<Repo> {
        return gson.fromJson<ArrayList<Repo>>(
            json,
            object : TypeToken<ArrayList<Repo>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toReposJson(repos: List<Repo>): String {
        return gson.toJson(
            repos,
            object : TypeToken<ArrayList<Repo>>() {}.type
        ) ?: "[]"
    }
}