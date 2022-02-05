/*-------------------------------------------------------------
- TAP JavaDataFrame: Scala implementation of the composite pattern
    for the DataFrames.
    Gabriel Garcia
/-------------------------------------------------------------*/
import java.util
import java.util.function.Predicate
import scala.collection.mutable.ListBuffer

class Directory(val name:String) extends DataFrame {
  var children: ListBuffer[DataFrame] = new ListBuffer[DataFrame]()

  /*Adds a child to the directory*/
  def addChild(child: DataFrame): Unit = {
    children += child
    labels = labels + child.labels
    rows = rows + child.rows
  }
  /*Removes child from the directory*/
  def removeChild(child: DataFrame): Unit = {
    children -= child
    labels = labels - child.labels  // Thanks to this, we won't need to do a map and a sum in the size and columns methods
    rows = rows - child.rows
  }

  /*Returns a String with the result of the operation at for the specified row and label passed by parameter in all the
  * dataframes of the directory*/
  override def at(row:Int, label:String):String=  {
    val dirAt = new StringBuilder()
    children.foreach((child:DataFrame)=>if(child.at(row,label)!=null)dirAt.append(child.at(row,label)).append(";"))
    dirAt.toString()

  }

  /*Returns the full size in the directory(all files included)*/
  override def size: Int = rows 

  /*Returns number of labels in the directory (all files included)*/
  override def columns: Int = labels

  /*Main visitor method that will call the accept method for each dataframe in the directory*/
  override def accept(dataFrameVisitor: AbstractDataFrameVisitor,label: String, predicate: Predicate[String]): Unit = {
    dataFrameVisitor match {
      case dataFrameVisitor: CounterVisitor => // if we are on a counterVisitor; we just need to see if it's a directory or not, so we call the visit method directly over the Directory (we don't want to navigate through subdirectories)
        children.foreach(child=>dataFrameVisitor.visit(child,label,predicate))
      case _ => // if we are working with a FilterVisitor or any other Visitor, we will query each subdirectory dataframe recursively normally
        children.foreach(_.accept(dataFrameVisitor,label,predicate))
    }
  }

}

