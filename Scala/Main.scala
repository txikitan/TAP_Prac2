/*-------------------------------------------------------------
- TAP JavaDataFrame: Scala main class to demostrate all the
      features implemented

    Gabriel Garcia
/-------------------------------------------------------------*/
import java.util.function.Predicate
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters.*

object Main {
  def main(args: Array[String]): Unit = {
    /*Demonstration of the composite implemented*/
    var dir = new Directory("Directory")
    var subdir = new Directory("Subdirectory")
    val CSVdf1 = new CSVDataFrame("cities.csv")
    val CSVdf2 = new CSVDataFrame("cities.csv")
    val CSVdf3 = new CSVDataFrame("cities.csv")
    subdir.addChild(CSVdf3)
    dir.addChild(subdir)
    dir.addChild(CSVdf1)
    dir.addChild(CSVdf2)
    println(dir.at(0,"LatD"))
    println(dir.size())
    println(dir.columns())


    /*Example predicate for the filterVisitor*/
    val predicateVisitor : Predicate[String] = {(p:String) =>
      if (p.matches("[0-9]+")) p.toInt > 45
      else false
    }
    /*Demonstration of the two visitors implemented*/
    val v = new FilterVisitor()
    dir.accept(v,"LatD",predicateVisitor)
    println("Filtered: " + v.elements)
    val c = new CounterVisitor()
    dir.accept(c,"LatD",predicateVisitor)
    println("DataFrame files: " + c.files + " DataFrame dirs: " + c.dirs)


    /*Condition and operation for the two demonstrations of the listFilterMap*/
    /*Round the values of a float-type column that are greater than a value*/
    def condition(dato : String): Boolean = { // greater than 45
      if(dato.toFloat>45) true
      else false

    }
    def operation(dato:String) : Int = {
      val floatVal = dato.toFloat
      val rounded = floatVal.round
      rounded
    }
    /*Replace a certain word from a string-type column on the elements that contain that word*/
    def condition2(dato : String): Boolean = {
      val wordToMatch = "Winchester"
      if(dato.equalsIgnoreCase(wordToMatch)) true
      else false
    }
    def operation2(dato:String) : String = {
      val wordToReplace = "Replaced"
      wordToReplace
    }
    /*Demonstration of the two implementations of listFilterMap*/
    val CSVdf = new CSVDataFrame("cities.csv")
    val listJava = CSVdf.getCol("LatD")
    val listJava2 = CSVdf.getCol("City")
    val listScala = listJava.asScala.toList
    val listScala2 = listJava2.asScala.toList
    val listAccum:List[Int] = List()
    val listFilterMap = new ListFilterMap
    val listTail = listFilterMap.listFilterMapTail(condition, operation, listScala,listAccum)
    val listStack = listFilterMap.listFilterMapStack(condition, operation, listScala)
    val listTail2 = listFilterMap.listFilterMapTail(condition2, operation2, listScala2,listAccum)
    val listStack2 = listFilterMap.listFilterMapStack(condition2, operation2, listScala2)
    println("Result of rounding values using tail recursion: "+ listTail.toString())
    println("Result of rounding values using stack recursion: "+ listStack.toString())
    println("Result of replacing values using tail recursion(number of values replaced): "+ listTail2.size)
    println("Result of replacing values using stack recursion(number of values replaced): "+ listStack2.size)
  }
}
