package com.matthewglover.reactivepostgres.postgres

import com.matthewglover.reactivepostgres.util.StringUtil
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.function.DatabaseClient

@Configuration
@EnableConfigurationProperties(DataSourceConfig::class)
class PostgresConfig {
    @Bean
    fun databaseClient(dataSourceConfig: DataSourceConfig): DatabaseClient {
        val connectionFactory = PostgresqlConnectionFactory(connectionConfig(dataSourceConfig))

        return DatabaseClient.create(connectionFactory)
    }

    fun connectionConfig(dataSourceConfig: DataSourceConfig): PostgresqlConnectionConfiguration {
        val urlConfig = checkNotNull(StringUtil.parseDataSourceUrl(dataSourceConfig.url))

        return PostgresqlConnectionConfiguration.builder()
                .host(urlConfig.host)
                .port(urlConfig.port)
                .database(urlConfig.database)
                .username(dataSourceConfig.username)
                .password(dataSourceConfig.password)
                .build()
    }
}
