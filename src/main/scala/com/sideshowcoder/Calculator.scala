package com.sideshowcoder

object Calculator {

  def square(x: Double): Double =
    x * x

  object test {
    assert(square(2.0) == 4.0)
    assert(square(-2.0) == 4.0)
    assert(square(3.0) == 9.0)
  }
}
