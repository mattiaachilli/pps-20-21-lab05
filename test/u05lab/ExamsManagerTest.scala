package u05lab

import org.junit.jupiter.api.Assertions.{assertEquals, assertThrows}
import org.junit.jupiter.api.Test
import u05lab.code.ExamsManager.{ExamResult, ExamsManager, Kind}

class ExamsManagerTest {
  val examsManager: ExamsManager = ExamsManager()

  private def setupExams(): Unit = {
    examsManager.createNewCall("January")
    examsManager.createNewCall("February")
    examsManager.addStudentResult("January", "Achilli", ExamResult.succeeded(30, true))
    examsManager.addStudentResult("January", "Rossi", ExamResult.failed)
    examsManager.addStudentResult("February", "Achilli", ExamResult.retired)
    examsManager.addStudentResult("February", "Rossi", ExamResult.succeeded(20))
  }

  @Test def testGetAllStudentsFromCall(): Unit = {
    setupExams()
    assertEquals(Set("Achilli", "Rossi"), examsManager.getAllStudentsFromCall("January"))
    assertEquals(Set("Achilli", "Rossi"), examsManager.getAllStudentsFromCall("February"))
    assertThrows(classOf[IllegalArgumentException], () => examsManager.getAllStudentsFromCall("March"))
  }


}
