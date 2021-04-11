package u05lab

import u05lab.code.PerformanceUtils
import org.junit.jupiter.api.Assertions.{assertEquals, assertThrows}
import org.junit.jupiter.api.Test

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class CollectionTest {
  import PerformanceUtils._

  @Test def performanceListVsListBuffer(): Unit = {
    val numberList: List[Int] = List(10, 20, 30 ,40)
    val numberListBuffer: ListBuffer[Int] = ListBuffer(10, 20, 30, 40)
    assert(measure("ListBuffer add")(numberListBuffer.+=(50)) > measure("List add")(numberList.+:(50)))
    assert(measure("ListBuffer get")(numberListBuffer(1)) > measure("List get")(numberList(1)))
    assert(measure("ListBuffer delete")(numberListBuffer.-=(50)) > measure("List delete")(numberList.dropRight(1)))
    assert(measure("ListBuffer size")(numberListBuffer.size) < measure("List size")(numberList.size))
  }

  @Test def performanceVectorVsArrayVsArrayBuffer(): Unit = {
    val numberVector: Vector[Int] = Vector(10, 20, 30 ,40)
    val numberArray: Array[Int] = Array(10, 20, 30, 40)
    val numberArrayBuffer: ArrayBuffer[Int] = ArrayBuffer(10, 20, 30, 40)
    assert(measure("Vector add")(numberVector.+:(50)) < measure("ArrayBuffer add")(numberArrayBuffer.+:(50)))
    assert(measure("Vector get")(numberVector(1)) > measure("Array get")(numberArray(1)))
    assert(measure("Array get")(numberArray(1)) < measure("ArrayBuffer get")(numberArrayBuffer(1)))
    assert(measure("ArrayBuffer delete")(numberArrayBuffer.-(numberArrayBuffer.last)) > measure("Vector delete")(numberVector.dropRight(1)))
    assert(measure("Array size")(numberArray.length) < measure("ArrayBuffer size")(numberArrayBuffer.length))
    assert(measure("Vector size")(numberVector.size) > measure("Array size")(numberArray.length))
  }
}
