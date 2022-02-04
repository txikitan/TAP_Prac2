/*-------------------------------------------------------------
- TAP JavaDataFrame: Trait that defines the main visitor method

    Gabriel Garcia
/-------------------------------------------------------------*/

trait AbstractDataFrameVisitor {
  def visit(df:DataFrame, label:String):Unit
}
