package com.sideshowcoder

object FirstSteps {
  object Test1 {
    def hello(name: String): String = "Hello " + name
  }

  object Test2 {
    val name = "Phil"
    def hello(other: String): String =
      name + " says hi to " + other
  }

  object Test3 {
    val myField = {
      println("Waat")
      42
    }

    def myMethod = {
      println("wuut")
      43
    }
  }

  object Oswald {
    val color = "black"
    val favoriteFood = "Milk"
  }

  object Henderson {
    val color = "Ginger"
    val favoriteFood = "Chips"
  }

  object Quentin {
    val color = "Tabby and white"
    val favoriteFood = "Curry"
  }

  object calc2 {
    def square(x: Double): Double = x * x
    def cube(x: Double): Double = square(x) * x

    def square(x: Int): Int = x * x
    def cube(x: Int): Int = square(x) * x
  }

  object person {
    val firstName = "Phil"
    val lastName = "Fehre"
  }

  object alien {
    def greet(p: person.type): String = "Greetings " + p.firstName
  }


}
