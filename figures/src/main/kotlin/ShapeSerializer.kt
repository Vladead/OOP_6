import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.cbor.Cbor
import kotlinx.serialization.decodeFromHexString
import kotlinx.serialization.encodeToHexString
import kotlinx.serialization.modules.*
import java.io.File

object ShapeSerializer {
    private val module = SerializersModule {
        polymorphic(GeometricalShape::class) {
            subclass(Rectangle::class)
            subclass(Circle::class)
            subclass(Square::class)
            subclass(Triangle::class)
        }
    }

    @ExperimentalSerializationApi
    private val format = Cbor { serializersModule = module }

    @ExperimentalSerializationApi
    fun encodeToFile(nameOfFile: String, collection: List<GeometricalShape>) {
        File(nameOfFile).writer().use {
            it.write(format.encodeToHexString(collection))
        }
    }

    @ExperimentalSerializationApi
    fun decodeToArrayList(nameOfFile: String) : ArrayList<GeometricalShape>?
    {
        File(nameOfFile).reader().use {
            return format.decodeFromHexString(it.readText()) as ArrayList<GeometricalShape>
        }
    }
}
