val subjects = List("Noel", "The cat", "The dog")
val verbs = Map(
  "Noel" -> List("wrote", "chased", "slept on"),
  "The cat" -> List("meowed at", "chased", "slept on"),
  "The dog" -> List("barked at", "chased", "slept on")
)
val objects = Map(
  "wrote" -> List("the book", "the letter", "the code"),
  "chased" -> List("the ball", "the dog", "the cat"),
  "slept on" -> List("the bed", "the mat", "the train"),
  "meowed at" -> List("Noel", "the door", "the food cupboard"),
  "barked at" -> List("the postman", "the car", "the cat")
)


object Distribution {
  def uniform[A](atoms: List[A]): Distribution[A] = {
    val probability = 1.0 / atoms.size
    Distribution(atoms.map(_ -> probability))
  }
}

final case class Distribution[A](events: List[(A, Double)]) {
  def normalize: Distribution[A] = {
    val totalWeight = events.map(_._2).sum
    Distribution(events.map {
      case (a, p) => a -> (p / totalWeight)
    })
  }

  def compact: Distribution[A] = {
    val distinctEvents = events.map(_._1).distinct

    def eventWithProbablitly(a: A): (A, Double) =
      a -> events.filter(_._1 == a).map(_._2).sum

    Distribution(distinctEvents.map(eventWithProbablitly))
  }


  def map[B](f: A => B): Distribution[B] =
    Distribution(events.map {
      case (a, p) => f(a) -> p
    })

  def flatMap[B](f: A => Distribution[B]): Distribution[B] =
    Distribution(events.flatMap {
      case (a, p1) => f(a).events.map {
        case (b, p2) => b -> (p1 * p2)
      }
    }).compact.normalize
}

sealed trait Coin
case object Heads extends Coin
case object Tails extends Coin

val fairCoin: Distribution[Coin] = Distribution.uniform(List(Heads, Tails))

for {
  f1 <- fairCoin
  f2 <- fairCoin
  f3 <- fairCoin
} yield (f1, f2, f3)

//val sentences = for {
//  subject <- subjects
//  verb <- verbs(subject)
//  obj <- objects(verb)
//} yield (subject, verb, obj)