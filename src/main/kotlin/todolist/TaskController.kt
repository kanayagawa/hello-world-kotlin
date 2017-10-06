package todolist

import spark.Request
import spark.Response
import spark.Route

class TaskController {
    fun index(): Route = object : Route {
        override fun handle(request: Request?, response: Response?): Any =
                //ダミーデータを返す
                listOf(
                        Task(1,"クリーニングに出す",false),
                        Task(2,"住民票を取得する",true)
                )
    }
}