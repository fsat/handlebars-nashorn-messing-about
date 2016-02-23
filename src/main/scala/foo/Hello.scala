package foo

import java.io.{FileReader, File}
import javax.script._
import scala.collection.JavaConverters._

object Hello {
  val engineManager = new ScriptEngineManager(this.getClass.getClassLoader)
  val engine = engineManager.getEngineByName("nashorn")
  val compiledHandlebars = compile(engine, "/handlebars-4.0.5.js")
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
      .compile(new FileReader(new File(this.getClass.getResource(path).getFile)))
}
