import java.io.File
import java.nio.file.Paths

private val resourcePath: String = Paths.get("").toAbsolutePath().toString() + "/src/main/resources/"

fun loadResource(filename: String): List<Int> = File(pathname(filename)).readLines().map { it.toInt()}.toList()

private fun pathname(filename: String) = resourcePath + filename