object Main {
  def main(args: Array[String]): Unit ={
    var directory = new Directory("Directorio")
    var CSVdf : CSVDataFrame = new CSVDataFrame("cities.csv")
    var CSVdf2 : CSVDataFrame = new CSVDataFrame("cities.csv")
    directory.addChild(CSVdf)
    directory.addChild(CSVdf2)
    System.out.println(directory.size)

  }
}
