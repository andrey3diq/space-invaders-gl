package com.xdev.si.entity

import com.xdev.si.Game
import javax.media.opengl.GL
import com.xdev.si.gllisteners.MainRenderer
import com.xdev.engine.sprite.Sprite

/**
 * Created by User: xdev
 * Date: 25.08.2010
 * Time: 0:06:32
 */
class AlienEntity(sprite: Sprite, listener: MainRenderer, cx: Float, cy: Float) extends AbstractEntity(sprite, cx, cy){

  var currentAnimation = Game.ENEMY_ANIMATION
  var frameDuration = 250
  //Change started velocity
  vx = -50.0f * Game.CURRENT_LEVEL

  /** The time since the last frame change took place */
  var lastFrameChange: Long = 0;
  /** The current frame of animation being displayed */
  var frameNumber = 0;

  override def move(delta: Long){
    val frames = Game.animations(currentAnimation)
    // since the move tells us how much time has passed
    // by we can use it to drive the animation, however
    // its the not the prettiest solution
    lastFrameChange += delta;
    // if we need to change the frame, update the frame number
    // and flip over the sprite in use
    if (lastFrameChange > frameDuration) {
      // reset our frame change time counter
      lastFrameChange = 0;
      // update the frame
      frameNumber+=1;
      if (frameNumber >= frames.length) {
        frameNumber = 0;
        if(currentAnimation == Game.EXPLOSION_ANIMATION) isDead = true
      }
    }

    if ((vx < 0) && (x <= 0)) listener.updateLogic()
    if ((vx > 0) && (x > Game.WND_WIDTH - width)) listener.updateLogic()
    super.move(delta)
  }

  override def draw(gl: GL): Unit = {
    val frames = Game.animations(currentAnimation)
    frames(frameNumber).draw(gl, x, y)
  }

  def doLogic():Unit= {
    // swap over horizontal movement and move down the
    // screen a bit
    if(vx < 0) y -= 10 else y += 30
    vx = -vx
    // if we've reached the bottom of the screen then the player
    // dies
    if (y >= Game.WND_HEIGHT - (height * 3)) {
      listener.notifyDeath()
    }
  }

  def runFaster():Unit = {
    vx = vx * 1.02f
  }

  def collidedWith(target: AbstractEntity): Unit = {}
  
  def notifyDead(): Unit = {
    markedAsDead = true
    if(currentAnimation != Game.EXPLOSION_ANIMATION){
      currentAnimation = Game.EXPLOSION_ANIMATION
      frameNumber = 0
      frameDuration = 30
      lastFrameChange = 0
    }
  }
  override def toString = "AlienEntity[" + x + ", " + y + "]"
}
