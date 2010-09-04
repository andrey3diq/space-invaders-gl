package com.xdev.si.gllisteners

import com.xdev.engine.logging.LogHelper
import javax.media.opengl.GL
import com.xdev.si.{Game, ResourceFactory}
import com.xdev.engine.gl.render.GLEventListener2D
import com.xdev.engine.sprite.Sprite

/**
 * Created by User: xdev
 * Date: 27.08.2010
 * Time: 1:50:10
 */

class BackgroundRenderer extends GLEventListener2D with LogHelper {
  var background: Sprite = null

  def onInit(gl: GL): Unit = {
    background = ResourceFactory.getSprite(Game.BACKGROUND_SPRITE)
  }

  def onRenderFrame(gl: GL, w: Int, h: Int): Unit = {
     background.draw(gl, 0.0f, 0.0f)
  }
}