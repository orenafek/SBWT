

/**
  * @author Oren Afek & Ori Marcovitch
  * @since 05/02/17
  */
object Main {
  val random1 = "/random1.txt"

  def openFile[T](fileName: String, blockSize: Int): List[List[Char]] = {
    val source = getClass.getResourceAsStream("/random1.txt")
    val strings = scala.io.Source.fromInputStream(source).getLines.toList.flatten
    val slices = strings.grouped(blockSize).toList
    slices
  }

  def test(testObject: BurrowsWheelerTransform, fileName: String, blockSize: Int): Unit = {
    val sorted = openFile(fileName, blockSize).map(w => testObject.transformSlow(w).mkString)
    println(sorted.mkString)
  }

  def main(args: Array[String]): Unit = {
    test(new BurrowsWheelerTransform, random1, 500)
  }

}
