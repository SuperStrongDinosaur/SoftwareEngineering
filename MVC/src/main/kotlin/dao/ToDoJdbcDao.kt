package dao

import model.Work
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.support.JdbcDaoSupport
import org.springframework.lang.Nullable
import model.ToDoList

import javax.sql.DataSource

class ToDoJdbcDao(dataSource: DataSource) : JdbcDaoSupport(), ToDoDao {

    override val allToDoLists: List<ToDoList>
        get() {
            val sql = "SELECT * FROM ToDoLists"
            return getListsByQuery(sql)
        }

    init {
        setDataSource(dataSource)
        initDatabase()
    }

    override fun addToDoList(toDoList: ToDoList): Int {
        val sql = "INSERT INTO ToDoLists (name, description, email) VALUES (?, ?, ?)"
        return updateByQuery(sql, toDoList.name, toDoList.description, toDoList.email)
    }

    override fun deleteToDoListById(id: Int): Int {
        val sql = "DELETE FROM ToDoLists WHERE id = ?"
        return updateByQuery(sql, id)
    }


    override fun getListById(id: Int): ToDoList? {
        val sql = "SELECT * FROM ToDoLists WHERE id = ?"
        val lists = getListsByQuery(sql, id)
        return if (lists.isNotEmpty()) {
            lists[0]
        } else null
    }

    override fun addWorksToList(work: Work): Int {
        val sql = "INSERT INTO Work (listId, description, isDone, date) VALUES (?, ?, 0, ?)"
        return updateByQuery(sql, work.listId, work.description, work.date)
    }

    override fun getAllListWorks(list: ToDoList): List<Work> {
        val sql = "SELECT * FROM Work WHERE listId = ?"
        return getWorkByQuery(sql, list.id)
    }

    override fun setWorksDoneStatus(isDone: Int, id: Int): Int {
        val sql = "UPDATE Work SET isDone = ? WHERE id = ?"
        return updateByQuery(sql, isDone, id)
    }

    override fun getWorksById(id: Int): Work? {
        val sql = "SELECT * FROM Work WHERE id = ?"
        val works = getWorkByQuery(sql, id)
        return if (works.isNotEmpty()) {
            works[0]
        } else null
    }

    override fun deleteWorkById(id: Int): Int {
        val sql = "DELETE FROM Work WHERE id = ?"
        return updateByQuery(sql, id)
    }

    private fun getListsByQuery(sql: String, @Nullable vararg args: Any): List<ToDoList> {
        return jdbcTemplate!!.query(sql, BeanPropertyRowMapper(ToDoList::class.java), *args)
    }

    private fun getWorkByQuery(sql: String, @Nullable vararg args: Any): List<Work> {
        return jdbcTemplate!!.query(sql, BeanPropertyRowMapper(Work::class.java), *args)
    }

    private fun updateByQuery(sql: String, @Nullable vararg args: Any): Int {
        return jdbcTemplate!!.update(sql, *args)
    }

    private fun initDatabase(): Int {
        val sql = "CREATE TABLE IF NOT EXISTS ToDoLists(" +
                "id             INTEGER   PRIMARY KEY AUTOINCREMENT," +
                "name           TEXT      NOT NULL," +
                "description    TEXT      NOT NULL," +
                "email          TEXT      NOT NULL" +
                ");" +
                "CREATE TABLE IF NOT EXISTS Work(" +
                "id             INTEGER   PRIMARY KEY AUTOINCREMENT," +
                "listId         INTEGER   NOT NULL," +
                "description    TEXT      NOT NULL," +
                "isDone         INTEGER   NOT NULL," +
                "date           TEXT      NOT NULL" +
                ");"
        return jdbcTemplate!!.update(sql)
    }
}
