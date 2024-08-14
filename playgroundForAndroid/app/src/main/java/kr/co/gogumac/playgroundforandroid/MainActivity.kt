package kr.co.gogumac.playgroundforandroid

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.gogumac.playgroundforandroid.ui.theme.PlaygroundForAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaygroundForAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenA()
                }
            }
        }
    }
}

@Composable
fun ScreenA(modifier: Modifier=Modifier){
    val context= LocalContext.current
    BackHandler {
        Toast.makeText(context,"ScreenA",Toast.LENGTH_SHORT).show()
    }
    Box(modifier = modifier
        .padding(10.dp)
        .background(color = Color.Green)){
        ScreenB(Modifier.fillMaxSize())
    }
}

@Composable
fun ScreenB(modifier: Modifier=Modifier){
    val context= LocalContext.current
    BackHandler {
        Toast.makeText(context,"ScreenB",Toast.LENGTH_SHORT).show()
    }
    Box(modifier = modifier
        .padding(10.dp)
        .background(color = Color.Yellow)){
        ScreenC(Modifier.fillMaxSize())
    }
}

@Composable
fun ScreenC(modifier: Modifier=Modifier){
    val context= LocalContext.current
    BackHandler {
        Toast.makeText(context,"ScreenC",Toast.LENGTH_SHORT).show()
    }
    Box(modifier = modifier
        .padding(10.dp)
        .background(color = Color.Blue)){
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlaygroundForAndroidTheme {
        Greeting("Android")
    }
}