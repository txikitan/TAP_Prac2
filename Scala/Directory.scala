import java.util
import scala.collection.mutable.ListBuffer

class Directory(val name:String) extends DataFrame {
  var children: ListBuffer[DataFrame] = new ListBuffer[DataFrame]()

  def addChild(child: DataFrame): Unit = {
    children += child
    labels = labels + child.columns
    rows = rows + child.size()
  }

  def removeChild(child: DataFrame): Unit = {
    children -= child
    labels = labels - child.columns
    rows = rows - child.size()
  }

  override def at(row:Int, label:String):String=  {
    val dirAt = new StringBuilder()
    children.foreach((child:DataFrame)=>if(child.at(row,label)!=null)dirAt.append(child.at(row,label)).append(";"))
    dirAt.toString()

  }

  override def size: Int = {
    children.map(_.size()).sum
  }

  override def columns: Int = {
    children.map(_.columns()).sum
  }



}

