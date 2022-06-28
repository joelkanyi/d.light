package com.kanyideveloper.dlight.data.repository

import com.kanyideveloper.dlight.data.local.dao.FollowersDao
import com.kanyideveloper.dlight.data.local.dao.FollowingDao
import com.kanyideveloper.dlight.data.local.dao.ReposDao
import com.kanyideveloper.dlight.data.local.dao.UserDao
import com.kanyideveloper.dlight.data.local.entity.UserFollowersEntity
import com.kanyideveloper.dlight.data.local.entity.UserFollowingsEntity
import com.kanyideveloper.dlight.data.local.entity.UserReposEntity
import com.kanyideveloper.dlight.data.mapper.toDomain
import com.kanyideveloper.dlight.data.mapper.toEntity
import com.kanyideveloper.dlight.data.remote.GithubRestAPI
import com.kanyideveloper.dlight.domain.model.Follow
import com.kanyideveloper.dlight.domain.model.Repo
import com.kanyideveloper.dlight.domain.model.User
import com.kanyideveloper.dlight.domain.repository.UserRepository
import com.kanyideveloper.dlight.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class UserRepositoryImpl(
    private val githubRestAPI: GithubRestAPI,
    private val userDao: UserDao,
    private val followingDao: FollowingDao,
    private val followersDao: FollowersDao,
    private val reposDao: ReposDao
) : UserRepository {

    override suspend fun getUser(username: String): Flow<Resource<User>> = flow {

        emit(Resource.Loading())

        val databaseUserData = userDao.getUser(username)

        if (databaseUserData != null) {
            emit(Resource.Success(databaseUserData.toDomain()))
        } else {
            try {
                val apiResponse = githubRestAPI.getUser(username)

                userDao.deleteUser(username)

                userDao.insertUser(apiResponse.toEntity())

                val user = userDao.getUser(username)

                if (user != null) {
                    emit(Resource.Success(user.toDomain()))
                }

            } catch (e: HttpException) {
                when (e.code()) {
                    404 -> {
                        Timber.e("User not found, please check the username")
                        emit(Resource.Error("User not found, please check the username"))
                    }
                    403 -> {
                        Timber.e("Forbidden access, please add authorization header")
                        emit(Resource.Error("Forbidden access, please add authorization header"))
                    }
                    else -> {
                        emit(Resource.Error("Unknown error occurred"))
                    }
                }
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't connect to the server. Please check your internet connection."))
            }
        }
    }

    override suspend fun getUserFollowers(username: String): Flow<Resource<List<Follow>>> =
        flow {

            emit(Resource.Loading())

            val databaseFollowersData = followersDao.getUserFollowers(username)
            Timber.d("databaseFollowingData: $databaseFollowersData")

            if (databaseFollowersData != null) {
                emit(Resource.Success(databaseFollowersData.followers))
            } else {
                try {
                    val apiResponse = githubRestAPI.getUserFollowers(username)

                    Timber.d("followers apiResponse: $apiResponse")

                    val userFollowers = UserFollowersEntity(
                        login = username,
                        followers = apiResponse.map { it.toDomain() }
                    )

                    followersDao.deleteUserFollowers(username)

                    followersDao.insertUserFollowers(userFollowers)

                    val followers = followersDao.getUserFollowers(username)?.followers

                    if (followers != null) {
                        emit(Resource.Success(followers))
                    }

                } catch (e: HttpException) {
                    when (e.code()) {
                        404 -> {
                            Timber.e("User followers not found")
                            emit(Resource.Error("User followers not found"))
                        }
                        403 -> {
                            Timber.e("Forbidden access, please add authorization header")
                            emit(Resource.Error("Forbidden access, please add authorization header"))
                        }
                        else -> {
                            Timber.e("Unknown error occurred")
                            emit(Resource.Error("Unknown error occurred"))
                        }
                    }
                } catch (e: IOException) {
                    emit(Resource.Error("Couldn't connect to the server. Please check your internet connection."))
                }
            }
        }

    override suspend fun getUserFollowing(username: String): Flow<Resource<List<Follow>>> =
        flow {

            emit(Resource.Loading())

            val databaseFollowingData = followingDao.getUserFollowings(username)
            Timber.d("databaseFollowingData: $databaseFollowingData")

            if (databaseFollowingData != null) {
                emit(Resource.Success(databaseFollowingData.followings))
            } else {
                try {
                    val apiResponse = githubRestAPI.getUserFollowing(username)

                    Timber.d("following apiResponse: $apiResponse")

                    val userFollowings = UserFollowingsEntity(
                        login = username,
                        followings = apiResponse.map { it.toDomain() }
                    )

                    followingDao.deleteUserFollowings(username)

                    followingDao.insertUserFollowings(userFollowings)

                    val following = followingDao.getUserFollowings(username)

                    if (following?.followings != null) {
                        emit(Resource.Success(following.followings))
                    }

                } catch (e: HttpException) {
                    when (e.code()) {
                        404 -> {
                            Timber.e("User followers not found")
                            emit(Resource.Error("User followers not found"))
                        }
                        403 -> {
                            Timber.e("Forbidden access, please add authorization header")
                            emit(Resource.Error("Forbidden access, please add authorization header"))
                        }
                        else -> {
                            Timber.e("Unknown error occurred")
                            emit(Resource.Error("Unknown error occurred"))
                        }
                    }
                } catch (e: IOException) {
                    emit(Resource.Error("Couldn't connect to the server. Please check your internet connection."))
                }
            }
        }

    override suspend fun getUserRepos(username: String): Flow<Resource<List<Repo>>> =
        flow {

            emit(Resource.Loading())

            val databaseReposData = reposDao.getUserRepos(username)
            Timber.d("databaseReposData: $databaseReposData")

            if (databaseReposData != null) {
                emit(Resource.Success(databaseReposData.repos))
            } else {
                try {
                    Timber.d("Trying to get data from api")
                    val apiResponse = githubRestAPI.getUserRepos(username)

                    Timber.d("repos apiResponse: $apiResponse")

                    val userRepos = UserReposEntity(
                        login = username,
                        repos = apiResponse.map { it.toDomain() }
                    )

                    reposDao.deleteUserRepos(username)

                    reposDao.insertUserRepos(userRepos)

                    val repos = reposDao.getUserRepos(username)

                    if (repos?.repos != null) {
                        emit(Resource.Success(repos.repos))
                    }

                } catch (e: HttpException) {
                    when (e.code()) {
                        404 -> {
                            Timber.e("User repositories not found")
                            emit(Resource.Error("User followers not found"))
                        }
                        403 -> {
                            Timber.e("Forbidden access, please add authorization header")
                            emit(Resource.Error("Forbidden access, please add authorization header"))
                        }
                        else -> {
                            Timber.e("Unknown error occurred")
                            emit(Resource.Error("Unknown error occurred"))
                        }
                    }
                } catch (e: IOException) {
                    emit(Resource.Error("Couldn't connect to the server. Please check your internet connection."))
                }
            }
        }.flowOn(Dispatchers.IO)
}