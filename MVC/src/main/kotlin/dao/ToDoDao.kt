package dao

import model.Work
import model.ToDoList

interface ToDoDao {
    val allToDoLists: List<ToDoList>

    fun addToDoList(toDoList: ToDoList): Int
    fun deleteToDoListById(id: Int): Int
    fun getListById(id: Int): ToDoList?
    fun addWorksToList(work: Work): Int
    fun getAllListWorks(list: ToDoList): List<Work>
    fun setWorksDoneStatus(isDone: Int, id: Int): Int
    fun getWorksById(id: Int): Work?
    fun deleteWorkById(id: Int): Int
}
