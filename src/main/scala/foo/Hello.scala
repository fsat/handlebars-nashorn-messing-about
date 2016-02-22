package foo

import java.io.{FileReader, File}
import javax.script.ScriptEngineManager

object Hello {
  def main(args: Array[String]): Unit = {
    val engineManager = new ScriptEngineManager(this.getClass.getClassLoader)
    val engine = engineManager.getEngineByName("nashorn")
    println(s"Got engine [${engine}]")

    val scriptJs = new File(this.getClass.getResource("/script.js").getFile)
    val returnValue = engine.eval(new FileReader(scriptJs))

    println("----")
    println(returnValue)
    println("----")
  }
}
