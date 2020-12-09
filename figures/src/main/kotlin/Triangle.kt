import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.sqrt

@Serializable
@SerialName("triangle")
data class Triangle(val a: Double, val b: Double, val c: Double) : GeometricalShape {
    init {
        if (a <= 0 || b <= 0 || c <= 0)
            throw  IllegalArgumentException("Sides must be positive")
        if (a + b < c || a + c < b || b + c < a)
            throw IllegalArgumentException("Nonexistent triangle")
    }

    override fun calcArea(): Double {
        val p = calcPerimeter() / 2
        return sqrt(p * (p - a) * (p - b) * (p - c))
    }

    override fun calcPerimeter(): Double {
        return a + b + c
    }

    override fun toString(): String {
        return "Triangle $a, $b, $c"
    }
}
