package model

import javax.validation.constraints.*

class ToDoList(var id: Int = 0) {
    @NotBlank(message = "name shouldn't be blank") var name: String = ""
    @Size(min = 10, max = 200, message = "description must be between 10 and 200 characters") var description: String = ""
    @Email(message = "Email should be valid") var email: String = ""
    var works: List<Work> = ArrayList()
}
