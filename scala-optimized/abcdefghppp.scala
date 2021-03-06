object ABCDEFGHPPP { 
  def main(args: Array[String]): Unit = {

    val start = System.currentTimeMillis()
    println("Start Calculation")
    val possibleToTry = (0 to 9)
      .toList
      .filter(x => x != 1)

    // first solve ef + gh = 111
    val efghs =
      possibleToTry
        .combinations(4)
        .flatMap(_.permutations)
        .filter {
          case List(e, f, g, h) =>  e > 0 && g > 0
        }
        .filter {
          case List(e, f, g, h) => 111 - (g * 10 + h) ==  e * 10 + f
        }
        .toList

    println(s"Time used to solve EF + GH = 111 = ${System.currentTimeMillis() - start}ms")

    // use the possible solution of ef + gh to curry on solve ab + cd
    efghs
      .flatMap(efgh => possibleToTry.diff(efgh).permutations.map(_.take(4)).map(_ ++ efgh))
      .filter {
        case List(a,b,c,d,e,f,g,h) => a > 0 && c > 0 && a > c
      }
      .filter {
        case List(a,b,c,d,e,f,g,h) => a * 10 + b - (c * 10 + d) == e * 10 + f
      }
      .foreach{
        case List(a,b,c,d,e,f,g,h) => println(s"a=$a,b=$b,c=$c,d=$d,e=$e,f=$f,g=$g,h=$h,p=1")
      }
    println(s"Total Time used to solve AB - CD = 111 - GH = EF ${System.currentTimeMillis() - start}ms")

  }
}
