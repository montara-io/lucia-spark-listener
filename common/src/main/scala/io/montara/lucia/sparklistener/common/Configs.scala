package io.montara.lucia.sparklistener.common

import org.apache.spark.SparkConf

import scala.concurrent.duration._

object Configs {

  def isEdge(sparkConf: SparkConf): Boolean = {
    sparkConf.getBoolean("spark.lucia.sparklistener.edge", false)
  }

  def getDMAppId(sparkConf: SparkConf): String = {
    val configName = "spark.lucia.sparklistener.dmAppId"
    sparkConf
      .getOption(configName)
      .getOrElse {
        val generatedDMAppId = generateDMAppId(sparkConf)
        sparkConf.set(configName, generatedDMAppId)
        generatedDMAppId
      }
  }

  def getPipelineId(sparkConf: SparkConf): String = {
    sparkConf.get(
      "spark.lucia.sparklistener.pipelineId",
      null
    )
  }

  def getPipelineRunId(sparkConf: SparkConf): String = {
    sparkConf.get(
      "spark.lucia.sparklistener.pipelineRunId",
      null
    )
  }

  def getJobId(sparkConf: SparkConf): String = {
    val configName = "spark.lucia.sparklistener.jobId"
    sparkConf
      .getOption(configName)
      .getOrElse {
        val generatedJobId = generateJobId(sparkConf)
        sparkConf.set(configName, generatedJobId)
        generatedJobId
      }
  }

  def luciaSparkListenerUrl(sparkConf: SparkConf): String = {
    sparkConf.get(
      "spark.lucia.sparklistener.url",
      null
    )
  }

  def bufferMaxSize(sparkConf: SparkConf): Int = {
    sparkConf.getInt("spark.lucia.sparklistener.buffer.maxNumEvents", 1000)
  }

  def payloadMaxSize(sparkConf: SparkConf): Int = {
    sparkConf.getInt("spark.lucia.sparklistener.payload.maxNumEvents", 10000)
  }

  def heartbeatInterval(sparkConf: SparkConf): FiniteDuration = {
    sparkConf
      .getDouble("spark.lucia.sparklistener.heartbeatIntervalSecs", 10)
      .seconds
  }

  def pollingInterval(sparkConf: SparkConf): FiniteDuration = {
    sparkConf
      .getDouble("spark.lucia.sparklistener.pollingIntervalSecs", 0.5)
      .seconds
  }

  def maxPollingInterval(sparkConf: SparkConf): FiniteDuration = {
    sparkConf
      .getDouble("spark.lucia.sparklistener.maxPollingIntervalSecs", 60)
      .seconds
  }

  def maxWaitOnEnd(sparkConf: SparkConf): FiniteDuration = {
    sparkConf
      .getDouble("spark.lucia.sparklistener.maxWaitOnEndSecs", 10)
      .seconds
  }

  def waitForPendingPayloadsSleepInterval(
      sparkConf: SparkConf
  ): FiniteDuration = {
    sparkConf
      .getDouble(
        "spark.lucia.sparklistener.waitForPendingPayloadsSleepIntervalSecs",
        1
      )
      .seconds
  }

  def logDuration(sparkConf: SparkConf): Boolean = {
    sparkConf.getBoolean("spark.lucia.sparklistener.logDuration", false)
  }

  private def generateJobId(sparkConf: SparkConf): String = {
    val appName = sparkConf
      .getOption("spark.lucia.sparklistener.appNameOverride")
      .orElse(
        sparkConf.getOption("spark.databricks.clusterUsageTags.clusterName")
      )
      .orElse(sparkConf.getOption("spark.app.name"))
      .getOrElse("undefined")
    val sanitizedAppName =
      appName.replaceAll("\\W", "-").replaceAll("--*", "-").stripSuffix("-")
    s"$sanitizedAppName"
  }

  private def generateDMAppId(sparkConf: SparkConf): String = {
    val appName = sparkConf
      .getOption("spark.lucia.sparklistener.appNameOverride")
      .orElse(
        sparkConf.getOption("spark.databricks.clusterUsageTags.clusterName")
      )
      .orElse(sparkConf.getOption("spark.app.name"))
      .getOrElse("undefined")
    val sanitizedAppName =
      appName.replaceAll("\\W", "-").replaceAll("--*", "-").stripSuffix("-")
    val uuid = java.util.UUID.randomUUID().toString
    s"$sanitizedAppName-$uuid"
  }

  val luciaSparkListenerVersion: String = "1.0.3"

}
