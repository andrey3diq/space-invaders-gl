package com.xdev.si.gllisteners

import java.awt.event.KeyEvent
import javax.media.opengl.GL
import collection.mutable.ArrayBuffer
import com.xdev.si.entity.{ShotEntity, AlienEntity, AbstractEntity, ShipEntity}
import com.xdev.engine.logging.LogHelper
import com.xdev.engine.input.Keyboard
import com.xdev.si.{Game, ResourceFactory}
import com.xdev.engine.gl.render.GLEventListener2D

/**
 * Created by User: xdev
 * Date: 26.08.2010
 * Time: 22:57:23
 */

class MainRenderer extends GLEventListener2D with LogHelper{

  private val SCORE_LOSE_PENALTY = 1000

  private var ship: AbstractEntity = null
  private val moveSpeed: Float = 250.0f

  private var maxEnemyCount: Int = 60
  /** The message to display which waiting for a key press */
  private var message: String = Game.PRESS_ANY_KEY_SPRITE
  /** True if the fire key has been released */
  private var fireHasBeenReleased = false;
  /** True if we're holding up game play until a key has been pressed */
  private var waitingForKeyPress = true
  private var logicRequiredThisLoop = false

  private val aliens = new ArrayBuffer[AlienEntity]()
  private val shots = new ArrayBuffer[ShotEntity]()

  //==========================================================
  def onInit(gl: GL): Unit = {
    debug("Initialize")
    ship = new ShipEntity(ResourceFactory.getSprite(Game.SHIP_SPRITE), 370, 550)
    newGame()
  }
  //==========================================================
  def onRenderFrame(gl: GL, w: Int, h: Int): Unit = {

  processKeyboard()
  if (!waitingForKeyPress) {
    if (logicRequiredThisLoop) {
      logicRequiredThisLoop = false
      aliens.foreach(_.doLogic())
    }
    aliens.foreach(_.move(delta))
    shots.foreach(_.move(delta))
    ship.move(delta)
  } else {
    ResourceFactory.getSprite(message).draw(gl, 325,250)
    return
  }
  if (aliens.length == 0)notifyWin()

  // Collision detection shot with enemies
  checkCollisions()
  //Remove dead entites
  aliens--=aliens.filter(e => e.isDead)
  shots--=shots.filter(e => e.isDead)
  //Draw entities
  aliens.foreach(_.draw(gl))
  shots.foreach(_.draw(gl))
  ship.draw(gl)
 }

 //==========================================================
  private def checkCollisions(): Unit = {
    for(shot <- shots if !shot.isDead; if !shot.markedAsDead){
      for(enemy <- aliens if !enemy.isDead; if !enemy.markedAsDead; if shot.collidesWith(enemy)){
        shot.collidedWith(enemy)
        return
      }
    }
  }

  private def newGame(): Unit = {
    debug("New Game")
    if(aliens.length == maxEnemyCount)return
    aliens.clear()
		initEntities()
    waitingForKeyPress = false    
  }

  private def initEntities(): Unit = {
    for(row <- 0 until 5) {
      for(x <- 0 until 12) {
        aliens += new AlienEntity(ResourceFactory.getSprite(Game.ALIEN_SPRITE_0), this, 100+(x*50),(50)+row*30)
      }
    }
  }

  def updateLogic():Unit= logicRequiredThisLoop = true;

  def notifyWin(): Unit = {
    shots.clear()
    message = Game.WIN_SPRITE
    waitingForKeyPress = true
    Game.CURRENT_LEVEL += 1
  }

  def notifyDeath():Unit={
    message = Game.GAME_OVER_SPRITE
    waitingForKeyPress = true
    if(Game.SCORE >= SCORE_LOSE_PENALTY ){
      Game.SCORE -= SCORE_LOSE_PENALTY
    }else Game.SCORE = 0 
  }
  /**
     * Notification that an alien has been killed
     */
  def notifyAlienKilled(): Unit = {
    aliens.foreach(_.runFaster())
    Game.SCORE += 100
  }
  /**
     * Attempt to fire a shot from the player. Its called "try"
     * since we must first check that the player can fire at this
     * point, i.e. has he/she waited long enough between shots
     */
  def tryToFire() {
      // check that we have waiting long enough to fire
      if (System.currentTimeMillis() - ShotEntity.lastFire < ShotEntity.firingInterval) {
        return
      }
      // if we waited long enough, create the shot entity, and record the time.
      ShotEntity.lastFire = System.currentTimeMillis()
      shots += new ShotEntity(ResourceFactory.getSprite(Game.SHOT_SPRITE), this, ship.x + 10, ship.y - 30)
    }

  // TODO: Move processKeyboard method to right place. It is hard to understand why Keyboard processor placed in renderer.
  private def processKeyboard(): Unit = {
    //Process keyboard
    val leftPressed = Keyboard.isPressed(KeyEvent.VK_LEFT)
    val rightPressed = Keyboard.isPressed(KeyEvent.VK_RIGHT)
    val firePressed = Keyboard.isPressed(KeyEvent.VK_SPACE)

    DebugRenderer.setTextForDebugging("left: " + leftPressed + "right: " + rightPressed + "fire: " + firePressed)
    
    ship.stop();
    if(leftPressed){
      ship.accelerate(-moveSpeed, 0)
    }
    if(rightPressed){
      ship.accelerate(moveSpeed, 0)
    }
    if(firePressed){
      if(waitingForKeyPress){
        newGame()
      }else{
        tryToFire();
      }
    }

//    ship.vx = 0.0f;
//    if (!waitingForKeyPress) {
//      if (leftPressed && !rightPressed) {
//         ship.vx = -moveSpeed
//      } else if (rightPressed && !leftPressed) {
//         ship.vx = moveSpeed
//      }
//      if (firePressed) tryToFire()
//    }else{
//      if(!firePressed)fireHasBeenReleased = true
//      if ((firePressed) && (fireHasBeenReleased)) {
//        waitingForKeyPress = false
//        fireHasBeenReleased = false
//        newGame()
//      }
//    }
  }
}
