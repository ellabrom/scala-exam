package com.epam.scala_exam.utils

import com.epam.scala_exam.model.Adapters.{ClientToUserAdapter, PersonToUserAdapter}
import com.epam.scala_exam.model.{Client, Person, ReportRequest, User}
import com.epam.scala_exam.services.converters._
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.apache.poi.ss.usermodel.Sheet


object HelperUtils {
  val objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)

  implicit class StringExt(string: String) {
    def isValidEmail: Boolean = string.contains("@") && string.endsWith(".com")

    def isValidPhoneNumber: Boolean = string.matches(".*\\d+.*")

    def convertStringToPersonList: List[Person] = objectMapper.readValue(string, classOf[Array[Person]]).toList

    def convertStringToRequestList: List[ReportRequest] = objectMapper.readValue(string, classOf[Array[ReportRequest]]).toList

  }

  implicit class UserExt(user: User) {
    def printUser: Unit = {
      user match {
        case client: ClientToUserAdapter => println(client.client)
        case person: PersonToUserAdapter => println(person.person)
        case _ => Nil
      }
    }
  }

  implicit class IntExt(intNum: Int) {
    def isPositive: Boolean = intNum > 0
  }

  implicit class SheetExt(sheet: Sheet) {
    def convertSheetToClientList: List[Client] = new ExcelToObjectsDataConverter().convertSheetToObjects(sheet)
  }

  def createReport(reportRequest: ReportRequest, usersList: List[User]): Unit = {
    reportRequest match {
      case ReportRequest(minAge, maxAge, _, "", _, -10000) => val filteredList = usersList.filter(_.getAge > minAge).filter(_.getAge < maxAge)
        filteredList.foreach(_.printUser)
        println(filteredList.size)
      case ReportRequest(minAge, maxAge, gender, "", maritalStatus, numOfChildren) => val filteredList = usersList.filter(_.getAge >= minAge).filter(_.getAge <= maxAge).filter(u => (u.isInstanceOf[PersonToUserAdapter] || u.getGender.compare(gender) == 0 && u.getMaritalStatus.compare(maritalStatus) == 0 && u.getNumOfChildren > numOfChildren && u.isInstanceOf[ClientToUserAdapter]))
        filteredList.foreach(_.printUser)
        println(filteredList.size)
      case ReportRequest(minAge, maxAge, _, prefixName, _, -10000) => val filteredList = usersList.filter(_.getAge > minAge).filter(_.getAge < maxAge).filter(_.getName.startsWith(prefixName))
        filteredList.foreach(_.printUser)
        println(filteredList.size)
      case ReportRequest(minAge, maxAge, gender, prefixName, maritalStatus, numOfChildren) => val filteredList = usersList.filter(_.getAge >= minAge).filter(_.getAge <= maxAge).filter(_.getName.startsWith(prefixName)).filter(u => (u.isInstanceOf[PersonToUserAdapter] || u.getGender.compare(gender) == 0 && u.getMaritalStatus.compare(maritalStatus) == 0 && u.getNumOfChildren > numOfChildren && u.isInstanceOf[ClientToUserAdapter]))
        filteredList.foreach(_.printUser)
        println(filteredList.size)

      case _ => usersList
        usersList.foreach(_.printUser)
        println(usersList.size)
    }

  }

}
