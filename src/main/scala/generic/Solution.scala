package generic

object Solution {

  def main(args: Array[String]): Unit = {
    val lines = io.Source.stdin.getLines()
    val numberOfTestCases = lines.next().toInt
    for (index <- 0 until numberOfTestCases) {
      var currentNumber = lines.next().toInt

      var counter = 0;
      while(currentNumber > 0) {
        if (currentNumber % 2 != 0) {
          currentNumber = currentNumber - 1
        } else {
          currentNumber = currentNumber / 2
        }
        counter = counter + 1
      }

      println(counter)
    }
  }
}