package controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import model.ToDoList
import model.Work
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.client.RestTemplate
import javax.validation.Valid

@Controller
class ToDoListController {
    private var restTemplate = RestTemplate()
    private val mapper = ObjectMapper().registerModule(KotlinModule())
    private val ow = ObjectMapper().writer().withDefaultPrettyPrinter()


    @RequestMapping(value = ["/add-list"], method = [RequestMethod.POST])
    fun addToDoList(@Valid @ModelAttribute("product") toDoList: ToDoList, bindingResult: BindingResult, map: ModelMap): String {
        if (bindingResult.hasErrors()) {
            val errors = ArrayList<String>()
            for(error in bindingResult.fieldErrors) {
                errors.add(error.field + ": " + error.defaultMessage)
            }
            map.addAttribute("errors", errors)
            map.addAttribute("error", String())
            return "error"
        }

        val json = ow.writeValueAsString(toDoList)
        val uri = "http://localhost:8080/api/add-list"

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        val map = LinkedMultiValueMap<String, String>()
        map["jsonBody"] = json
        val request = HttpEntity<MultiValueMap<String, String>>(map, headers)
        restTemplate.postForObject(uri, request, String::class.java)

        return "redirect:/get-lists"
    }

    @RequestMapping(value = ["/get-lists"], method = [RequestMethod.GET])
    fun getToDoLists(map: ModelMap): String {
        val uri = "http://localhost:8080/api/get-lists"
        val result = restTemplate.getForObject(uri, String::class.java)

        map.addAttribute("lists", mapper.readValue<List<ToDoList>>(result))
        map.addAttribute("list", ToDoList())

        return "index"
    }

    @RequestMapping(value = ["/remove-list"], method = [RequestMethod.GET])
    fun deleteToDoList(@ModelAttribute("list") toDoList: ToDoList): String {
        val uri = "http://localhost:8080/api/remove-list"

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        val map = LinkedMultiValueMap<String, Int>()
        map["id"] = toDoList.id
        val request = HttpEntity<MultiValueMap<String, Int>>(map, headers)
        val result = restTemplate.postForObject(uri, request, String::class.java)

        return "redirect:/get-lists"
    }

    @RequestMapping(value = ["/edit-list"], method = [RequestMethod.GET])
    fun editToDoList(@ModelAttribute("list") toDoList: ToDoList, map: ModelMap): String {
        val uri = "http://localhost:8080/api/get-lists"
        val result = restTemplate.getForObject(uri, String::class.java)
        val todos = mapper.readValue<List<ToDoList>>(result)
        for(todo in todos) {
            if(todo.id == toDoList.id) {
                map.addAttribute("list", todo)
                map.addAttribute("works", todo.works)
            }
        }

        map.addAttribute("work", Work())
        return "list"
    }

    @RequestMapping(value = ["/add-work"], method = [RequestMethod.POST])
    fun addWorkToList(@Valid @ModelAttribute("work") work: Work, bindingResult: BindingResult, map: ModelMap): String {
        if (bindingResult.hasErrors()) {
            val errors = ArrayList<String>()
            for(error in bindingResult.fieldErrors) {
                errors.add(error.field + ": " + error.defaultMessage)
            }
            map.addAttribute("errors", errors)
            map.addAttribute("error", String())
            return "error"
        }

        val json = ow.writeValueAsString(work)
        val uri = "http://localhost:8080/api/add-work"

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        val map = LinkedMultiValueMap<String, String>()
        map["jsonBody"] = json
        val request = HttpEntity<MultiValueMap<String, String>>(map, headers)
        restTemplate.postForObject(uri, request, String::class.java)

        return "redirect:/edit-list?id=" + work.listId
    }

    @RequestMapping(value = ["/set-done"], method = [RequestMethod.POST])
    fun setWorkAsDone(@ModelAttribute("work") work: Work, @RequestParam("sid") selected: String): String {
        val id = Integer.parseInt(selected)

        val uri = "http://localhost:8080/api/get-lists"
        val result = restTemplate.getForObject(uri, String::class.java)
        val todos = mapper.readValue<List<ToDoList>>(result)

        for(todo in todos) {
            for(w in todo.works) {
                if(w.id == id) {
                    w.isDone = (w.isDone + 1) % 2

                    val json = ow.writeValueAsString(w)
                    val uri = "http://localhost:8080/api/edit-work"

                    val headers = HttpHeaders()
                    headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
                    val map = LinkedMultiValueMap<String, Object>()
                    map["id"] = id as Object
                    map["jsonBody"] = json as Object
                    val request = HttpEntity<MultiValueMap<String, Object>>(map, headers)
                    restTemplate.postForObject(uri, request, String::class.java)
                }
            }
        }

        return "redirect:/edit-list?id=" + work.listId
    }

    @RequestMapping(value = ["/remove-work"], method = [RequestMethod.GET])
    fun deleteWork(@ModelAttribute("work") work: Work): String {
        val uri = "http://localhost:8080/api/remove-work"

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        val map = LinkedMultiValueMap<String, Int>()
        map["id"] = work.id
        val request = HttpEntity<MultiValueMap<String, Int>>(map, headers)
        restTemplate.postForObject(uri, request, String::class.java)

        return "redirect:/edit-list?id=" + work.listId
    }
}
