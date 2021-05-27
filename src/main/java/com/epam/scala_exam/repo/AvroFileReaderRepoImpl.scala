package com.epam.scala_exam.repo

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import java.io.File
import scala.collection.mutable.ListBuffer
import scala.io.Source


@Component
class AvroFileReaderRepoImpl(@Value("${avro_file_ext}")  val fileExt: String
                             ) extends FileReaderRepo {
  override def readFilesToMemory(inputFilesPath:String): List[String] = {
    val auroFiles: List[File] = findFilesInInputPath(fileExt,inputFilesPath)
    val listOfLoadedFiles = new ListBuffer[String]()
    val itr = auroFiles.iterator
    while (itr.hasNext) {
      listOfLoadedFiles += readAuroFileToMemory(itr.next())
    }
    listOfLoadedFiles.toList
  }

  private def readAuroFileToMemory(file: File): String = {
    val testTxtSource =  Source.fromFile(file)
    val jsonString =  testTxtSource.getLines.mkString
    testTxtSource.close()
    jsonString

  }
}
