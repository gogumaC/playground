package kr.co.gogumac.playgroundforandroid

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kr.co.gogumac.playgroundforandroid.ui.theme.PlaygroundForAndroidTheme

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val requestPermissionLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission()){isGranted:Boolean->
            if(isGranted){
                Toast.makeText(this,"권한 승인",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"권한 거부",Toast.LENGTH_SHORT).show()
            }
        }
        setContent {
            PlaygroundForAndroidTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
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