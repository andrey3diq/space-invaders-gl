package com.xdev.si.entity

import com.xdev.si.gllisteners.MainRenderer
import com.xdev.engine.sprite.Sprite

/**
 * Created by User: xdev
 * Date: 25.08.2010
 * Time: 0:07:51
 */
object ShotEntity{
  var lastFire: Long = 0
  val firingInterval: Long = 250
  val START_VELOCITY_Y = -350
}
class ShotEntity(sprite : Sprite, listener: MainRenderer, cx: Float, cy: Float) extends AbstractEntity(sprite, cx, cy){
  private var used  = false
  vy = ShotEntity.START_VELOCITY_Y

  override def move(delta: Long){
    super.move(delta)
    if (y < 0) notifyDead()
  }

  def collidedWith(target: AbstractEntity): Unit = {
    if (used) {return}
    // remove the affected entities
    this.notifyDead()
    target.notifyDead()
    // notify the game that the alien has been killed
    listener.notifyAlienKilled()
    used = true;
  }
  
  def doLogic():Unit= {}
  def notifyDead(): Unit = {markedAsDead = true; isDead = true}
  override def toString = "ShotEntity[" + x + ", " + y + "]"
}