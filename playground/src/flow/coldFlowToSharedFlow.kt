package flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.shareIn

// ref : https://bowser-f.medium.com/are-you-sure-you-know-how-kotlin-flow-works-e070f6d00cbc

val coroutineScope= CoroutineScope(Dispatchers.Default)

val sharedFlow=flowOf("a","b","c").shareIn(coroutineScope,SharingStarted.Eagerly)
//public fun <T> kotlinx.coroutines.flow.Flow<T>.shareIn(scope: kotlinx.coroutines.CoroutineScope, started: kotlinx.coroutines.flow.SharingStarted, replay: kotlin.Int = COMPILED_CODE): kotlinx.coroutines.flow.SharedFlow<T>
/*
* scope : 코루틴 스코프
* stared : 시작 전략
* --> Eagerly : started strategy중 하나로 플로우가 생성되자마자 방출 시작
* reply (default=0): 얼마나 많은 최신값을 버퍼에 저장해서 다시 방출해줄건지 결정, 0이면 새로운 구독자에게 이전에 방출한 값 재방출하지 않음
*                  : 1이라면 최근 방출된 1개의 값을 버퍼에 저장하여 새 구독자에게 전달
* */

/*
* sharedFlow는 hot flow type이다.
* sharedIn함수를 통해 flowOf로 만든 coldFlow를 sharedFlow(hot flow)로 바꿀 수 있다.
* hot flow : consumer가 있던 ㅊ없던 마이웨이로 방출하고, 새로운 컨슈머가 방출을 다시 트리거할수 없다. 그냥 진짜 흘러가는 물느낌
*  방출은 컨슈머가 있던없던 시작되고 이때 컨슈머가 있었다면 해당 시점의 모든 컨슈머는 같은 값을 받는다.
* */
fun main()= runBlocking {
    coroutineScope.launch {
        delay(1)
        sharedFlow.collect{println("consumer 1: $it")}
    }

    /*
    * delay(1)에 의해 1ms가 지나는 동안 이미 flow방출이 끝났으므로 collect에서 수집할 정보가 없으므로 아무것도 출력되지 않는다.
    * */

    delay(1000)
}