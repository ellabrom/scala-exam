package com.epam.scala_exam.services.validators

import com.epam.scala_exam.model._

trait Validator {
  def validate(user:User): Boolean
}
