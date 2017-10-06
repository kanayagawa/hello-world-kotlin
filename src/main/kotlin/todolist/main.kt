package todolist

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import spark.Spark.get

fun main(args: Array<String>) {
    val objectMapper = ObjectMapper().registerKotlinModule()
    get("/tasks") {request, response ->
        val tasks = listOf(
                Task(1,"クリーニングに出す",false),
                Task(2,"住民票を取得する",true)
        )
        objectMapper.writeValueAsString(tasks)
    }
}