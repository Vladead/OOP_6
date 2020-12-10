import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun squareDialog(onDismissFun: () -> Unit, onCreated: (MutableState<Square>) -> Unit) {
    val a = mutableStateOf(0.0)
    val errorState = mutableStateOf(false)
    val currentRadius = remember { mutableStateOf("") }
    Dialog(onDismissRequest = onDismissFun) {
        Column(Modifier.fillMaxSize().padding(top = 5.dp), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
            if (errorState.value)
                Text("Such a square can't exist", modifier = Modifier.padding(5.dp))
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                DataInputRow("a", currentRadius.value, onValueChange = { str ->
                    run {
                        currentRadius.value = str
                        errorState.value = false
                        a.value = str.toDoubleOrNull() ?: 0.0
                    }
                })
            }
            Button(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                onClick = {
                    try {
                        onCreated(mutableStateOf(Square(a.value)))
                    } catch (e: Exception) {
                        errorState.value = true
                    }
                }) {
                Text("Create")
            }
        }
    }
}
