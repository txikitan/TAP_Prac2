import java.util.function.Predicate

/*-------------------------------------------------------------
- TAP JavaDataFrame: Scala main class to demostrate all the
      features implemented

    Gabriel Garcia
/-------------------------------------------------------------*/
object Main {
  def main(args: Array[String]): Unit = {

    val predicate2 : Predicate[String] = {(p:String) =>
      if (p.matches("[0-9]+")) p.toInt > 45
      else false
    }


    val directory = new Directory("Directorio")
    val CSVdf = new CSVDataFrame("cities.csv")
    val CSVdf2 = new CSVDataFrame("cities.csv")
    directory.addChild(CSVdf)
    directory.addChild(CSVdf2)
    val directory2 = new Directory("Directorio2")
    directory.addChild(directory2)
    var CSVdf3 = new CSVDataFrame("cities.csv")
    val CSVdf4 = new CSVDataFrame("cities.csv")
    val directory3 = new Directory("Directorio3")
    directory.addChild(directory3)
    directory3.addChild(CSVdf4)
    directory.addChild(CSVdf4)
    val v = new CounterVisitor()
    directory.accept(v,"LatD",predicate2)
    println("DataFrame files: " + v.files + " DataFrame dirs: " + v.dirs)
  }
}
