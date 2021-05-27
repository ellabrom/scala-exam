package com.epam.scala_exam.model

import com.epam.scala_exam.model.GenderEnum.GenderEnum
import com.epam.scala_exam.model.MaritalStatusEnum.MaritalStatusEnum


case class Client(firstName: String, lastName: String, gender: GenderEnum, age: Int, email: String, phoneNumber: String,
                  education: String, occupation: String, salary: Int, maritalStatus: MaritalStatusEnum, numOfChildren: Int)





