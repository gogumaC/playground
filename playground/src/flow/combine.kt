package flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart

// ref : https://bowser-f.medium.com/are-you-sure-you-know-how-kotlin-flow-works-e070f6d00cbc

val coroutineScope= CoroutineScope(Dispatchers.Default)

val intFlow= MutableSharedFlow<Int>()
val stringFlow= MutableStateFlow("a")

fun main()= runBlocking{
    coroutineScope.launch {
        combine(
            stringFlow,
            intFlow.onStart { 1 },
        ){
            valueStr,valueInt->
            "$valueStr: $valueInt"
        }.collect{
            println(it)
        }
    }
    /*
    * combine에 의해 두개의 flow가 합쳐지고 두 flow의 값을 변형하여 새로운 값을 새로운 flow로 방출
    * intFlow는 MutableSharedFlow로 값이 미리 세팅되어있지 않음
    * combine은 새로운 값을 생성하기 위해 각 플로우에서 적어도 하나의 값이 필요함.
    * 따라서 intFlow만 사용한다면 데이터를 받지 못하므로 계속해서 첫번째 값을 기다리며 아무것도 생성하지 못함
    *
    * onStart메서드는 시작 값을 세팅한 새 flow를 반환하는 함수다.
    * 따라서 intFlow에 아무 값이 없어도 onStart를 통해 1을 세팅해줌으로써 combine은 새로운 값을 생성할 수 있다.
    * 근데 여기서는 아무것도 찍히지 않는다. 왜냐면
    * 그런데 onStart는 Flow를 리턴한다.
    * 왜냐면 onStart는 파라미터로 action이라는 함수형 변수를 받는데 이것의 타입은 FLowCollector.
    * 뭐라는지 잘 모르겠는데 대충 이 collector에 세팅해야하는 값이 들어있으므로 emit을 호출하지 않으면 아무것도 안한다.
    * 따라서 emit안했으므로 combine은 첫번째 플로우 값을 받지 못했고 combine못하므로 아무것도 출력되지 않는다.
    * 대충요약, onStart는 Flow를 리턴하는데 이 플로우를 emit해주지 않았으므로 combine은 첫번쨰 value를 받지 못해 출력도 못한다.
    * */

    delay(1000)
}