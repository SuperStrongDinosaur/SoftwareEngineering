package config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import dao.TaskDao
import dao.TaskInMemoryDao
import dao.TaskListDao
import dao.TaskListInMemoryDao


@Configuration
open class InMemoryDaoContextConfiguration {
    @Bean
    open fun taskDao(): TaskDao {
        return TaskInMemoryDao()
    }

    @Bean
    open fun taskListDao(): TaskListDao {
        return TaskListInMemoryDao()
    }
}