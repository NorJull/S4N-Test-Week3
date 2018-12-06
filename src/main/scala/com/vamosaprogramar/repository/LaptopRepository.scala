package com.vamosaprogramar.repository

import com.vamosaprogramar.entity.Laptop

object LaptopRepository {

  private val laptops = Map(1 -> Laptop(1, "LaptopXL", "Quad core Intel APOLLO", Some("Colombia")),
                            2 -> Laptop(2, "LaptopX", "CORE i7 vProv", Some("China")),
                            3 -> Laptop(3, "LaptopS", "CORE i5 8v Generation", None))

  def findById(id: Int): Option[Laptop] = laptops.get(id)

  def findAll : Iterable[Laptop] = laptops.values
}
