package u05lab

import u05lab.code.PerformanceUtils
import org.junit.jupiter.api.Test

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class CollectionTest {
  import PerformanceUtils._

  @Test def performanceListVsListBuffer(): Unit = {
    val numberList: List[Int] = List(10, 20, 30 ,40)
    val numberListBuffer: ListBuffer[Int] = ListBuffer(10, 20, 30, 40)
    assert(measure("ListBuffer add")(numberListBuffer.+=(50)) < measure("List add")(numberList.+:(50)))
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

  @Test def performanceSet(): Unit = {
    val immutableSet: Set[Int] = Set(10 ,20 ,30 ,40 ,50)
    val mutableSet: collection.mutable.Set[Int] = collection.mutable.Set(10 ,20 ,30 ,40 ,50)

    assert(measure("Immutable set add")(immutableSet.+(60)) > measure("Mutable set add")(mutableSet.+=(60)))
    assert(measure("Immutable set delete")(immutableSet.-(60)) < measure("Mutable set delete")(mutableSet.-=(60)))
    assert(measure("Immutable set contains")(immutableSet.contains(60)) < measure("Mutable set contains")(mutableSet.contains(60)))
  }
}
