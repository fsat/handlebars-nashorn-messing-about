package foo

import java.io.{InputStreamReader, FileReader, File}
import javax.script._
import org.webjars.WebJarAssetLocator

import scala.collection.JavaConverters._

object Hello {
  val engineManager = new ScriptEngineManager(this.getClass.getClassLoader)
  val engine = engineManager.getEngineByName("nashorn")

  val webJarAssetLocator = new WebJarAssetLocator(
    WebJarAssetLocator.getFullPathIndex(""".*""".r.pattern, this.getClass.getClassLoader)
  )
  val compiledHandlebars = compile(engine, s"/${webJarAssetLocator.getFullPath("handlebars.js")}")
  val compiledScript = compile(engine, "/script.js")

  def main(args: Array[String]): Unit = {
    val bindings = engine.createBindings()
    compiledHandlebars.eval(bindings)

    bindings.put("defaultTemplate", "hello {{ name }}")
    bindings.put("overrideTemplate", "this is override {{ name }}")
    bindings.put("context", Map("data" -> """ { "name": "city wok" } """).asJava)

    val returnValue = compiledScript.eval(bindings)
    println("----")
    println(returnValue)
    println("----")
  }

  private def compile(engine: ScriptEngine, path: String): CompiledScript =
    engine
      .asInstanceOf[Compilable]
      .compile(new InputStreamReader(this.getClass.getResourceAsStream(path)))
}
