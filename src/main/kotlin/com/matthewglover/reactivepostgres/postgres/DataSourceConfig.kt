package com.matthewglover.reactivepostgres.postgres

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.datasource")
class DataSourceConfig {
    lateinit var url: String
    lateinit var username: String
    lateinit var password: String
}
