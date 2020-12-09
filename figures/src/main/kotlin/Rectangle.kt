import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("rectangle")
data class Rectangle(val length: Double, val width: Double) : GeometricalShape {
    init {
        if (length <= 0 || width <= 0)
            throw IllegalArgumentException("Length and width must be positive")
    }

    override fun calcArea(): Double {
        return length * width
    }

    override fun calcPerimeter(): Double {
        return 2 * (length + width)
    }

    override fun toString(): String {
        return "Rectangle ${length}x$width"
    }
}
