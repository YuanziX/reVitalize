package dev.yuanzix.revitalize.util

import dev.yuanzix.revitalize.data.repository.DataStoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.ZoneId

fun isToday(date: Long, now: Long): Boolean {
    val storedDate =
        LocalDate.ofEpochDay(date / (24 * 60 * 60 * 1000)).atStartOfDay(ZoneId.systemDefault())
            .toEpochSecond() * 1000
    return now == storedDate
}

fun checkDateAndTriggerFunction(
    dataStoreRepository: DataStoreRepository,
    triggerFunction: () -> Unit,
) {
    runBlocking {
        val now = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000
        val storedDate = dataStoreRepository.readDate.first()
        if (!isToday(storedDate, now)) {
            triggerFunction()
        }
        dataStoreRepository.persistDate(now)
    }
}