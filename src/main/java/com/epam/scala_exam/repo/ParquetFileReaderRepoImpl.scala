package com.epam.scala_exam.repo

import org.apache.poi.ss.usermodel.{Sheet, WorkbookFactory}
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import java.io.File
import scala.collection.mutable.ListBuffer


@Component
class ParquetFileReaderRepoImpl(
                                @Value("${parquet_file_ext}")  val fileExt: String
                             ) extends FileReaderRepo {
  override def readFilesToMemory(inputFilesPath:String): List[Sheet] = {

    val parquetFiles: List[File] = findFilesInInputPath(fileExt,inputFilesPath)
    val listOfReadedPArquetFiles = new ListBuffer[Sheet]
    val itr = parquetFiles.iterator
    while (itr.hasNext) {
      listOfReadedPArquetFiles += readExcelFileToMemory(itr.next())
    }
    listOfReadedPArquetFiles.toList
  }
  private def readExcelFileToMemory(file: File): Sheet = WorkbookFactory.create(file).getSheetAt(0)

}
