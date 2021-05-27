package com.epam.scala_exam.model

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration
import org.springframework.stereotype.Component


@JsonCreator
case class ReportRequest (@JsonProperty("min_age")minAge:Int=0,@JsonProperty("max_age")maxAge:Int=1000,
                          @JsonProperty("gender") @JsonScalaEnumeration(classOf[GenderEnumType]) gender: GenderEnum.GenderEnum,
                          @JsonProperty("prefix_name")prefixName: String = "",
                          @JsonProperty("Marital Status") @JsonScalaEnumeration(classOf[MaritalStatusEnumType]) maritalStatus: MaritalStatusEnum.MaritalStatusEnum,
                          @JsonProperty("Number of Children")numOfChildren:Int = -10000) extends Serializable

