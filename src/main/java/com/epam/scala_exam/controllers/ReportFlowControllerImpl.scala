package com.epam.scala_exam.controllers

import com.epam.scala_exam.services.FilesLoaderService
import com.epam.scala_exam.utils.HelperUtils._
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
class ReportFlowControllerImpl(filesLoaderService: FilesLoaderService, @Value("${input_files_path}") val inputFilesPath: String, @Value("${request_files_path}") val requests_file_path: String) extends ReportFlowController {
  override def controlFlow(): Unit = {
    val inputFilesList = filesLoaderService.readFilesToMemory(inputFilesPath)
    val requestFilesList = filesLoaderService.readFilesToMemory(requests_file_path)
    val usersList = filesLoaderService.convertLoadedDataToObjects(inputFilesList)
    val reportRequestsFilesList = filesLoaderService.convertRequestDataToObjects(requestFilesList)
    createReport(reportRequestsFilesList.head, usersList)
  }
}
