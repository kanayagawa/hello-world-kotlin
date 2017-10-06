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
                try {
                    objectMapper.readValue(request.bodyAsBytes(), TaskCreateRequest::class.java)
                } catch (e: Exception) {
                    throw halt(400)
                }
        val task = taskRepository.create(request.content)
        response.status(200)
        task
    }
}