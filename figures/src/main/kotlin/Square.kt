import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("square")
class Square(val a: Double) : Shape {
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
}
