package com.xdev.engine.logging

import org.apache.log4j.Logger

/**
 * Created by User: xdev
 * Date: 26.08.2010
 * Time: 13:38:28
 */

trait LogHelper {
    val loggerName = this.getClass.getName
    lazy val logger = Logger.getLogger(loggerName)

    def debug(message: String): Unit ={
      if(logger.isDebugEnabled)logger.debug(message)
    }
}
