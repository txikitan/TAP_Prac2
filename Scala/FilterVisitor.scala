/*-------------------------------------------------------------
- TAP JavaDataFrame: Scala implementation of a filter visitor

    Gabriel Garcia
/-------------------------------------------------------------*/
import java.util
import java.util.function.Predicate
import java.util.*


class FilterVisitor extends AbstractDataFrameVisitor {

  var elements : StringBuilder = new StringBuilder()
  /*This visitor will execute the query operation over every dataframe or subdirectory */
  def visit(df:DataFrame, label:String, predicate : Predicate[String]): Unit =  {
    val queryedMap: util.LinkedHashMap[String, util.List[String]] = df.query(label, predicate)
    val queryedCol: util.List[String] = queryedMap.get(label)
    elements.append(queryedCol.toString)
  }
}
