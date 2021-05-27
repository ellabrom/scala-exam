package com.epam.scala_exam.model

import com.fasterxml.jackson.core.`type`.TypeReference

class MaritalStatusEnumType extends TypeReference[MaritalStatusEnum.type]
object MaritalStatusEnum extends Enumeration {
  type MaritalStatusEnum = Value
  val married: Value = Value("married")
  val single: Value = Value("single")
  val unknown: Value = Value("unknown")

}
