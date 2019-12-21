import math.abs

implicit val absOrdering: Ordering[Int] = Ordering.fromLessThan[Int](abs(_) < abs(_))

assert(List(-4, -1, 0, 2, 3).sorted(absOrdering) == List(0, -1, 2, 3, -4))
assert(List(-4, -3, -2, -1).sorted(absOrdering) == List(-1, -2, -3, -4))


assert(List(-4, -1, 0, 2, 3).sorted == List(0, -1, 2, 3, -4))
assert(List(-4, -3, -2, -1).sorted == List(-1, -2, -3, -4))


object Rational {
  implicit val ordering = Ordering.fromLessThan[Rational] { (x, y) =>
    (x.numerator.toDouble / x.denominator.toDouble) < (y.numerator.toDouble / y.denominator.toDouble)
  }
}

final case class Rational(numerator: Int, denominator: Int)


assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted ==
  List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))

//by totalPrice;
//by number of units; and
//by unitPrice.

final case class Order(units: Int, unitPrice: Double) {
  def total: Double = units * unitPrice
}

object Order {
  implicit val ordering: Ordering[Order] = Ordering.fromLessThan[Order](_.total < _.total)
}

object OrderByUnits {
  implicit val ordering: Ordering[Order] = Ordering.fromLessThan[Order](_.units < _.units)
}

object OrderByUnitPrice {
  implicit val ordering: Ordering[Order] = Ordering.fromLessThan[Order](_.unitPrice < _.unitPrice)
}


val orders = List(Order(10, 1.0), Order(5, 1.0), Order(1, 20.0))

orders.sorted
orders.sorted(OrderByUnits.ordering)
orders.sorted(OrderByUnitPrice.ordering)



trait Equal[A] {
  def equal(x: A, y: A): Boolean
}

case class APerson(name: String, email: String)

object EmailEqualImplicit {

  implicit object EmailEqual extends Equal[APerson] {
    override def equal(x: APerson, y: APerson) =
      x.email == y.email
  }

}

object NameEmailEqualImplicit {

  implicit object NameEmailEqual extends Equal[APerson] {
    override def equal(x: APerson, y: APerson) = (x.name, x.email) == (x.name, x.email)
  }

}

object Eq {
  def apply[A](x: A, y: A)(implicit equal: Equal[A]): Boolean =
    equal.equal(x, y)
}

object Equal {
  def apply[A](implicit instance: Equal[A]): Equal[A] =
    instance

  implicit class ToEqual[A](in: A) {
    def ===(other: A)(implicit equal: Equal[A]): Boolean =
      equal.equal(in, other)
  }
}

object Example {
  def byEmail = {
    import EmailEqualImplicit._
    Eq(APerson("Noel", "noel@example.com"), APerson("Noel", "noel@example.com"))
  }

  def byNameAndEmail = {
    import NameEmailEqualImplicit._
    Eq(APerson("Noel", "noel@example.com"), APerson("Noel", "noel@example.com"))
  }

  def companion = {
    import NameEmailEqualImplicit._
    Equal[APerson].equal(APerson("Noel", "noel@example.com"), APerson("Noel", "noel@example.com"))
  }
}


implicit class IntOps(i: Int) {
  def times(f: Int => Unit): Unit =
    for {
      x <- 0 until i
    } f(x)

  def yeah(): Unit =
    i.times(_ => println("yeah!"))
}


1.yeah()
-1.yeah()


3.times(x => println(s"$x this is Numberwang!"))


implicit val caseInsensitiveEqual: Equal[String] = (x: String, y: String) => x.equalsIgnoreCase(y)

import Equal._

"abcd".===("ABCD")

sealed trait JsValue {
  def stringify: String
}

final case class JsObject(values: Map[String, JsValue]) extends JsValue {
  def stringify = values
    .map { case (name, value) => "\"" + name + "\":" + value.stringify }
    .mkString("{", ",", "}")
}

final case class JsString(value: String) extends JsValue {
  def stringify = "\"" + value.replaceAll("\\|\"", "\\\\$1") + "\""
}

trait JsWriter[A] {
  def write(v: A): JsValue
}

implicit class JsUtil[A](v: A) {
  def toJson(implicit jsWriter: JsWriter[A]) =
    jsWriter.write(v)
}

import java.util.Date

implicit object StringWriter extends JsWriter[String] {
  override def write(v: String) = JsString(v)
}

implicit object DateWriter extends JsWriter[Date] {
  override def write(v: Date) = JsString(v.toString)
}

implicit object AnonymousWriter extends JsWriter[Anonymous] {
  override def write(v: Anonymous) =
    JsObject(Map(
      "id" -> v.id.toJson,
      "createdAt" -> v.createdAt.toJson
    ))
}

implicit object UserWriter extends JsWriter[User] {
  override def write(v: User) =
    JsObject(Map(
      "id" -> v.id.toJson,
      "email" -> v.email.toJson,
      "createdAt"-> v.createdAt.toJson
    ))
}

implicit object VisitorWriter extends JsWriter[Visitor] {
  override def write(v: Visitor) =
    v match {
      case anon: Anonymous => anon.toJson
      case user: User => user.toJson
    }
}

val visitors: Seq[Visitor] = Seq(Anonymous("001", new Date), User("003", "dave@xample.com", new Date))
visitors.map(_.toJson)

