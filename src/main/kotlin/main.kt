import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.desktop.Window
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Suppress("EXPERIMENTAL_API_USAGE")
fun main() = Window(title = "Well, figures") {
    MaterialTheme(
            shapes = Shapes(RoundedCornerShape(0.dp), RoundedCornerShape(0.dp), RoundedCornerShape(0.dp)),
            colors = MaterialTheme.colors.copy(
                    primary = Color(80, 50, 50),
                    onPrimary = Color.Black
            )
    ) {
        val list = remember { mutableStateListOf<GeometricalShape>() }
        AppWindowAmbient.current?.events?.onOpen = {
            val tempList: ArrayList<GeometricalShape>?
            try {
                tempList = ShapeSerializer.decodeToArrayList("encoded.shape")
                if (!tempList.isNullOrEmpty())
                    list.addAll(tempList)
            } catch (exception: Exception) {
                println("File not found")
            }
        }
        AppWindowAmbient.current?.events?.onClose = {
            ShapeSerializer.encodeToFile("encoded.shape", list)
        }
        Box(Modifier.background(Color(80, 80, 80))) {
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Column(Modifier.fillMaxHeight(),
                        Arrangement.spacedBy(0.dp, Alignment.Top),
                        Alignment.CenterHorizontally
                ) {
                    TriangleCreateButton { list.add(it) }
                    SquareCreateButton { list.add(it) }
                    RectangleCreateButton { list.add(it) }
                    CircleCreateButton { list.add(it) }
                }
                Box(
                        modifier = Modifier.fillMaxHeight()
                                .background(color = Color(120, 120, 120))
                                .padding(10.dp)
                ) {
                    val stateVertical = rememberScrollState(0f)

                    ScrollableColumn(
                            modifier = Modifier.fillMaxHeight(),
                            scrollState = stateVertical
                    ) {
                        Column {
                            if (list.size > 0) {
                                repeat(list.size) { i ->
                                    ShapeListElement("${list[i]}",
                                            onDeleteClick = { list.removeAt(i) },
                                            onUpClick = { list.trySwap(i, i - 1) },
                                            onDownClick = { list.trySwap(i, i + 1) })
                                }
                            } else
                                Box(Modifier.fillMaxWidth())

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
