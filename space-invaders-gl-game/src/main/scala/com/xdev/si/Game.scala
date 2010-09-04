package com.xdev.si

import gllisteners.{BackgroundRenderer, HudRenderer, MainRenderer}
import javax.media.opengl.GL
import com.xdev.engine.gl.GLGameWindow
import com.xdev.engine.gl.render.GLEventListener2D
import com.xdev.engine.sprite.Sprite
import collection.mutable.HashMap

object Game extends GLGameWindow("Space Invaders GL - Scala version 1.0", 800, 600, 75){

  val WND_WIDTH = 800
  val WND_HEIGHT = 600

  val PRESS_ANY_KEY_SPRITE = "/sprites/pressanykey.gif"
  val WIN_SPRITE = "/sprites/youwin.gif"
  val GAME_OVER_SPRITE = "/sprites/gotyou.gif"
  val SHIP_SPRITE = "/sprites/ship.gif"
  val SHOT_SPRITE = "/sprites/shot.gif"
  val ALIEN_SPRITE_0 = "/sprites/alien.gif"
  val ALIEN_SPRITE_1 = "/sprites/alien2.gif"
  val BACKGROUND_SPRITE = "/sprites/space.png"

  val EXPL_TILE_MAP = "/sprites/exp1.png"
  var explTileMap:TileMap = null

  val ENEMY_ANIMATION = 0
  val EXPLOSION_ANIMATION = 1
  val animations = new HashMap[Int, Array[Sprite]]()

  def initGameRenders(): Array[GLEventListener2D] = {
    debug("Init game renders")
    Array[GLEventListener2D](new BackgroundRenderer(), new MainRenderer(), new HudRenderer())
  }

  def initGameResources(gl: GL): Unit = {
    debug("Init game resources")
    ResourceFactory.getSprite(PRESS_ANY_KEY_SPRITE)
    ResourceFactory.getSprite(WIN_SPRITE)
    ResourceFactory.getSprite(GAME_OVER_SPRITE)
    ResourceFactory.getSprite(SHIP_SPRITE)
    ResourceFactory.getSprite(SHOT_SPRITE)
    ResourceFactory.getSprite(ALIEN_SPRITE_0)
    ResourceFactory.getSprite(ALIEN_SPRITE_1)
    ResourceFactory.getSprite(BACKGROUND_SPRITE)

    explTileMap = TileManager.load(EXPL_TILE_MAP, 42, 42)
    animations.put(ENEMY_ANIMATION, Array[Sprite](
      ResourceFactory.getSprite(Game.ALIEN_SPRITE_0),
      ResourceFactory.getSprite(Game.ALIEN_SPRITE_1),
      ResourceFactory.getSprite(Game.ALIEN_SPRITE_0),
      ResourceFactory.getSprite(Game.ALIEN_SPRITE_1)
      ))
    animations.put(EXPLOSION_ANIMATION, explTileMap.toLine)

  }
  
  //Game entry point
  def main(args: Array[String]): Unit = {
    Game.showWindow()
  }
}
