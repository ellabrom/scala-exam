package com.epam.scala_exam.model

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration

@JsonCreator
case class Person(@JsonProperty("age") age: Int, @JsonProperty("name") name: String,
                  @JsonProperty("gender") @JsonScalaEnumeration(classOf[GenderEnumType]) gender: GenderEnum.GenderEnum, @JsonProperty("company") company: String,
                  @JsonProperty("email") email: String, @JsonProperty("phone") phoneNumber: String,
                  @JsonProperty("address") address: String) extends Serializable {

}
