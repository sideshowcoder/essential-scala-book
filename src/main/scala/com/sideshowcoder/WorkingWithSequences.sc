case class Film(name: String, yearOfRelease: Int, imdbRating: Double)

case class Director(firstName: String, lastName: String, yearOfBirth: Int, films: Seq[Film]) {
  def name = s"${firstName} $lastName}"
}

val memento = Film("Memento", 2000, 8.5)
val darkKnight = Film("Dark Knight", 2008, 9.0)
val inception = Film("Inception", 2010, 8.8)

val highPlainsDrifter= Film("High Plains Drifter", 1973, 7.7)
val outlawJoseyWales = Film("The Outlaw Josey Wales", 1976, 7.9)
val unforgiven = Film("Unforgiven", 1992, 8.3)
val granTorino = Film("Gran Torino", 2008, 8.2)
val invictus = Film("Invictus", 2009, 7.4)
val predator = Film("Predator", 1987, 7.9)
val dieHard = Film("Die Hard", 1988, 8.3)
val huntForRedOctober = Film("The Hunt for Red October", 1990, 7.6)
val thomasCrownAffair = Film("The Thomas Crown Affair", 1999, 6.8)

val eastwood = Director("Clint", "Eastwood", 1930,
  Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino, invictus))

val mcTiernan = Director("John", "McTiernan", 1951,
  Seq(predator, dieHard, huntForRedOctober, thomasCrownAffair))

val nolan = Director("Christopher", "Nolan", 1970,
  Seq(memento, darkKnight, inception))

val someGuy = Director("Just", "Some Guy", 1990,
  Seq())

val directors = Seq(eastwood, mcTiernan, nolan, someGuy)

nolan.films.map(_.name)

directors.flatMap(_.films)
  .map(_.name)

mcTiernan.films.minBy(_.yearOfRelease)
  .yearOfRelease

directors.flatMap(_.films)
  .sortBy(_.imdbRating)
  .reverse

val allFilms = directors.flatMap(_.films)

allFilms.map(_.imdbRating).sum / allFilms.size


directors.foreach(d => d.films.foreach(f => println(s"Tonight only! ${f.name} by ${d.name}!")))

allFilms.sortBy(_.yearOfRelease).headOption

def smallest(s: Seq[Int]): Int =
  s.foldLeft(Int.MaxValue)(math.min)

smallest(Seq(10, 11, 1, 20))


val l = Seq(1, 1, 2, 4, 3, 4)

def unique(s: Seq[Int]): Seq[Int] =
  s.foldLeft(Seq.empty[Int])((uniqueS, i) => {
    if(uniqueS.contains(i))
      uniqueS
    else
      i +: uniqueS
  })

unique(l)


def reverse(s: Seq[Int]) =
  s.foldLeft(Seq.empty[Int])((reverseS, i) => i +: reverseS)

reverse(Seq(1,2,3))

def myMap[A, B](s: Seq[A], f: A => B) =
  s.foldRight(Seq.empty[B]){
    (a, seq) => f(a) +: seq
  }

myMap(Seq(1,2,3), (x: Int) => x + 1)


def myFoldLeft[A, B](seq: Seq[A], zero: B, f: (B, A) => B): B = {
  var res = zero
  seq.foreach { elt => res = f(res, elt) }
  res
}

myFoldLeft(Seq(1,2,3), 0, (acc: Int, i: Int) => acc + i)