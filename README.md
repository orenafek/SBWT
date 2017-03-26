# SBWT - Scala Burrows Wheeler Transform (and more)

### By
* Oren Afek, oren.afek[at]cs.technion.ac.il, 315472589
* Ori Marcovitch sorimar[at]cs.technion.ac.il, 311545172

#### Developed in Scala (2.12) using SBT (0.13.8)

#### Project Type
This is a research project, hoping to be graded with a 100 :).

### Project Goals
In this project we implement the Bijective String Sorting Transform suggested by [**Gil & Scott**]( http://bijective.dogma.net/00yyy.pdf), which is based on the Burrows Wheeler Transform.
We also implement the standard Burrows Wheeler Transform.
This includes an implementation of a suffix array sorter which runs in linear time.


### What have we got
1. Implementation of the **Burrows Wheeler Transform** running in O(n^2) (`BurrowsWheelerTransform.transformSlow()`).
2. Implementation of the **Burrows Wheeler Transform** running in O(n) (`BurrowsWheelerTransform.transformLinear()`).
3. Implementation of the [**Skew**](http://www.cs.cmu.edu/~ckingsf/bioinfo-lectures/suffixarrays.pdf) algorithm for string suffix sorting in linear time (`SuffixesSorter.sort()`).
4. Implementation of the **Bijective String Sorting Transform** running in O(n^2) (`GilScottBijectiveTransform.transformSlow()`).
5. Implementation of the **Inverse Bijective String Sorting Transform** running in O(n) (`GilScottBijectiveTransform.inverse()`).


### About the implementation
* **BurrowsWheelerTransform.transformSlow:**
This is the basic implementation of the BW's transform: 
    ```scala
    takeLast(sort(rotations(w)))
    ```
    * `rotations(ω: Word): List[Word]` - calculates and returns all of the rotations of ω.
    * `sort(l: List[Word]): List[Word]` - sorts the rotations calculated above. The strings are compared 
    as they were in the same length : `(s2 * s1.length) >= (s1 * s2.length)`
    * `takeLast(words: List[Word]): Word` - returns the last column of the rotations of ω.
         
* **BurrowsWheelerTransform.transformLinear:**
This is a time complexity improvement to the basic implementation of the BW's transform: 
    ```scala
     SuffixesSorter.suffixesIndexesSorted(w).map(i => w((w.size + i - 1) % w.size))
    ```
     * `SuffixesSorter.suffixesIndexesSorted(w)` - creates suffixes of w and sorts them using the SuffixesSorter as explained below.
     * `map(i => w((w.size + i - 1) % w.size))` - extracts the last character of each sorted suffix. 
     
     
* SuffixesSorter - TODO: @orimarco

* Bijective - TODO: @orimarco
* Bijective inverse - TODO: @orimarco
* Technicalities
> In order to be consistant with the paper's sugested algorithems, we've used some greek alphabetic notations when naming variables and methods. In order to stay fully consistant, we've left some variables that should have normally been inlined, as they are.

### Usage guide
1. **Goto:** `SBWT\BWT\src\main\scala`
2. **Compile:** run scala compiler by: `scalac *.scala`
3. **Run:** `scala Main <file_name> <mode> [options]`
  
    > mode: BWT - for Burrows Wheeler Transform, GS - for Gil&Scott Transform
        options: -s: for slow, normal mode
                 -l: for fast, linear mode

### For example...
Given the string:
    `val str = now is the time for the truly nice people to come to the party`
```scala
(new BurrowsWheelerTransform).transformLinear(str)
```
and
```scala
(new BurrowsWheelerTransform).transformSlow(str)
```
both will return:
    `oewyeeosreeeepi mhchlmhp tttnt puio yttcefn  ooati       rrolt`
    
but
```scala
(GilScottBijectiveTransform).transformSlow(str)
```
will return:

    `yoeyeeosreeeepi mhchlmhp tttnt puio wttcefn  ooati       rrotl`
### Research Results
  TODO: @orenafek

