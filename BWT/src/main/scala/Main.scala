/**
  * Created by sorimar on 12/8/2016.
  */

import List.range
import List.fill

object Main {
  val paddingChar = '$'

  def main(args: Array[String]): Unit = {
    print(oren(List('a', 'c', 'b')))
  }

  type Suffix = Int

  def padWord(orig: List[Char]): List[Char] = orig ::: fill(math.pow(3, math.ceil(math.log(orig.length) / math.log(3))).toInt - orig.length)(paddingChar)

  def oren(orig: List[Char]): List[Char] = {
    val word = padWord(orig)

    def wordSuffixes(): List[Suffix] = range(0, word.length)

    def splitToThreeGroups(suffixes: List[Suffix]): (List[Suffix], List[Suffix], List[Suffix]) = (suffixes.filter(_ % 3 == 0), suffixes.filter(_ % 3 == 1), suffixes.filter(_ % 3 == 2))

    def radixSort(group: List[Suffix]): List[Suffix] = {
      def radixSort(group: List[Suffix], idx: Int): List[Suffix] =
        group match {
          case Nil => Nil
          case x :: Nil => group
          case group => {
            val $ = new Array[List[Suffix]](256)
            group.foreach(x => x :: $(word(x + idx)))
            range(0, 256).foreach(i => $(i) = radixSort($(i), idx + 1))
            $.reduce(_ ::: _)
          }
        }
      radixSort(group, 0)
    }
    def tripletsMergeSort(g1: List[Suffix], g2: List[Suffix]): List[Suffix] = ???

    def merge(g1: List[Suffix], g2: List[Suffix]): List[Suffix] = ???

    def extractLastColumn(indexes: List[Suffix]): List[Char] = ???


    val suffixes = wordSuffixes()
    val groups = splitToThreeGroups(suffixes)
    val sortedFirstGroup = radixSort(groups._1)
    //    val mergedOtherGroups = tripletsMergeSort(groups._2, groups._3)
    //    val allMerged = merge(sortedFirstGroup, mergedOtherGroups)
    //    extractLastColumn(allMerged)
    print(radixSort(suffixes))

    Nil
  }
}
