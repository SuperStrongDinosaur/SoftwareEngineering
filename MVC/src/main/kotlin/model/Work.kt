package model

import java.text.SimpleDateFormat
import java.util.*
import javax.validation.constraints.*

class Work(var id: Int = 0,
           var listId: Int = 0,
           var isDone: Int = 0) {

    var date: String = ""
    set(value) {
        field = value
        dt = SimpleDateFormat("yyyy-MM-dd").parse(value)
    }

    @FutureOrPresent(message = "Date should be in future")
    var dt : Date = Date()

    @Size(min = 10, max = 200, message = "description must be between 10 and 200 characters") var description: String = ""


    fun getIsDone(): Int {
        return isDone
    }

    fun setIsDone(isDone: Int) {
        this.isDone = isDone
    }
}



