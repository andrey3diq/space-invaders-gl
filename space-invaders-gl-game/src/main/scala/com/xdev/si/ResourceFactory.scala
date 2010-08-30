package com.xdev.si

import com.xdev.engine.util.TextureLoader
import collection.mutable.HashMap
import com.xdev.engine.sprite.Sprite

/**
 * Created by User: xdev
 * Date: 26.08.2010
 * Time: 3:06:45
 */

object ResourceFactory {

  val spritesContainer = new HashMap[String, Sprite]()

  def getSprite(path: String): Sprite = {
    if(spritesContainer.contains(path)) {return spritesContainer(path)}
    val sprite = new Sprite(TextureLoader.getTexture(path))
    spritesContainer.put(path, sprite)
    return sprite
  }
}