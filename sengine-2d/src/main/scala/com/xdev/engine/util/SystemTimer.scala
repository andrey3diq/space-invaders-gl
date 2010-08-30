package com.xdev.engine.util

import com.dnsalias.java.timer.AdvancedTimer
/**
 * Created by User: xdev
 * Date: 25.08.2010
 * Time: 16:01:21
 */

object SystemTimer {
  /** Our link into the GAGE timer library */
  val timer : AdvancedTimer  = new AdvancedTimer()
  /** The number of "timer ticks" per second */
  //Run timer on class loading
  timer.start()
  val timerTicksPerSecond: Long = AdvancedTimer.getTicksPerSecond()

  /**
   * Get the high resolution time in milliseconds
   *
   * @return The high resolution time in milliseconds
   */
  def getTime(): Long = {
    // we get the "timer ticks" from the high resolution timer
    // multiply by 1000 so our end result is in milliseconds
    // then divide by the number of ticks in a second giving
    // us a nice clear time in milliseconds
    return (timer.getClockTicks() * 1000) / timerTicksPerSecond
  }

  /**
   * Sleep for a fixed number of milliseconds.
   *
   * @param duration The amount of time in milliseconds to sleep for
   */
  def sleep(duration: Long):Unit = {
    timer.sleep((duration * timerTicksPerSecond) / 1000)
  }
}
