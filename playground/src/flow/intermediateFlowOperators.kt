package flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// ref : https://bowser-f.medium.com/are-you-sure-you-know-how-kotlin-flow-works-e070f6d00cbc

/*
* filter
* map
* filterIsInstance
* filterNotNull
* transform
* */

val coroutineScope= CoroutineScope(Dispatchers.Default)

val flow=flowOf(1,2,3,4,5)
    .filter{it%2==0}
    .onEach { println("After filter: $it") }
    .map{it*it}
    .onEach { println("After map: $it") }

/*
* filter : 방출할 값 조건 설정
* map : 방출할 값에 추가적인 연산이나 처리
* */

fun main() = runBlocking{
    coroutineScope.launch{
        flow.takeWhile { it>8 }.collect{println(it)}
    }
    /*
    * takeWhile : consumer side에서 호출시 조건이 true일때만 flow실행(false가 되면 종료)
    * */

    //-> 이 경우 첫번째로 방출되는 2*2=4가 takeWhile조건을 만족하지 못하므로 flow는 취소됨. 따라서 아무것도 출력되지 않음
    delay(1000)
}