package foo

import java.io.{FileReader, File}
import javax.script.{ScriptEngine, ScriptEngineManager}

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

    val returnValue = loadJs(engine, "/script.js")
    println("----")
    println(returnValue)
    println("----")
  }

  private def loadJs(engine: ScriptEngine, file: String): AnyRef = {
    val scriptJs = new File(this.getClass.getResource(file).getFile)
    engine.eval(new FileReader(scriptJs))
  }
}
