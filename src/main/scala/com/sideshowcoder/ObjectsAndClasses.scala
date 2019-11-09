case class Person(firstName: String, lastName: String) {
  def name =
    s"$firstName $lastName"
}

object Stormtrooper {
  def inspect(person: Person) =
    person match {
      case Person("Luke", "Skywalker") => "Stop!"
      case Person("Han", "Solo") => "Stop!"
      case Person(first, _) => s"Move along $first"
    }
}

object alien {
  def greet(p: Person) =
    "Greetings " + p.name

  def greetByName(first: String = "Some", last: String = "Body") =
    "Greetings " + first + " " + last + "!"
}

object badassery {
  def badass = throw new Exception("Error")
  def other = null
}

case class Cat(name: String, color: String, food: String)

object cats {
  val oswald = Cat("Oswald", "black", "milk")
  val henderson = Cat("Henderson", "ginger", "chips")
  val quentin = Cat("Quentin", "tabby", "curry")
}

object ChipShop {
  def willServe(cat: Cat) =
    cat match {
      case Cat(_, _, "chips") => true
      case _ => false
    }
}

case class Adder(x: Int) {
  def apply(y: Int) =
    y + x
}

case class Counter(count: Int = 0) {
  def dec: Counter = dec()
  def inc: Counter = inc()
  def dec(n: Int = 1) = copy(count = count - n)
  def inc(n: Int = 1) = copy(count = count + n)
  def adjust(adder: Adder) = copy(count = adder(count))
}


class Timestamp(val secs: Long)

object Timestamp {
  def apply(hs: Int, mins: Int, secs: Int) =
    new Timestamp(hs * 3600 + mins * 60 + secs)
}
