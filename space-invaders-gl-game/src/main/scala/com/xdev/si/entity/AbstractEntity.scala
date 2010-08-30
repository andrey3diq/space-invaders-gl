package com.xdev.si.entity

import java.awt.Rectangle
import javax.media.opengl.GL
import com.xdev.engine.sprite.Sprite

/**
 * Created by User: xdev
 * Date: 24.08.2010
 * Time: 21:56:53
 */
abstract class AbstractEntity(sprite : Sprite, cx: Float, cy: Float) {
  //Coordinates
  var x: Float = cx
  var y: Float = cy
  val width = sprite.getWidth()
  val height = sprite.getHeight()
  //Velocity
  var vx: Float = 0.0f
  var vy: Float = 0.0f
  //Bounding boxes
  private val thisBoundBox : Rectangle  = new Rectangle(cx.asInstanceOf[Int], cy.asInstanceOf[Int], width, height)
  private val targetBoundBox : Rectangle  = new Rectangle()
  //State
  var markedAsDead = false
  var isDead = false
  /* =============================================
     Methods
    =============================================*/

  /**
   * Move  entity
   */
  def move(delta: Long){
    x = x + (vx * delta) / 1000.0f
    y = y + (vy * delta) / 1000.0f
    thisBoundBox.x = x.asInstanceOf[Int]
    thisBoundBox.y = y.asInstanceOf[Int]
  }

  def draw(gl: GL) = sprite.draw(gl, x, y)
  
  def collidesWith(target : AbstractEntity): Boolean ={
    thisBoundBox.setBounds(x.asInstanceOf[Int],
      y.asInstanceOf[Int],
      width,
      height)
    targetBoundBox.setBounds(target.x.asInstanceOf[Int],
      target.y.asInstanceOf[Int],
      target.width,
      target.height)
    return thisBoundBox.intersects(targetBoundBox)
  }

  def collidedWith(target: AbstractEntity): Unit
  def doLogic():Unit
  def notifyDead(): Unit
}
