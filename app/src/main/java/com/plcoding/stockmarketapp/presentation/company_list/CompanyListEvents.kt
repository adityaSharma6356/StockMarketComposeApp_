package com.plcoding.stockmarketapp.presentation.company_list

sealed class CompanyListEvents {
    object Refresh: CompanyListEvents()
    data class OnSearchQueryChange(val query: String): CompanyListEvents()
}
