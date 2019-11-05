import java.util.Date

trait Visitor {
  def id: String
  def createdAt: Date

  def age: Long = new Date().getTime() - createdAt.getTime()
}

case class Anonymous(
  id: String,
  createdAt: Date = new Date()
) extends Visitor

case class User(
  id: String,
  email: String,
  createdAt: Date = new Date()
) extends Visitor

object stuff {
  def older(v1: Visitor, v2: Visitor) =
    if(v1.age > v2.age) v1 else v2
}

trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
}

case class Circle(radius: Double) extends Shape {
  val sides = 1
  val perimeter = 2 * math.Pi * radius
  val area = math.Pi * radius * radius
}

sealed trait Rectangular extends Shape {
  def width: Double
  def height: Double
  val sides = 4
  override def perimeter = 2 * width + 2 * height
  override def area = width * height
}

case class Rect(width: Double, height: Double) extends Rectangular

case class Square(size: Double) extends Rectangular {
  val width = size
  val height = size
}


sealed trait DivRes
final case class Finite(value: Int) extends DivRes
case object Infinite extends DivRes

object divide {
  def apply(x: Int, y: Int) =
    if(y == 0) Infinite else Finite(x/y)
}

sealed trait LinkedList[T] {
  def length: Int = this match {
	  case Pair(head, tail) => 1 + tail.length
	  case End() => 0
  }

  def contains(x: T): Boolean = this match {
    case Pair(head, tail) =>
      if(x == head)
        true
      else
        tail.contains(x)
	  case End() => false
  }
}

final case class End[T]() extends LinkedList[T]
final case class Pair[T](head: T, tail: LinkedList[T]) extends LinkedList[T]
