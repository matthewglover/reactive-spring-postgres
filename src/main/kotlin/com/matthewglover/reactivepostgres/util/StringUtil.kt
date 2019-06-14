package com.matthewglover.reactivepostgres.util

private val DATASOURCE_REGEX = "^jdbc:postgresql://([^:]+):(\\d+)/(.+)\$".toRegex()

object StringUtil {

    fun parseDataSourceUrl(url: String): DataSourceUrl? {
        val groups = DATASOURCE_REGEX.matchEntire(url)?.groupValues

        return groups?.let {
            StringUtil.DataSourceUrl(
                    host = it[1],
                    port = it[2].toInt(),
                    database = it[3]
            )
        }
    }

    data class DataSourceUrl(val host: String, val port: Int, val database: String)
}