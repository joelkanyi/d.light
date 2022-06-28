package com.kanyideveloper.dlight.di

import android.content.Context
import androidx.room.Room
import com.kanyideveloper.dlight.data.local.converters.Converters
import com.kanyideveloper.dlight.data.local.database.GithubUserDatabase
import com.kanyideveloper.dlight.data.remote.GithubRestAPI
import com.kanyideveloper.dlight.data.repository.UserRepositoryImpl
import com.kanyideveloper.dlight.domain.repository.UserRepository
import com.kanyideveloper.dlight.domain.use_case.*
import com.kanyideveloper.dlight.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideGithubUserDatabase(
        @ApplicationContext context: Context,
        converters: Converters
    ): GithubUserDatabase {
        return Room.databaseBuilder(
            context,
            GithubUserDatabase::class.java,
            "github_user_database"
        )
            .addTypeConverter(converters)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubRestApi(okHttpClient: OkHttpClient): GithubRestAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(GithubRestAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        githubRestAPI: GithubRestAPI,
        githubUserDatabase: GithubUserDatabase
    ): UserRepository {
        return UserRepositoryImpl(
            githubRestAPI = githubRestAPI,
            userDao = githubUserDatabase.userDao(),
            followingDao = githubUserDatabase.followingDao(),
            followersDao = githubUserDatabase.followersDao(),
            reposDao = githubUserDatabase.reposDao()
        )
    }

    @Provides
    @Singleton
    fun provideUseCases(userRepository: UserRepository): UserUseCases {
        return UserUseCases(
            getUserData = GetUserData(userRepository),
            getUserRepos = GetUserRepos(userRepository),
            getUserFollowers = GetUserFollowers(userRepository),
            getUserFollowings = GetUserFollowings(userRepository),
        )
    }
}