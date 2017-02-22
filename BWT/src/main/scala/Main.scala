/**
  * @author Oren Afek
  * @since 05/02/17
  */
object Main {

  def main(args: Array[String]): Unit = {
    val source = getClass.getResourceAsStream("/random1.txt")
    val strings = scala.io.Source.fromInputStream(source).getLines.toList.flatten
    println(Bijective.transform(strings)




  }

}
