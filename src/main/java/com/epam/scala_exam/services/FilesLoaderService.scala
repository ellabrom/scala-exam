package com.epam.scala_exam.services

import com.epam.scala_exam.model.{ReportRequest, User}


trait FilesLoaderService {
  def readFilesToMemory(filesPath:String): List[Any]
  def convertLoadedDataToObjects(listOfLoadedData:List[Any]): List[User]
  def convertRequestDataToObjects(listOfLoadedData:List[Any]): List[ReportRequest]

}
