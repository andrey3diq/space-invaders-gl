package com.xdev.engine.util

import java.net.URL
import java.io.{FileInputStream, InputStream, IOException}
import com.xdev.engine.logging.LogHelper

/**
 * Created by User: xdev
 * Date: 26.08.2010
 * Time: 11:00:35
 */

object ResourceRetriever extends LogHelper{

  @throws(classOf[IOException])
  def getResource(filename: String) : URL = {
    debug("getResource : " + filename)
    val url: URL = getClass.getResource(filename)
    if(url == null)return new URL("file", "localhost", filename) else return url
  }

  @throws(classOf[IOException])
  def getResourceAsStream(filename: String) : InputStream = {
    debug("getResourceAsStream : " + filename)
    val convertedFileName = filename.replace('\\', '/')
    val stream = getClass.getResourceAsStream(convertedFileName)
    // If not found in jar, then load from disk
    if (stream == null) return new FileInputStream(convertedFileName) else stream
  }

}
