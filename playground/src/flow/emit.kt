package flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.*

// ref : https://bowser-f.medium.com/are-you-sure-you-know-how-kotlin-flow-works-e070f6d00cbc

/*
* consumer가 producer보다 처리에 시간이 걸릴 경우
* 이 경우 emit은 모든 consumer가 방출된 값을 처리할때까지 기다린다.
* 근데 만약 한 consumer가 너무 느리다면 emit의 값 방출이 늦어지고 이는 다른 consumer들에게도 영향을 주게 된다.
* 이러한 상황을 해결하기 위해 buffer 메서드의 onBufferOverflow를 통해 buffer strategy를 변경가능하다.
* <buffer strategy>
* BufferOverflow.SUSPEND (default) : 다처리할때까지 기다린다.
* BufferOverflow.DROP_OLDEST : consumer의 buffer가 overflow일때 가장 오래된값을 버리고 새로운 값을 버퍼에 추가한다.(안기다려준다)
* BufferOverflow.DROP_LATES : consumer의 buffer가 overflow일때 가장 최근 값을 버리고 새로운 값을 버퍼에 추가한다.(따라서 버퍼의 내용물은 같은 값을 유지한다)(안기다려준다)
*
* */
val flow = flow {
    var i=0
    while(i<5){
        println("[${Thread.currentThread().name}] Producer: $i")
        emit(i)
        i++
        delay(10)
    }
}.flowOn(Dispatchers.IO).buffer(onBufferOverflow = BufferOverflow.DROP_OLDEST)
//flowOn으로 producer의 context를 변경해줌으로서 cunsumer에 의해 block되는 상황을 피함.
fun main()= runBlocking {
    flow.collect{
        //delay(100)
        Thread.sleep(100) //long process진행하고 있다는 가정
        /*
        * Flow의 값은 호출된 코루틴 context상(지금은 runBlocking,main thread)에서 collect됨 : context preservation 원칙
        * 예를 들어 producer와 consumer가 같은 thread상에서 동작 시 그중 하나가 thread를 block하면 다른 하나도 영향을 받음
        * 이경우 BufferOverflow.DROP_OLDEST를 사용했음에도 producer가 block되어 cunsumer가 해당 값을 처리할때까지 값을 방출하지 못하게 됨.
        *
        * 이러한 상황을 방지하기 위해서는 flowOn함수를 사용해 producer를 다른 context에서 실행시켜야함.
        * flowOn은 context가 없는 이전 operators의 context를 변경한다.(?)
        *
        *
        * */
        println("[${Thread.currentThread().name}] Consumer: $it")
    }

}