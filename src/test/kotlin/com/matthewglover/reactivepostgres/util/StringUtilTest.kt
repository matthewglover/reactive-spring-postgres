package com.matthewglover.reactivepostgres.util

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class StringUtilTest {

    @Test
    fun parseDatasourceUrl(): Unit {
        val expected = StringUtil.DataSourceUrl(
                host = "localhost",
                port = 5432,
                database = "testdb"
        )

        val actual = StringUtil.parseDataSourceUrl("jdbc:postgresql://localhost:5432/testdb")

        assertEquals(expected, actual)
    }
}