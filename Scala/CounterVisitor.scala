/*-------------------------------------------------------------
- TAP JavaDataFrame: Scala implementation of a counter visitor
    T
    Gabriel Garcia
/-------------------------------------------------------------*/
import java.util
import java.util.function.Predicate
import java.util.*

class CounterVisitor extends AbstractDataFrameVisitor {

  var files: Int = 0
  var dirs : Int = 0
  
  /*Pattern matching*/
  /*This visitor will count the number of files and directories that the visited dataframe has*/
  override def visit(df: DataFrame, label: String, predicate: Predicate[String]): Unit ={
    df match {
      case directory: Directory =>  
        dirs = dirs + 1
      case _ => files = files + 1
    }
  }

}
