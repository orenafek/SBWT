/**
  * Created by sorimar on 12/8/2016.
  */

import List.range
import List.fill

object Main {
  def main(args: Array[String]): Unit = {

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

  def padWord(word: List[Char], char: Char): List[Char] = word ::: fill(math.pow(3, math.ceil(math.log(word.length) / math.log(3))).toInt - word.length)(char)

  def wordSuffixes(word: List[Char]): List[Suffix] = range(0, word.length)

  def splitToThreeGroups(word: List[Suffix]): (List[Suffix], List[Suffix], List[Suffix]) = (word.filter(_ % 3 == 0), word.filter(_ % 3 == 1), word.filter(_ % 3 == 2))

  def merge() = ???

  def radixSort(group: List[Suffix]): List[Suffix] = ???

  def tripletsMergeSort(g1: List[Suffix], g2: List[Suffix]): List[Suffix] = ???

  def merge(g1: List[Suffix], g2: List[Suffix]): List[Suffix] = ???

  def extractLastColumn(indexes: List[Suffix]): List[Char] = ???
}
