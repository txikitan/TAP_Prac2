/*-------------------------------------------------------------
- TAP JavaDataFrame: Scala implementation of the listFilterMap method
    using the two paradigms of recursion
    
    Gabriel Garcia
/-------------------------------------------------------------*/
import scala.collection.mutable.ListBuffer

class ListFilterMap {

  /*Stack recursion*/
  def listFilterMapStack[A,B](condition: A=>Boolean, operation:A=>B, collection:List[A]):List[B] = {
    collection match {
      case Nil => Nil
      case x :: xs => if(condition(x)) { //filter
        operation(x)::listFilterMapStack(condition, operation, xs) // apply de operation and then recursive call
      } else listFilterMapStack(condition,operation,xs)
    }
  }
  /*Tail recursion*/
  def listFilterMapTail[A,B](condition: A=>Boolean, operation:A=>B, collection:List[A], accum:ListBuffer[B]): List[B] = {
    collection match {
      case Nil => accum.toList    // finished working with the list: return the accumulated result
      case x::xs => if(condition(x)) {
        accum+=operation(x) // update the accumulator if filtered
        listFilterMapTail(condition, operation, xs, accum) // recursive call
       }
        else listFilterMapTail(condition,operation,xs, accum)
    }
  }
}
