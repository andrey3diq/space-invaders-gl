package com.xdev.si.gllisteners

import com.xdev.engine.logging.LogHelper
import javax.media.opengl.GL
import com.sun.opengl.util.j2d.TextRenderer
import java.awt.Font
import com.xdev.engine.gl.render.GLEventListener2D
import com.xdev.si.Game

/**
 * Created by User: xdev
 * Date: 27.08.2010
 * Time: 1:19:36
 */

class HudRenderer extends GLEventListener2D with LogHelper {

  var textRenderer: TextRenderer = null

  def onInit(gl: GL): Unit = {
    textRenderer = new TextRenderer(new Font("Default", Font.PLAIN, 14))
  }

  def onRenderFrame(gl: GL, w: Int, h: Int): Unit = {
    textRenderer.beginRendering(w, h)
    textRenderer.setColor(1f, 1f, 0f, 1f)
    textRenderer.draw("fps count : %d".format(fps), 10, 20)
    textRenderer.setColor(0f, 1f, 0f, 1f)
    textRenderer.draw("level : %d score : %d".format(Game.CURRENT_LEVEL, Game.SCORE), 10, Game.WND_HEIGHT - 25)
    textRenderer.endRendering()
  }
}