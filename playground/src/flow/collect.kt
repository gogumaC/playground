package flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

val coroutineScope= CoroutineScope(Dispatchers.Default)

val intFlow =flowOf(1,2,3)
val stringFlow=flowOf("a","b","c")
// ref : https://bowser-f.medium.com/are-you-sure-you-know-how-kotlin-flow-works-e070f6d00cbc
/*
* terminal operation은 suspend function.
* 따라서 collect는 flow가 끝나기 전에는 실행되지 않는다.
* flow가 끝난다(complete)는것은 모든 원소를 방출했다는 것
*
* */
fun main()= runBlocking{
    coroutineScope.launch{
        intFlow.onEach{delay(100)}.collect{println(it)}
        //onEach의 적용시점에따라 실행이 달라진다. coldFlow.kt의 경우에는 intFlow정의와 함께 적용되지만 이경우는 collect의 호출시점에 적용한다.
        //따라서 각 아이템을 collect하고 100ms를 대기하는 방식으로 동작한다.
        stringFlow.collect{println(it)}

        /*
        * collect는 flow가 완전히 끝난 후 실행되므로 intFlow에 100ms의 delay를 주어도 complete된 intFlow를
        * collect하므로 intFlow,stringFlow가 교차되어 collect되지 않는다.
        * */
    }
    delay(1000)
}