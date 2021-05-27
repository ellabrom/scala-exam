package com.epam.scala_exam.model

import com.epam.scala_exam.model.GenderEnum.GenderEnum
import com.epam.scala_exam.model.MaritalStatusEnum.MaritalStatusEnum


trait User {
   def getAge:Int
   def getName:String
   def getGender:GenderEnum
   def getEmail:String
   def getPhoneNumber:String
   def getMaritalStatus: MaritalStatusEnum
   def getNumOfChildren: Int
 }





