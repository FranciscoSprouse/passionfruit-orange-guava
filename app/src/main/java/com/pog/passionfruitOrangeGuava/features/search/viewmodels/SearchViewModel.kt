package com.pog.passionfruitOrangeGuava.features.search.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.pog.passionfruitOrangeGuava.features.search.model.UserSearchModel
import com.pog.passionfruitOrangeGuava.features.search.repository.PogRepository
import com.pog.passionfruitOrangeGuava.features.search.ui.SearchListAdapter
import com.pog.passionfruitOrangeGuava.networking.ResponseState
import com.pog.passionfruitOrangeGuava.networking.StateFailed
import com.pog.passionfruitOrangeGuava.networking.StateLoading
import com.pog.passionfruitOrangeGuava.networking.StateSuccess
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

/*
 * ViewModel for the Search feature
 * This is my first attempt at using Coroutines, and Flows; it might not contain best practices.
 */
class SearchViewModel @Inject constructor(val repo: PogRepository): ViewModel() {
    val nameInput = MutableLiveData<String>()
    val userList = MutableLiveData<List<UserSearchModel>>()
    val isLoading = MutableLiveData<Boolean>().apply { this.postValue(false) }
    val errorMessage = MutableLiveData<String>()

    init {
        launchSearchCoroutine()
    }

    /*
     * Launches the parent job for the github user search job.
     * This job will observe nameInput as a Flow.
     * When nameInput changes we launch a child coroutine to get the user data from the repo.
     */
    fun launchSearchCoroutine() {
        viewModelScope.launch {
            var job: Job? = null
            nameInput.asFlow()
                .distinctUntilChanged()
                .debounce(300L)
                .collect { username ->
                    job?.cancel()
                    job = launch(Dispatchers.IO) {
                        repo.getUser(username).catch { error ->
                            emit(StateFailed(message = error.message))
                        }.collect { state ->
                            when (state) {
                                is StateSuccess -> onSearchSuccess(state.data)
                                is StateLoading -> onLoading()
                                is StateFailed -> onSearchFailed(state.message)
                            }
                        }
                    }
                }
        }
    }

    fun onLoading() {
        isLoading.postValue(true)
    }

    fun onSearchFailed(message: String?) {
        userList.postValue(listOf())
        isLoading.postValue(false)
        // Ideally this string would be in a resource file and would be better tailored to an end user
        val error = message ?: "Whoops error message not found"
        errorMessage.postValue(error)
    }

    fun onSearchSuccess(user: UserSearchModel?) {
        user?.let {
            userList.postValue(listOf(user))
        } ?: kotlin.run {
            // Ideally this string would be in a resource file and would be better tailored to an end user
            errorMessage.postValue("Something went wrong, user found but not found")
        }
        isLoading.postValue(false)

    }
}