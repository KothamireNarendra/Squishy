import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bouncybutton.ui.theme.BouncyButtonTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BouncyButtonTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Greeting(
                        "Jetpack",
                        labelColor = Color.White,
                        color = Color(77, 83, 111),
                        borderColor = Color.Transparent
                    )
                    Spacer(modifier = Modifier.size(50.dp))
                    Greeting(
                        "Compose",
                        labelColor = Color(77, 83, 111),
                        color = Color.Transparent,
                        borderColor = Color(77, 83, 111)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    label: String,
    labelColor: Color,
    color: Color,
    borderColor: Color
) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(300.dp, 100.dp)
            .squishy(color = color, borderColor = borderColor)
    ) {
        Text(
            text = label,
            color = labelColor,
            fontSize = 30.sp,
        )
    }
}
