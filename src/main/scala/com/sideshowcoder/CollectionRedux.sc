import scala.collection.mutable

object scratch {
  val animals = Seq("cat", "dog", "penguin")

  "mouse" +: animals :+ "tyrannosaurus"

  2 +: animals // => success Seq[Any]

  val mutableAnimals = mutable.Seq("cat", "dog", "penguin")

  // mutableAnimals(1) = 2 // => Fails with type error!

  import java.nio.charset.StandardCharsets.UTF_8

  // ("aaaaaaaaaaaaaaaaaaaa" * 1000000)
  val largeValue = "a" * 1000

  (0 to 2048).map { x =>
    s"Key${x}" -> largeValue
  }.toMap
}
