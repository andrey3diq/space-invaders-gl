package com.xdev.si.entity

import com.xdev.si.Game
import com.xdev.engine.sprite.Sprite

/**
 * Created by User: xdev
 * Date: 24.08.2010
 * Time: 23:55:17
 */
case class ShipEntity(sprite : Sprite, cx: Float, cy: Float) extends AbstractEntity(sprite, cx, cy){

  def collidedWith(target: AbstractEntity): Unit = {}

  override def move(delta: Long){
     if ((vx < 0) && (x <= 0)) return
     if ((vx > 0) && (x > Game.WND_WIDTH - width)) return
     super.move(delta)
  }

  def doLogic():Unit= {}
  def notifyDead(): Unit = {}
}
