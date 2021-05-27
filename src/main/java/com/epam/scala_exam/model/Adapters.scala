package com.epam.scala_exam.model

import com.epam.scala_exam.model.GenderEnum.GenderEnum
import com.epam.scala_exam.model.MaritalStatusEnum.MaritalStatusEnum

object Adapters {

  implicit class ClientToUserAdapter(val client: Client) extends User {
    override def getAge: Int = client.age
    override def getName:String = client.firstName + " "+client.lastName
    override def getGender:GenderEnum = client.gender
    override def getEmail:String = client.email
    override def getPhoneNumber:String = client.phoneNumber
    override def getMaritalStatus: MaritalStatusEnum = client.maritalStatus
    override def getNumOfChildren: Int = client.numOfChildren
  }
  implicit class PersonToUserAdapter(val person: Person) extends User {
    override def getAge: Int = person.age
    override def getName:String = person.name
    override def getGender:GenderEnum = person.gender
    override def getEmail:String = person.email
    override def getPhoneNumber:String = person.phoneNumber
    override def getMaritalStatus: MaritalStatusEnum = MaritalStatusEnum.unknown
    override def getNumOfChildren: Int = -1
  }
}
