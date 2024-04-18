package kr.co.gogumac.playgroundforandroid

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import kr.co.gogumac.playgroundforandroid.ui.theme.PlaygroundForAndroidTheme

@Composable
fun DrawingView(modifier: Modifier = Modifier) {
    var path by remember {mutableStateOf(Path()) }
    var currentPosition by remember{mutableStateOf(Offset.Zero)}
    Canvas(modifier.pointerInput(Unit){
        detectDragGestures(
            onDragStart = {
                path.moveTo(it.x,it.y)
            },
            onDragEnd={
            },
            onDrag={change,dragAmount->
                currentPosition=change.position
                with(change.position) {
                    path.lineTo(x, y)
                }
            }
        )
    }) {
        drawPath(path,color=Color.Blue)
    }
}


@Preview(showBackground = true)
@Composable
fun DrawingViewPreview(){
    PlaygroundForAndroidTheme {
        DrawingView(Modifier.fillMaxSize())
    }
}