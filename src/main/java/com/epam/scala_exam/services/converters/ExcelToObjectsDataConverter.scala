package com.epam.scala_exam.services.converters

import com.epam.scala_exam.model.{Client, GenderEnum, MaritalStatusEnum}
import org.apache.poi.ss.usermodel.{Row, Sheet}
import org.springframework.stereotype.Service

import scala.collection.mutable.ListBuffer

@Service
class ExcelToObjectsDataConverter {
  val titlesMap = collection.mutable.Map.empty[String, Int]

  def convertSheetToObjects(sheet: Sheet): List[Client] = {
    val clients: ListBuffer[Client] = new ListBuffer[Client]
    val itr = sheet.iterator()
    if (itr.hasNext) {
      createTitlesMap(itr.next())
    }
    while (itr.hasNext) {
      clients += convertRowToObject(itr.next())
    }
    clients.toList
  }

  private def createTitlesMap(titleRow: Row): Unit = {
    val titleRowIterator = titleRow.cellIterator()
    var i = 0
    while (titleRowIterator.hasNext) {
      titlesMap += titleRowIterator.next().toString -> i
      i += 1
    }
  }

  private def convertRowToObject(row: Row): Client = {
    Client(firstName = row.getCell(titlesMap("First Name")).toString, lastName = row.getCell(titlesMap("Last Name")).toString, gender = GenderEnum.withName(row.getCell(titlesMap("Gender")).toString.toLowerCase), age = row.getCell(titlesMap("Age")).toString.toDouble.toInt,
      email = row.getCell(titlesMap("Email")).toString, phoneNumber = row.getCell(titlesMap("Phone")).toString, education = row.getCell(titlesMap("Education")).toString, occupation = row.getCell(titlesMap("Occupation")).toString, salary = row.getCell(titlesMap("Salary")).toString.toDouble.toInt,
      maritalStatus = MaritalStatusEnum.withName(row.getCell(titlesMap("Marital Status")).toString.toLowerCase), numOfChildren = row.getCell(titlesMap("Number of Children")).toString.toDouble.toInt)
  }


}

