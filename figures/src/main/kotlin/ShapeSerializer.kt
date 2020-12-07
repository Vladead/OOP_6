import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.cbor.Cbor
import kotlinx.serialization.decodeFromHexString
import kotlinx.serialization.encodeToHexString
import kotlinx.serialization.modules.*
import java.io.File

object ShapeSerializer {
    private val module = SerializersModule {
        polymorphic(Shape::class) {
            subclass(Rectangle::class)
            subclass(Circle::class)
            subclass(Square::class)
            subclass(Triangle::class)
        }
    }

    @ExperimentalSerializationApi
    private val format = Cbor { serializersModule = module }

    @ExperimentalSerializationApi
    fun encodeToFile(nameOfFile: String, collection: List<Shape>) {
        File(nameOfFile).writer().use {
            it.write(format.encodeToHexString(collection))
        }
    }

    @ExperimentalSerializationApi
    fun decodeToArrayList(nameOfFile: String) : ArrayList<Shape>?
    {
        File(nameOfFile).reader().use {
            return format.decodeFromHexString(it.readText()) as ArrayList<Shape>
        }
    }
}
