import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.PI
import kotlin.math.pow

@Serializable
@SerialName("circle")
class Circle(val radius: Double) : Shape {
    init {
        if (radius <= 0)
            throw IllegalArgumentException("Radius must be positive")
    }

    override fun calcArea(): Double {
        return PI * radius.pow(2)
    }

    override fun calcPerimeter(): Double {
        return 2 * PI * radius
    }
}
