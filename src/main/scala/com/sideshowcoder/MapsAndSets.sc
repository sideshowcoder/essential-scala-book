val people = Set(
  "Alice",
  "Bob",
  "Charlie",
  "Derek",
  "Edith",
  "Fred")

val ages = Map(
  "Alice"   -> 20,
  "Bob"     -> 30,
  "Charlie" -> 50,
  "Derek"   -> 40,
  "Edith"   -> 10,
  "Fred"    -> 60)

val favoriteColors = Map(
  "Bob"     -> "green",
  "Derek"   -> "magenta",
  "Fred"    -> "yellow")

val favoriteLolcats = Map(
  "Alice"   -> "Long Cat",
  "Charlie" -> "Ceiling Cat",
  "Edith"   -> "Cloud Cat")

def favoriteColor(name: String): String = {
  favoriteColors.get(name) getOrElse "beige"
}

favoriteColor("Bob")
favoriteColor("Phil")

def printColors() =
  for {
    name <- people
  } yield s"${name} favorite color is ${favoriteColor(name)}"

printColors()

def printColorsMap() =
  for {
    (name, color) <- favoriteColors
  } yield s"${name} favorite color is ${color}"

printColorsMap()


def lookup[A](m: Map[String, A], key: String): Option[A] =
  m.get(key)

lookup(ages, "Alice")
lookup(favoriteLolcats, "Alice")


val orderByAge = Ordering.by((_: (String, Int))._2).reverse

val oldestPersonName = ages
  .toSeq
  .sorted(orderByAge)
  .map(_._1)
  .headOption

for {
  name <- oldestPersonName
} yield favoriteColor(name)


val set1 = Set(1,2,3)
val set2 = Set(3,4,5)

def myUnion[A](s1: Set[A], s2: Set[A]): Set[A] =
  s1.foldLeft(s2) { _ + _ }

myUnion(set1, set2)

val map1 = Map('a' -> 1, 'b' -> 2, 'z' -> 1)
val map2 = Map('a' -> 2, 'b' -> 4, 'c' -> 1)

def myMapUnion[A, B](z: B)(m1: Map[A, B], m2: Map[A, B], add: (B, B) => B): Map[A, B] =
  m1.foldLeft(m2){ (unionM, elt) =>
    val (k, v) = elt
    unionM.updated(k, add(v, m2.getOrElse(k, z)))
  }

myMapUnion(0)(map1, map2, _ + _)

















































