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


    /*Example predicate for the filterVisitor*/
    val predicate2 : Predicate[String] = {(p:String) =>
      if (p.matches("[0-9]+")) p.toInt > 45
      else false
    }
    /*Demonstration of the two visitors implemented*/





    /*Demonstration of the recursive listFilterMap */
    val CSVdf = new CSVDataFrame("cities.csv")
    val listJava = CSVdf.getCol("LatD")
    val listScala = listJava.asScala.toList

    /*Condition and operation for the two demonstrations of the listFilterMap*/
    /*Round the values of a float-type column that are greater than a value*/
    def condition(dato : String): Boolean = {
      if(Integer.valueOf(dato)>45) true
      else false
    }
    def operation(dato:String) : Int = {
      Integer.valueOf(dato) * 2
    }
    /*Replace a certain word from a string-type column on the elements that contain that word*/
    def condition2(dato : String): Boolean = {
      if(Integer.valueOf(dato)>45) true
      else false
    }
    def operation2(dato:String) : Int = {
      Integer.valueOf(dato) * 2
    }
    /*Demonstration of the two implementations of listFilterMap*/
    val listAccum = new ListBuffer[Int]
    val listFilterMap = new ListFilterMap
    val listTail = listFilterMap.listFilterMapTail(condition, operation, listScala,listAccum)
    val listStack = listFilterMap.listFilterMapStack(condition, operation, listScala)
    println(listTail.toString())
    println(listStack.toString())
  }
}
