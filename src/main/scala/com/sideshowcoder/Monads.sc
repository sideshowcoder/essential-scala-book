import scala.util.Try

val opt1 = Some(1)
val opt2 = Some(2)
val opt3 = Some(3)

for {
  x <- opt1
  y <- opt2
  z <- opt3
} yield x + y + z

val seq1 = Seq(1)
val seq2 = Seq(2)
val seq3 = Seq(3)

for {
  x <- seq1
  y <- seq2
  z <- seq3
} yield x + y + z

val try1 = Try(1)
val try2 = Try(2)
val try3 = Try(3)

for {
  x <- try1
  y <- try2
  z <- try3
} yield x + y + z


