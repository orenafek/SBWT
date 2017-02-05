/**
  * @author Oren Afek & Ori Marcovich
  * @since 05/02/17
  */
object Bijective {

  type Word = String

  def transform(w: Word): Word = {

    def factorize(w: Word): List[Word] = ???

    def cyclicRotations(factors: List[Word]): List[Word] = ???

    def takeLast(factors: List[Word]): Word = ???

    takeLast(cyclicRotations(factorize(w)))
  }

  def inverse(η: Word): Word = {

    def bijectiveMatch(η: Word): Word = ???

    def multiThread(η: Word, θ: Word) = ???

    val θ = bijectiveMatch(η)

    multiThread(η, θ)

  }

}
