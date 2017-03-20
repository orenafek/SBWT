import Bijective.{Word, π}

/**
  * @author Oren Afek & Ori Marcovitch
  * @since 15/03/17
  */

trait BurrowsWheeler {
  type Word = List[Char]
  type π = Array[Int]

  def factorize(w: Word): List[Word] = List(w)

  def rotation(word: Word): Word = word match {
    case Nil => Nil
    case c :: cs => cs ::: List(c)
  }

  def lt(s1: String, s2: String): Boolean = (s2 * s1.length) >= (s1 * s2.length)

  def sort(l: List[Word]): List[Word] = {
    //println("Sorting Started")
    val returnValue = l.map(_.mkString).sortWith(lt).map(_.toList)
    //println("Sorting Finished")
    return returnValue
  }

  def takeLast(words: List[Word]): Word = words.map(_.last)

  def rotations(ωs: List[Word]): List[Word] = {

    def concatRotationAndContinue(α: Word, i: Int): List[Word] = α :: rotations(α, i - 1)

    def rotations(α: Word, i: Int): List[Word] = if (i == 0) Nil else concatRotationAndContinue(rotation(α), i)

    ωs.flatMap(w => rotations(w, w.length))
  }

  def transform(w: Word): Word = takeLast(sort(rotations(factorize(w))))

  def sortWithSorter(words: List[Word], sorter: (String, String) => Boolean): List[Word] =
    words.map(_.mkString).sortWith(sorter).map(_.toList)

  def transformWithSorter(w: Word, sorter: (String, String) => Boolean): Word =
    takeLast(sortWithSorter(rotations(factorize(w)), sorter))

  def inverse(η: Word): Word = {

    def Match(η: Word): π = {
      val n = η.size
      val Σ = 256
      val ϑ = new Array[Int](n)
      val counts = new Array[Int](Σ)
      for (i <- 0 until n)
        counts(η(i)) += 1
      val before = new Array[Int](Σ)
      before(0) = counts(0)
      for (c <- 1 until Σ)
        before(c) = before(c - 1) + counts(c - 1)
      val seen = new Array[Int](Σ)
      for (i <- 0 until n) {
        val c = η(i)
        ϑ(i) = before(c) + seen(c)
        seen(c) += 1
      }
      ϑ
    }

    def multiThread(η: Word, ϑ: π) = {
      val n = η.size
      val ⊥ = -17
      val T = new Array[Int](n)
      for (i <- 0 until n)
        T(i) = ϑ(i)
      var α: Word = Nil
      for (j <- 0 until n)
        if (T(j) != ⊥) {
          var k = j
          do {
            α = η(k) :: α
            val t = k
            k = T(k)
            T(t) = ⊥
          } while (T(k) != ⊥)
        }
      α
    }

    val ϑ = Match(η)

    multiThread(η, ϑ)

  }

}

object BurrowsWheeler extends BurrowsWheeler {

}
