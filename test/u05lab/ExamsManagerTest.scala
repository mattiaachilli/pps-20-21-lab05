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

  @Test def testGetEvaluationsMapFromCall(): Unit = {
    setupExams()
    assertEquals(Map("Achilli" -> 30), examsManager.getEvaluationsMapFromCall("January"))
    assertEquals(Map("Rossi" -> 20), examsManager.getEvaluationsMapFromCall("February"))
  }

  @Test def testGetResultMapFromStudent(): Unit = {
    setupExams()
    assertEquals(Map("January" -> ExamResult.succeededCumLaude.toString, "February" -> ExamResult.retired.toString),
      examsManager.getResultsMapFromStudent("Achilli"))
    assertEquals(Map("January" -> ExamResult.failed.toString, "February" -> ExamResult.succeeded(20).toString),
      examsManager.getResultsMapFromStudent("Rossi"))
  }

  @Test def testGetBestResultFromStudent(): Unit = {
    setupExams()
    assertEquals(Some(30), examsManager.getBestResultFromStudent("Achilli"))
  }

}
