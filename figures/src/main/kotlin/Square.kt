import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("square")
data class Square(val a: Double) : GeometricalShape {
    init {
        if (a <= 0)
            throw IllegalArgumentException("Side must be positive")
    }

    override fun calcArea(): Double {
        return a * a
    }

    override fun calcPerimeter(): Double {
        return 4 * a
    }

    override fun toString(): String {
        return "Square $a"
    }
}
