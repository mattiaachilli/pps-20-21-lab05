package u05lab

import u05lab.code.PerformanceUtils
import org.junit.jupiter.api.Assertions.{assertEquals, assertThrows}
import org.junit.jupiter.api.Test

import scala.collection.mutable.ListBuffer

class CollectionTest {
  import PerformanceUtils._

  @Test def performanceListVsListBuffer(): Unit = {
    val numberList: List[Int] = List(10, 20, 30 ,40)
    val numberListBuffer: ListBuffer[Int] = ListBuffer(10, 20, 30, 40)
    assert(measure("ListBuffer add")(numberListBuffer.+=(5)) > measure("List add")(numberList.+:(5)))
    assert(measure("ListBuffer size")(numberListBuffer.size) < measure("List size")(numberList.size))
    assert(measure("ListBuffer get")(numberListBuffer(1)) > measure("List get")(numberList(1)))
    assert(measure("ListBuffer delete")(numberListBuffer.-=(5)) > measure("List delete")(numberList.reverse.drop(1)))
  }
}
