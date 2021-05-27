package com.epam.scala_exam.services

import com.epam.scala_exam.model.Adapters._
import com.epam.scala_exam.model.{ReportRequest, User}
import com.epam.scala_exam.repo.FileReaderRepo
import com.epam.scala_exam.services.validators.ValidatorAggregator
import com.epam.scala_exam.utils.HelperUtils._
import org.apache.poi.ss.usermodel.Sheet
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import java.util
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._

@Service
class FilesLoaderServiceImpl(fileReaderRepos: java.util.List[FileReaderRepo], validatorAggregator: ValidatorAggregator
                            ) extends  FilesLoaderService {

  override def readFilesToMemory(filesPath:String): List[Any] = {
    val listOfReadedFiles = new ListBuffer[List[Any]]
    fileReaderRepos.asScala.toList.foreach(listOfReadedFiles += _.readFilesToMemory(filesPath))
    listOfReadedFiles.toList.flatten
  }


  override def convertLoadedDataToObjects(listOfLoadedData:List[Any]): List[User] = {
    val listOfLoadedObjects = new ListBuffer[List[User]]
    listOfLoadedData.foreach(data=>listOfLoadedObjects+=convertDataBasedToType(data))
    listOfLoadedObjects.toList.flatten
  }

   override def convertRequestDataToObjects(listOfLoadedData:List[Any]): List[ReportRequest] = {
    val listOfLoadedObjects = new ListBuffer[List[ReportRequest]]
    listOfLoadedData.foreach(data=>listOfLoadedObjects+=convertDataForRequestFiles(data))
    listOfLoadedObjects.toList.flatten
  }

  private def convertDataBasedToType(data:Any): List[User] =  {
    val usersList:util.List[User] = new util.ArrayList[User]()

    data match {
      case str:String =>str.convertStringToPersonList.filter(user => validatorAggregator.validateAllData(user) ).foreach(usersList.add(_));usersList.asScala.toList
      case sheet:Sheet => sheet.convertSheetToClientList.filter(user => validatorAggregator.validateAllData(user) ).foreach(usersList.add(_));usersList.asScala.toList
      case _ =>Nil
    }
    usersList.asScala.toList
  }

  private def convertDataForRequestFiles(data:Any): List[ReportRequest] =  {
    var requestsList :List[ReportRequest] =Nil
    data match {
      case str:String =>requestsList = str.convertStringToRequestList
      case _ =>Nil
    }
    requestsList
  }
}
