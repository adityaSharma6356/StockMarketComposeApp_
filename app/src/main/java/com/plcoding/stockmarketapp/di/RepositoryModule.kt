package com.plcoding.stockmarketapp.di

import com.plcoding.stockmarketapp.data.csv.CSVParser
import com.plcoding.stockmarketapp.data.csv.CompanyListingsParser
import com.plcoding.stockmarketapp.data.csv.IntraDayInfoParser
import com.plcoding.stockmarketapp.data.repository.StocksRepositoryImpl
import com.plcoding.stockmarketapp.domain.model.CompanyListing
import com.plcoding.stockmarketapp.domain.model.IntraDayInfo
import com.plcoding.stockmarketapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListParser(
        parser: CompanyListingsParser
    ) : CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun intraDayInfoParser(
        parser: IntraDayInfoParser
    ) : CSVParser<IntraDayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        repository: StocksRepositoryImpl
    ): StockRepository
}












