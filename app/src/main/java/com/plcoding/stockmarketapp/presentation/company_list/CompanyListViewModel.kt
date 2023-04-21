 package com.plcoding.stockmarketapp.presentation.company_list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.stockmarketapp.domain.repository.StockRepository
import com.plcoding.stockmarketapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyListViewModel @Inject constructor(
    private val repository: StockRepository,
) : ViewModel() {

    var state by mutableStateOf(CompanyListStates())

    private var searchJob: Job? = null

    init {
        getCompanyList()
    }

    fun onEvent(event: CompanyListEvents){
        when(event){
            is CompanyListEvents.Refresh -> {
                getCompanyList(fetchFromRemote = true)
            }
            is CompanyListEvents.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCompanyList()
                }
            }
        }
    }

    private fun getCompanyList(
        fetchFromRemote: Boolean = false,
        query: String  = state.searchQuery.lowercase()
    ) {
        viewModelScope.launch {
            repository.getCompanyListing(fetchFromRemote , query)
                .collect { result ->
                    when(result){
                        is Resource.Success -> {
                            result.data?.let { list ->
                                state = state.copy(
                                    companies = list
                                )
                            }
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Error -> Unit
                    }
                }
        }
    }



}