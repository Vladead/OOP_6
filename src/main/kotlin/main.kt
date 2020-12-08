import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import java.awt.image.BufferedImage

fun main() = Window(title = "Well, figures") {
    val count1 = remember { mutableStateOf(0) }
    val count2 = remember { mutableStateOf(0) }
    val checked1 = remember {mutableStateOf(false)}
    val checked2 = remember {mutableStateOf(false)}
    val list = arrayListOf(Circle(24.0), Triangle(10.0, 5.0, 6.0), Rectangle(20.0, 10.0), Square(25.0))
    MaterialTheme {
        Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Column(Modifier.fillMaxHeight().padding(10.dp), Arrangement.spacedBy(5.dp, Alignment.CenterVertically)) {
                Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            count1.value++
                        }) {
                    Text(if (count1.value == 0) "Hello World" else "Clicked ${count1.value}!")
                }
                Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            count1.value = 0
                        }) {
                    Text("Reset")
                }
                Checkbox(checked = checked1.value,
                        onCheckedChange = {
                    bool -> run { checked1.value = !checked1.value; if (bool) count1.value++ }
                                                                     },
                        modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            Column(Modifier.fillMaxHeight().padding(10.dp), Arrangement.SpaceAround) {
                Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            count2.value++
                        }) {
                    Text(if (count2.value == 0) "Hello World" else "Clicked ${count2.value}!")
                }
                Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            count2.value = 0
                        }) {
                    Text("Reset")
                }
                Checkbox(checked = checked2.value,
                        onCheckedChange = { bool -> run { checked2.value = !checked2.value; if (bool) count2.value++ } },
                        modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}
