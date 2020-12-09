import androidx.compose.desktop.Window
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

fun main() = Window(title = "Well, figures") {
    val list = arrayListOf(Circle(24.0), Triangle(10.0, 5.0, 6.0), Rectangle(20.0, 10.0), Square(25.0))
    MaterialTheme {
        Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Column(Modifier.fillMaxHeight(), Arrangement.spacedBy(5.dp, Alignment.Top)) {
                Button(modifier = Modifier.align(Alignment.CenterHorizontally).width(300.dp)
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 25.dp),
                    onClick = {}) {
                    Text("Удалить")
                }
                Button(modifier = Modifier.align(Alignment.CenterHorizontally).width(300.dp)
                    .padding(start = 10.dp, end = 10.dp),
                    onClick = {}) {
                    Text("Добавить треугольник")
                }
                Button(modifier = Modifier.align(Alignment.CenterHorizontally).width(300.dp)
                    .padding(start = 10.dp, end = 10.dp),
                    onClick = {}) {
                    Text("Добавить прямоугольник")
                }
                Button(modifier = Modifier.align(Alignment.CenterHorizontally).width(300.dp)
                    .padding(start = 10.dp, end = 10.dp),
                    onClick = {}) {
                    Text("Добавить круг")
                }
                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally).width(300.dp)
                        .padding(start = 10.dp, end = 10.dp),
                    onClick = {}) {
                    Text("Добавить квадрат")
                }
            }

            Box(
                modifier = Modifier.fillMaxHeight()
                    .background(color = Color(180, 180, 180))
                    .padding(10.dp)
            ) {
                val stateVertical = rememberScrollState(0f)
                val stateHorizontal = rememberScrollState(0f)

                ScrollableColumn(
                    modifier = Modifier.fillMaxHeight(),
                    scrollState = stateVertical
                ) {
                    Column {
                        for (item in 0..30) {
                            ListElement("Item in ScrollableColumn #$item")
                            if (item < 30) {
                                Spacer(modifier = Modifier.height(5.dp))
                            }
                        }
                    }
                }
                VerticalScrollbar(
                    modifier = Modifier.align(Alignment.CenterEnd)
                        .fillMaxHeight(),
                    adapter = rememberScrollbarAdapter(stateVertical)
                )
            }
        }
    }
}

@Composable
fun ListElement(string: String) =
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(text = string, color = Color.Blue, textAlign = TextAlign.Start)
        Row(modifier = Modifier.padding(end = 10.dp) ,horizontalArrangement = Arrangement.End) {
            Icon(Icons.Sharp.KeyboardArrowUp, Modifier.clickable { })
            Icon(Icons.Sharp.KeyboardArrowDown, Modifier.clickable { })
        }
    }
