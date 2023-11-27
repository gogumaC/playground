package flow

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

// ref : https://bowser-f.medium.com/are-you-sure-you-know-how-kotlin-flow-works-e070f6d00cbc

val intFlow=flowOf(1,2,3,4,5).onEach{
    if(it==2)throw IllegalStateException("Invalid value")
}

/*
* throw Exception("msg") : 해당 메시지로 exception던짐
* .catch{e-> ... emit() } : 예외 처리, emit()로 원하는 값 방출 가능.
* 왜 emit()으로 던져야하냐면 예외가 나면 Flow가 종료되서 값을 방출 못하므로
*catch는 upstream flow에서 일어난 예외만 처리 가능하고 downstream flow는 불가
* 왜냐면 catch는 새로운 flow를 만들기 위해 내부에서 'flow' 를 사용하는데 얘는 오직 upstream에서 방출되는 값만 보고있으므로?
* 따라서 catch의 downStream flow에서 방출한 42는 잡지 못한다.
* */

fun main()=runBlocking{
    intFlow.catch{e->
        println("Exception catched")
        emit(42)
    }.collect{
        check(it<=1){"collected $it"}
        println("Consumer: $it")
    }
}
