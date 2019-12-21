def addOptions(x: Option[Int], y: Option[Int]) =
  for {
    a <- x
    b <- y
  } yield a + b

addOptions(Some(1), Some(2))
addOptions(None, Some(3))

def addOptions2(x: Option[Int], y: Option[Int]) =
  x.flatMap(a => y.map(_ + a))

addOptions2(Some(1), Some(2))
addOptions2(None, Some(3))


def addOptions(x: Option[Int], y: Option[Int], z: Option[Int]) =
  for {
    a <- x
    b <- y
    c <- z
  } yield a + b + c

addOptions(Some(1), Some(2), Some(3))
addOptions(None, Some(3), None)

def addOptions2(x: Option[Int], y: Option[Int], z: Option[Int]) =
  x.flatMap(a => y.flatMap(b => z.map(_ + a + b)))

addOptions2(Some(1), Some(2), Some(3))
addOptions2(None, Some(3), None)


def myDivide(a: Int, b: Int): Option[Int] =
  (a, b) match {
    case (_, 0) => None
    case (a, b) => Some(a / b)
  }

myDivide(4, 2)
myDivide(1, 0)

import scala.util._

implicit class RichOptionConvert(val s: String) extends AnyVal {
  def toIntOption() =
    Try(s.toInt).toOption
}

def calculator(operand1: String, operator: String, operand2: String): Option[Int] = {
  for {
    x <- operand1.toIntOption()
    y <- operand2.toIntOption()
    result <- operator match {
      case "+" => Some(x + y)
      case "-" => Some(x - y)
      case "/" => myDivide(x, y)
      case "*" => Some(x * y)
      case _ => None
    }
  } yield result
}

calculator("1", "+", "2")
calculator("10", "/", "2")
calculator("10", "foo", "2")




