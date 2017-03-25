

/**
  * @author Oren Afek & Ori Marcovitch
  * @since 05/02/17
  */
object Main extends App {
  val random1 = "/random1.txt"

  def openFile[T](fileName: String, blockSize: Int): List[List[Char]] = {
    val source = getClass.getResourceAsStream("/random1.txt")
    val strings = scala.io.Source.fromInputStream(source).getLines.toList.flatten
    val slices = strings.grouped(blockSize).toList
    slices
  }

  def test(testObject: BurrowsWheelerTransform, fileName: String, blockSize: Int, isSlow: Boolean): Unit = {
    val chars = openFile(fileName, blockSize)
    if (isSlow && testObject != GilScottBijectiveTransform)
      println(chars.map(w => testObject.transformSlow(w).mkString).mkString)
    else
      println(chars.map(w => testObject.transformLinear(w).mkString).mkString)
  }

  def getTestObject(mode: String): BurrowsWheelerTransform = {
    if (mode.equalsIgnoreCase("bwt")) new BurrowsWheelerTransform
    else if (mode.equalsIgnoreCase("gs")) GilScottBijectiveTransform
    else null
  }

  def isSlow(mode: String): Boolean = mode.equalsIgnoreCase("-s")

  def isLinear(mode: String): Boolean = mode.equalsIgnoreCase("-l")

  override def main(args: Array[String]): Unit = {
    if (args.length < 2 || getTestObject(args(1)) == null ||
      (args.length == 4 && !isSlow(args(2)) && !isLinear(args(2)))) {
      println("usage: <file_name> <mode> [options]")
      println("mode: BWT - for Burrows Wheeler Transform, GS - for Gil&Scott Transform")
      println("options: -s: for slow, normal mode")
      println("         -l: for fast, linear mode")
    } else {
      val fileName = args(0)
      val testObject = getTestObject(args(1))
      test(testObject, fileName, 500, isSlow(args(2)))
    }
  }

}
