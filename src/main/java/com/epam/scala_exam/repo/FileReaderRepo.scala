package com.epam.scala_exam.repo

import java.io.File


trait FileReaderRepo {
  def findFilesInInputPath( fileExtention:String, inputFilesPath:String):List[File] = {
    val d = new File(inputFilesPath)
       if (d.exists && d.isDirectory) {
              val filesList = d.listFiles
               filesList.filter(_.isFile).filter(_.getName.endsWith(fileExtention)).toList
        }
       else
         Nil
  }
  def readFilesToMemory(inputFilesPath:String):List[Any]

}
