/*-------------------------------------------------------------
- TAP JavaDataFrame: Scala implementation of the composite pattern
    for the DataFrames.
    Gabriel Garcia
/-------------------------------------------------------------*/
import java.util
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
    labels = labels - child.labels
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



}

