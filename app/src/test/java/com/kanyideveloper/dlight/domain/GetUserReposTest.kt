package com.kanyideveloper.dlight.domain

import com.kanyideveloper.dlight.data.FakeUserRepository
import com.kanyideveloper.dlight.domain.use_case.GetUserRepos
import org.junit.Before

class GetUserReposTest {

    private lateinit var getUserRepos: GetUserRepos
    private lateinit var fakeUserRepository: FakeUserRepository

    @Before
    fun setUp() {
        fakeUserRepository = FakeUserRepository()
        getUserRepos = GetUserRepos(fakeUserRepository)
    }
}