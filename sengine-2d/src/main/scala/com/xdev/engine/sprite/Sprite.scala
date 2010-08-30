package com.xdev.engine.sprite

import javax.media.opengl.GL
import com.sun.opengl.util.texture.{Texture, TextureCoords}

/**
 * Created by User: xdev
 * Date: 24.08.2010
 * Time: 22:02:57
 */

class Sprite(texture: Texture){
  val tc: TextureCoords = texture.getImageTexCoords()

  def getWidth(): Int = texture.getImageWidth()
  def getHeight(): Int = texture.getImageHeight()

  def draw(gl : GL, x: Float, y: Float) = {

    // Enable two-dimensional texturing.
    texture.enable()
    // Bind this texture to the current rendering context.
    texture.bind()
    // store the current model matrix
    gl.glPushMatrix()
    // bind to the appropriate texture for this sprite
    // translate to the right location and prepare to draw
    gl.glTranslatef(x, y, 0)
    gl.glColor3f(1, 1, 1)
    // draw a quad textured to match the sprite
    gl.glBegin(GL.GL_QUADS)
      gl.glTexCoord2f (tc.left(), tc.top())
      gl.glVertex2f(0, 0)

      gl.glTexCoord2f (tc.right(), tc.top())
      gl.glVertex2f(getWidth(), 0)

      gl.glTexCoord2f (tc.right(), tc.bottom())
      gl.glVertex2f(getWidth(),getHeight())

      gl.glTexCoord2f (tc.left(), tc.bottom())
      gl.glVertex2f(0, getHeight())

    gl.glEnd()
    // restore the model view matrix to prevent contamination
    gl.glPopMatrix()

    texture.disable()
  }
}