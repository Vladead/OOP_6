import androidx.compose.desktop.Window
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

fun main() = Window(title = "Well, figures") {
    val list = arrayListOf(Circle(24.0), Triangle(10.0, 5.0, 6.0), Rectangle(20.0, 10.0), Square(25.0))
    MaterialTheme(shapes = Shapes(RoundedCornerShape(0.dp), RoundedCornerShape(0.dp), RoundedCornerShape(0.dp)),
            colors = MaterialTheme.colors.copy(primary = Color(80, 50, 50),
                    onPrimary = Color.Black)
    ) {
        Box(Modifier.background(Color(80, 80, 80))) {
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Column(Modifier.fillMaxHeight(), Arrangement.spacedBy(0.dp, Alignment.Top)) {
                    val isTriangleDialogSummoned = remember { mutableStateOf(false) }
                    Button(modifier = Modifier.align(Alignment.CenterHorizontally).width(300.dp),
                            onClick = { isTriangleDialogSummoned.value = true }) {
                        Text("Добавить треугольник")
                    }
                    if (isTriangleDialogSummoned.value) {
                        var retVal: MutableState<Triangle>
                        triangleDialog(
                                onDismissFun = { isTriangleDialogSummoned.value = false },
                                onCreated = { returned -> retVal = returned; isTriangleDialogSummoned.value = false; list.add(retVal.value) })
                    }

                    val isRectangleDialogSummoned = remember { mutableStateOf(false) }
                    Button(modifier = Modifier.align(Alignment.CenterHorizontally).width(300.dp),
                            onClick = { isRectangleDialogSummoned.value = true }) {
                        Text("Добавить прямоугольник")
                    }
                    if (isRectangleDialogSummoned.value) {
                        var retVal: MutableState<Rectangle>
                        rectangleDialog(
                                onDismissFun = { isRectangleDialogSummoned.value = false },
                                onCreated = { returned -> retVal = returned; isRectangleDialogSummoned.value = false; list.add(retVal.value) })
                    }

                    val isCircleDialogSummoned = remember { mutableStateOf(false) }
                    Button(modifier = Modifier.align(Alignment.CenterHorizontally).width(300.dp),
                            onClick = { isCircleDialogSummoned.value = true }) {
                        Text("Добавить круг")
                    }
                    if (isCircleDialogSummoned.value) {
                        var retVal: MutableState<Circle>
                        circleDialog(
                                onDismissFun = { isCircleDialogSummoned.value = false },
                                onCreated = { returned -> retVal = returned; isCircleDialogSummoned.value = false; list.add(retVal.value) })
                    }

                    val isSquareDialogSummoned = remember { mutableStateOf(false) }
                    Button(
                            modifier = Modifier.align(Alignment.CenterHorizontally).width(300.dp),
                            onClick = { isSquareDialogSummoned.value = true }) {
                        Text("Добавить квадрат")
                    }
                    if (isSquareDialogSummoned.value) {
                        var retVal: MutableState<Square>
                        squareDialog(
                                onDismissFun = { isSquareDialogSummoned.value = false },
                                onCreated = { returned -> retVal = returned; isSquareDialogSummoned.value = false; list.add(retVal.value) })
                    }
                }
                Box(
                        modifier = Modifier.fillMaxHeight()
                                .background(color = Color(120, 120, 120))
                                .padding(10.dp)
                ) {
                    val stateVertical = rememberScrollState(0f)
                    val stateHorizontal = rememberScrollState(0f)

                    ScrollableColumn(
                            modifier = Modifier.fillMaxHeight(),
                            scrollState = stateVertical
                    ) {
                        Column {
                            for (i in list.indices) {
                                ListElement("${list[i]}",
                                        { list.removeAt(i) },
                                        {},
                                        {})
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
}

@Composable
fun ListElement(string: String,
                onDeleteClick: () -> Unit,
                onUpClick: () -> Unit,
                onDownClick: () -> Unit) =
        Row(horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(top = 2.dp, bottom = 2.dp)
                        .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(25))
        ) {
            Icon(Icons.Sharp.Close, Modifier.clickable { })
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = string, textAlign = TextAlign.Start, modifier = Modifier.padding(start = 5.dp))
                Row(modifier = Modifier.padding(end = 10.dp), horizontalArrangement = Arrangement.End) {
                    Icon(Icons.Sharp.KeyboardArrowUp, Modifier.clickable { })
                    Icon(Icons.Sharp.KeyboardArrowDown, Modifier.clickable { })
                }
            }
        }
