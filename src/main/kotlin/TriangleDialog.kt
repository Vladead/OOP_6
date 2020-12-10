import androidx.compose.desktop.AppWindowAmbient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.MenuBar

@Composable
fun TriangleDialog(onDismissFun: () -> Unit, onCreated: (MutableState<Triangle>) -> Unit) {
    val a = mutableStateOf(0.0)
    val b = mutableStateOf(0.0)
    val c = mutableStateOf(0.0)
    val errorState = mutableStateOf(false)
    val isFirst = mutableStateOf(true)
    val curA = remember { mutableStateOf("") }
    val curB = remember { mutableStateOf("") }
    val curC = remember { mutableStateOf("") }
    Dialog(onDismissRequest = onDismissFun) {
        MaterialTheme(shapes = Shapes(RoundedCornerShape(0.dp), RoundedCornerShape(0.dp), RoundedCornerShape(0.dp)),
                colors = MaterialTheme.colors.copy(primary = Color(80, 50, 50),
                        onPrimary = Color.Black)
        ) {
            if (isFirst.value) {
                AppWindowAmbient.current?.setTitle("Well, triangle")
                AppWindowAmbient.current?.setSize(600, 400)
                isFirst.value = false
            }
            Box(Modifier.background(Color(80, 80, 80))) {
                Column(Modifier.fillMaxSize().padding(top = 5.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                    if (errorState.value)
                        Text("Such a triangle can't exist", modifier = Modifier.padding(5.dp))
                    else
                        Box(Modifier.preferredHeight(28.dp)) // Precision magic!
                    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        DataInputRow("a", curA.value, onValueChange = { str ->
                            run {
                                curA.value = str
                                errorState.value = false
                                a.value = str.toDoubleOrNull() ?: 0.0
                            }
                        })
                        DataInputRow("b", curB.value, onValueChange = { str ->
                            run {
                                curB.value = str
                                errorState.value = false
                                b.value = str.toDoubleOrNull() ?: 0.0
                            }
                        })
                        DataInputRow("c", curC.value, onValueChange = { str ->
                            run {
                                curC.value = str
                                errorState.value = false
                                c.value = str.toDoubleOrNull() ?: 0.0
                            }
                        })
                    }
                    Button(modifier = Modifier.preferredWidth(200.dp).padding(top = 5.dp, bottom = 5.dp),
                            onClick = {
                                try {
                                    onCreated(mutableStateOf(Triangle(a.value, b.value, c.value)))
                                } catch (e: Exception) {
                                    errorState.value = true
                                }
                            }) {
                        Text("Create")
                    }
                }
            }
        }
    }
}
