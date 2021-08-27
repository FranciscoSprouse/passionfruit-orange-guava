package com.pog.passionfruitOrangeGuava.features.search.repository

import com.pog.passionfruitOrangeGuava.features.search.model.UserModel
import com.pog.passionfruitOrangeGuava.features.search.model.UserSearchModel
import com.pog.passionfruitOrangeGuava.networking.PogApiService
import com.pog.passionfruitOrangeGuava.networking.ResponseState
import com.pog.passionfruitOrangeGuava.networking.StateLoading
import com.pog.passionfruitOrangeGuava.networking.StateSuccess
import com.pog.passionfruitOrangeGuava.networking.apimodel.UserSearchResponse
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class PogRepository @Inject constructor(val remoteDataSource: PogApiService)  {
    suspend fun getUser(username: String): Flow<ResponseState<UserModel?>> = flow {
        emit(StateLoading())
        val response = remoteDataSource.getUser(username = username)
        // Ideally we would use some sort of mapper to map the api objects to the actual model objects
        val responseAsModelObject = UserModel(response.login, response.avatarURL, response.publicRepos)
        emit(StateSuccess(responseAsModelObject))
    }

    suspend fun searchUsers(query: String): Flow<ResponseState<List<UserModel>>> = flow {
        emit(StateLoading())
        val response = remoteDataSource.searchUsers(query = query)
        emit(StateSuccess(response.mapToModel()))
    }
}

fun UserSearchResponse.mapToModel() = this.items.map { UserModel(it.login, it.avatarURL, it.publicRepos) }