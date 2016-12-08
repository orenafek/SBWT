/**
  * Created by sorimar on 12/8/2016.
  */

import List.range
import List.fill

object Main {
  def main(args: Array[String]): Unit = {

  }

  type Suffix = Int

  def Oren(orig: List[Char]): List[Char] = {
    val paddingChar = '$'
    val word = padWord()
    val suffixes = wordSuffixes()
    val groups = splitToThreeGroups(suffixes)
    val sortedFirstGroup = radixSort(groups._1)
    val mergedOtherGroups = tripletsMergeSort(groups._2, groups._3)
    val allMerged = merge(sortedFirstGroup, mergedOtherGroups)
    extractLastColumn(allMerged)

    def padWord(): List[Char] = orig ::: fill(math.pow(3, math.ceil(math.log(orig.length) / math.log(3))).toInt - orig.length)(paddingChar)

    def wordSuffixes(): List[Suffix] = range(0, word.length)

    def splitToThreeGroups(suffixes: List[Suffix]): (List[Suffix], List[Suffix], List[Suffix]) = (suffixes.filter(_ % 3 == 0), suffixes.filter(_ % 3 == 1), suffixes.filter(_ % 3 == 2))

    def radixSort(group: List[Suffix]): List[Suffix] = {

      def radixSort(group: List[Suffix], idx: Int): List[Suffix] = {
        val $ = new Array[List[Suffix]](256)
        group.foreach(x => x :: $(word(x + idx)))
        range(0, 256).foreach(i => $(i) = radixSort($(i), idx + 1))
        $.reduce(_ ::: _)
      }
      radixSort(group, 0)
    }
    def tripletsMergeSort(g1: List[Suffix], g2: List[Suffix]): List[Suffix] = ???

    def merge(g1: List[Suffix], g2: List[Suffix]): List[Suffix] = ???

    def extractLastColumn(indexes: List[Suffix]): List[Char] = ???
  }
