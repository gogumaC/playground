package kr.co.gogumac.playgroundforandroid

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import com.google.mlkit.vision.digitalink.Ink
import kr.co.gogumac.playgroundforandroid.ui.theme.PlaygroundForAndroidTheme

class MainActivity : ComponentActivity() {

    var inkBuilder= Ink.Builder()
    lateinit var strokeBuilder:Ink.Stroke.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            PlaygroundForAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DrawingView(Modifier.fillMaxSize())
                }
            }
        }
    }

    fun addNewTouchEvent(event: MotionEvent){
        val action=event.actionMasked
        val x=event.x
        val y=event.y
        val t=System.currentTimeMillis()

        when(action){
            MotionEvent.ACTION_DOWN->{
                strokeBuilder=Ink.Stroke.builder()
                strokeBuilder.addPoint(Ink.Point.create(x,y,t))
            }
            MotionEvent.ACTION_MOVE-> strokeBuilder.addPoint(Ink.Point.create(x,y,t))
            MotionEvent.ACTION_UP->{
                strokeBuilder.addPoint(Ink.Point.create(x,y,t))
                inkBuilder.addStroke(strokeBuilder.build())
            }
        }
    }

}