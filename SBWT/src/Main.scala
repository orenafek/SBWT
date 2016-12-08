/**
  * Created by sorimar on 12/8/2016.
  */

import scala.math

object Main {
  def main(args: Array[String]): Unit = {
    print(padWord("abcd".toList, '$'))
  }

  type Suffix = Int

  def Oren(word: List[Char]): List[Char] = {
    val padded = padWord(word, '$')
    val suffixes = wordSuffixes(padded)
    val groups = splitToThreeGroups(suffixes)
    val sortedFirstGroup = radixSort(groups._1)
    val mergedOtherGroups = tripletsMergeSort(groups._2, groups._3)
    val allMerged = merge(sortedFirstGroup, mergedOtherGroups)
    extractLastColumn(allMerged)

  }

  def padWord(word: List[Char], char: Char): List[Char] = word ::: List.fill(math.pow(3, math.ceil(math.log(word.length) / math.log(3)) ).toInt- word.length)(char)

  def wordSuffixes(word: List[Char]): List[Suffix] = ???

  def splitToThreeGroups(word: List[Suffix]): (List[Suffix], List[Suffix], List[Suffix]) = ???

  def merge() = ???

  def radixSort(group: List[Suffix]): List[Suffix] = ???

  def tripletsMergeSort(g1: List[Suffix], g2: List[Suffix]): List[Suffix] = ???

  def merge(g1: List[Suffix], g2: List[Suffix]): List[Suffix] = ???

  def extractLastColumn(indexes: List[Suffix]): List[Char] = ???
}
