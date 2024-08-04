package kr.co.gogumac.playgroundforandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kr.co.gogumac.playgroundforandroid.ui.theme.PlaygroundForAndroidTheme


@Serializable
object ScreenA

@Serializable
data class ScreenB(val arg: String)

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
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = ScreenA) {
                        composable<ScreenA> {
                            ScreenA(navigateToScreenB = { navController.navigate(ScreenB("Hello")) })
                        }
                        composable<ScreenB> { navBackStackEntry ->
                            val route = navBackStackEntry.toRoute<ScreenB>()
                            ScreenB(
                                navigateToScreenA = { navController.navigate(ScreenA) },
                                arg = route.arg
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenA(navigateToScreenB: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text("ScreenA")
        Button(onClick = navigateToScreenB) {
            Text("go to ScreenB")
        }
    }
}

@Composable
fun ScreenB(navigateToScreenA: () -> Unit, arg: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text("ScreenB : arg = $arg")
        Button(onClick = navigateToScreenA) {
            Text("go to ScreenA")
        }
    }
}


