package com.xdev.engine.input

import java.awt.event.{KeyEvent, AWTEventListener, KeyAdapter}
import java.awt.{AWTEvent, Component, Toolkit}

/**
 * Created by User: xdev
 * Date: 25.08.2010
 * Time: 22:44:33
 */

object Keyboard{
  /** The status of the keys on the keyboard */
    val keys: Array[Boolean] = Array.fill(1024){false}

    /**
     * Initialise the central keyboard handler
     */
    def init(): Unit = Toolkit.getDefaultToolkit().addAWTEventListener(KeyHandler, AWTEvent.KEY_EVENT_MASK)

    /**
     * Initialise the central keyboard handler
     *
     * @param c The component that we will listen to
     */
    def init(c : Component) {
      c.addKeyListener(KeyHandler);
    }

    /**
     * Check if a specified key is pressed
     *
     * @param key The code of the key to check (defined in KeyEvent)
     * @return True if the key is pressed
     */
    def isPressed(key: Int) : Boolean ={
        return keys(key)
    }

    /**
     * Set the status of the key
     *
     * @param key The code of the key to set
     * @param pressed The new status of the key
     */
    def setPressed(key: Int, pressed: Boolean): Unit ={
        keys(key) = pressed
    }

    /**
     * A class to respond to key presses on a global scale.
     *
     * @author Kevin Glass
     */
    private object KeyHandler extends KeyAdapter with AWTEventListener {
        /**
         * Notification of a key press
         *
         * @param e The event details
         */
        override def keyPressed(e: KeyEvent) {
          if (e.isConsumed()) {
            return;
          }
            keys(e.getKeyCode()) = true;
        }

        /**
         * Notification of a key release
         *
         * @param e The event details
         */
        override def keyReleased( e: KeyEvent) {
          if (e.isConsumed()) {
            return;
          }

          // AK: I don't understand what is checking here, but i have "lags" with ship moving when next conditions are uncommented  
//          val nextPress = Toolkit.getDefaultToolkit().getSystemEventQueue().peekEvent(KeyEvent.KEY_PRESSED).asInstanceOf[KeyEvent]
//          if ((nextPress == null) || (nextPress.getWhen() != e.getWhen())) {
            keys(e.getKeyCode()) = false;
//          }
        }

        /**
         * Notification that an event has occured in the AWT event
         * system
         *
         * @param e The event details
         */
        override def eventDispatched(e: AWTEvent) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                keyPressed(e.asInstanceOf[KeyEvent])
            }
            if (e.getID() == KeyEvent.KEY_RELEASED) {
                keyReleased(e.asInstanceOf[KeyEvent])
            }
        }

    }

}
