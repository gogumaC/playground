package flow

//ref : https://bowser-f.medium.com/are-you-sure-you-know-how-kotlin-flow-works-e070f6d00cbc

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

val coroutineScope=CoroutineScope(Dispatchers.Default)

//flowOf : arguemnt를 방출하며 flow생성
val producer=flowOf("a","b","c").onEach{
    println("Producer: $it")
}//->cold flow : 만들어진 flow의 값을 가져오기 위한 terminal operation(collect,first 등) 이 없다면 동작하지 않음
//consumer가 있을때만 동작

fun main()=runBlocking{//코루틴동작을 기다리지 않고 메인이 끝나는걸 방지
    coroutineScope.launch {
        producer.collect{println("Consumer 1 : $it")}
    }
    coroutineScope.launch {
        producer.collect{println("Consumer 2: $it")} //collect순서는 비동기적인 코루틴의 특성에 따라 고정되지 않으므로 1,2가 거꾸로 실행될 수 있다.
    }
    delay(1000)
}