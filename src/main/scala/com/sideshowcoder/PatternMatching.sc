object Positive {
  def unapply(value: Int): Option[Int] =
    value match {
      case x if x > 0 => Some(x)
      case _ => None
    }
}

assert(
  "No" ==
    (0 match {
      case Positive(_) => "Yes"
      case _ => "No"
    })
)

assert(
  "Yes" ==
    (42 match {
      case Positive(_) => "Yes"
      case _ => "No"
    })
)

object Titlecase {
  def unapply(value: String): Option[String] = {
    Some(
      value.split(" ")
        .map(_.capitalize)
        .mkString(" ")
    )
  }
}

assert(
  "Sir Lord Doctor David Gurnell" ==
    ("sir lord doctor david gurnell" match {
      case Titlecase(str) => str
    })
)