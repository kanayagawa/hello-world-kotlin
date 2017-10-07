package todolist

import com.fasterxml.jackson.databind.ObjectMapper
import spark.Request
import spark.Response
import spark.Route
import spark.Spark.halt

class TaskController(private val objectMapper: ObjectMapper,
                     private val taskRepository: TaskRepository) {

    fun index(): Route = Route {request, response ->
        taskRepository.findAll()
    }

    fun create(): Route = Route { request, response ->
        val request: TaskCreateRequest =
            objectMapper.readValue(request.bodyAsBytes()) ?: throw halt(400)
        val task = taskRepository.create(request.content)
        response.status(201)
        task
    }

    fun show(): Route = Route { request, response ->
        val id = request.params("id").toLongOrNull()
        id?.let(taskRepository::findById) ?: throw halt(404)
    }
}