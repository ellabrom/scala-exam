package com.epam.scala_exam.services.validators

import com.epam.scala_exam.model.User
import org.springframework.stereotype.Component
import com.epam.scala_exam.utils.HelperUtils._
@Component
class EmailValidator extends Validator {
  override def validate(user: User): Boolean = user.getEmail.isValidEmail
}
