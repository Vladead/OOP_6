import androidx.compose.runtime.snapshots.SnapshotStateList

/**
 * Just a function to swap 'dem list elements, as it seems that standard interface has no such heresy.
 */
fun <T> SnapshotStateList<T>.trySwap(a: Int, b: Int) {
    val d = if (a >= b) a else b
    val c = if (a >= b) b else a
    if (c < 0 || d < 0 || c > this.lastIndex || d > this.lastIndex || c == d)
        return
    var tempT = this[c]
    this.removeAt(c)
    this.add(d - 1, tempT)
    tempT = this[d]
    this.removeAt(d)
    this.add(c, tempT)
}
