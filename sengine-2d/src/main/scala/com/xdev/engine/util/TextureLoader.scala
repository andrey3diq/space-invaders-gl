package com.xdev.engine.util

import javax.media.opengl.GL
import collection.mutable.HashMap
import javax.imageio.ImageIO
import com.sun.opengl.util.texture.{Texture, TextureIO}
import com.xdev.engine.logging.LogHelper

/**
 * Created by User: xdev
 * Date: 26.08.2010
 * Time: 2:16:50
 */

object TextureLoader extends LogHelper{
  /** The table of textures that have been loaded in this loader */
      private val table = new HashMap[String, Texture]()

      def getTexture(resourceName: String): Texture ={
          if (table.contains(resourceName)){
            return table(resourceName)
          }
          debug("Load texture " + resourceName)
          val texture: Texture = TextureIO.newTexture (ImageIO.read(ResourceRetriever.getResourceAsStream(resourceName)), false);

          texture.setTexParameteri (GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
          // Use the NEAREST minification function when the pixel being
          // textured maps to an area greater than one texel.
          texture.setTexParameteri (GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
          debug("Texture loaded ")
          table.put(resourceName, texture);
          return texture;
      }
}
