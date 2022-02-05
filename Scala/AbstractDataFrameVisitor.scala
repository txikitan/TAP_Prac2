/*-------------------------------------------------------------
- TAP JavaDataFrame: Trait that defines the main visitor method

    Gabriel Garcia
/-------------------------------------------------------------*/
import java.util.function.Predicate
trait AbstractDataFrameVisitor {
  
  def visit(df:DataFrame, label:String, predicate : Predicate[String]):Unit
}
