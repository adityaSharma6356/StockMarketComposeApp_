package com.plcoding.stockmarketapp.data.csv

import com.opencsv.CSVReader
import com.plcoding.stockmarketapp.data.mapper.toIntraDayInfo
import com.plcoding.stockmarketapp.data.remote.dto.IntraDayInfoDto
import com.plcoding.stockmarketapp.domain.model.CompanyListing
import com.plcoding.stockmarketapp.domain.model.IntraDayInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class IntraDayInfoParser @Inject constructor(): CSVParser<IntraDayInfo> {

    override suspend fun parse(stream: InputStream): List<IntraDayInfo> {
        val csvReader = CSVReader(InputStreamReader(stream))
        val days = if(LocalDateTime.now().dayOfWeek.value==2) 2 else 1
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val timestamp = line.getOrNull(0) ?: return@mapNotNull null
                    val closeValue = line.getOrNull(4) ?: return@mapNotNull null
                    val dto = IntraDayInfoDto(
                        timestamp , closeValue.toDouble()
                    )
                    dto.toIntraDayInfo()
                }
                .filter {
                    it.date.dayOfMonth == LocalDateTime.now().minusDays(days.toLong()).dayOfMonth
                }
                .sortedBy {
                    it.date.hour
                }
                .also {
                    csvReader.close()
                }
        }
    }
}

















