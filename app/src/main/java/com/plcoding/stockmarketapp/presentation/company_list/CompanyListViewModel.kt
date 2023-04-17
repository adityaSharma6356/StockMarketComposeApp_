package com.plcoding.stockmarketapp.presentation.company_list

import androidx.lifecycle.ViewModel
import com.plcoding.stockmarketapp.domain.repository.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompanyListViewModel @Inject constructor(
    private val repository: StockRepository,
) : ViewModel() {

}