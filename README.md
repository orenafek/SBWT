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
4. Implementation of the **Inverse Bijective String Sorting Transform** running in O(n) (`GilScottBijectiveTransform.inverse()`).


### About the implementation
* BurrowsWheelerTransform.transformSlow - TODO: @orenafek

* BurrowsWheelerTransform.transformFast - TODO: @orenafek

* SuffixesSorter - TODO: @orimarco

* Bijective - TODO: @orimarco
* Bijective inverse - TODO: @orimarco
* Technicalities
> In order to be consistant with the paper's sugested algorithems, we've used some greek alphabetic notations when naming variables and methods. In order to stay fully consistant, we've left some variables that should have normally been inlined, as they are.

### Usage guide
  TODO: @orenafek
### For example...
  TODO: @orimarco
### Research Results
  TODO: @orenafek

