package config

import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.context.annotation.Bean
import dao.TaskJdbcDao
import dao.TaskListJdbcDao

import javax.sql.DataSource;

open class JdbcDaoContextConfiguration {
    @Bean
    fun taskJdbcDao(dataSource: DataSource): TaskJdbcDao {
        return TaskJdbcDao(dataSource)
    }

    @Bean
    fun taskListJdbcDao(dataSource: DataSource): TaskListJdbcDao {
        return TaskListJdbcDao(dataSource)
    }

    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("org.sqlite.JDBC")
        dataSource.url = "jdbc:sqlite:task.db"
        dataSource.username = ""
        dataSource.password = ""
        return dataSource
    }
}