package com.xdev.si.gllisteners

import com.xdev.engine.gl.render.GLEventListener2D
import javax.media.opengl.GL
import com.sun.opengl.util.j2d.TextRenderer
import java.awt.Font
import com.xdev.si.Game

/**
 * Created by IntelliJ IDEA.
 * User: Andrey Klochkov
 * Date: 25.11.2010
 * Time: 21:52:23
 * To change this template use File | Settings | File Templates.
 */

object DebugRenderer extends GLEventListener2D{

  var isDebuggerInfoRendered = true
  var textForDebug = "test"
  var textRenderer: TextRenderer = null

  def setDisplayMode(isDisplayed: Boolean){
    isDebuggerInfoRendered = isDisplayed
  }

  def show(){
    setDisplayMode(true)
  }

  def hide(){
    setDisplayMode(false)
  }

  def setTextForDebugging(text: String){
    textForDebug = text
  }

  def onRenderFrame(gl: GL, w: Int, h: Int) = {
    if(isDebuggerInfoRendered){
      textRenderer.beginRendering(w, h)
      textRenderer.setColor(1f, 1f, 1f, 1f)
      textRenderer.draw(textForDebug, 10, Game.WND_HEIGHT - 50)
      textRenderer.endRendering()
    }
  }

  def onInit(gl: GL) = {
    textRenderer = new TextRenderer(new Font("Default", Font.PLAIN, 14))    
  }

}