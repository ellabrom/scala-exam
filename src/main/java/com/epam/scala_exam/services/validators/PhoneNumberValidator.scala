package com.epam.scala_exam.services.validators

import com.epam.scala_exam.model._
import org.springframework.stereotype.Component
import com.epam.scala_exam.utils.HelperUtils._

@Component
class PhoneNumberValidator extends Validator{
  override def validate(user: User): Boolean = user.getPhoneNumber.isValidPhoneNumber
}
