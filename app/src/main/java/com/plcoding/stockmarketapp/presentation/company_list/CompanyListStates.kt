package com.plcoding.stockmarketapp.presentation.company_list

import com.plcoding.stockmarketapp.domain.model.CompanyListing

data class CompanyListStates(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)