package foo

import java.io.{FileReader, File}
import javax.script.{SimpleBindings, Bindings, ScriptEngine, ScriptEngineManager}
import scala.collection.JavaConverters._

object Hello {
  def main(args: Array[String]): Unit = {
    val engineManager = new ScriptEngineManager(this.getClass.getClassLoader)
    val engine = engineManager.getEngineByName("nashorn")
    println(s"Got engine [$engine]")

    loadJs(engine, "/handlebars-4.0.5.js")
    val handlebars = engine.eval("Handlebars")
    println(s"Handlebars compiled - $handlebars")

    engine.put("defaultTemplate", "hello {{ name }}")
    engine.put("overrideTemplate", "this is override {{ name }}")
    engine.put("context", Map("data" -> """ { "name": "city wok" } """).asJava)

    val returnValue = loadJs(engine, "/script.js")
    println("----")
    println(returnValue)
    println("----")
  }

  private def loadJs(engine: ScriptEngine, file: String, bindings: Option[Bindings] = None): AnyRef = {
    val scriptJs = new File(this.getClass.getResource(file).getFile)
    val fileReader = new FileReader(scriptJs)
    bindings.fold(engine.eval(fileReader))(engine.eval(fileReader, _))
  }
}
