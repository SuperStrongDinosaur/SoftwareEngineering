package dao

import model.TaskList

interface TaskListDao {
    fun addTaskList(list: TaskList): Int

    fun getTaskLists(): List<TaskList>

    fun deleteTaskList(id: Int)
}
