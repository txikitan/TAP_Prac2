trait AbstractDataFrameVisitor {
  def visit(df:DataFrame, label:String):Unit
}
