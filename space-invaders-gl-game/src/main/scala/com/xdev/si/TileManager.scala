package com.xdev.si

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import com.xdev.engine.util.ResourceRetriever
import com.xdev.engine.logging.LogHelper
import com.sun.opengl.util.texture.TextureIO
import com.xdev.engine.sprite.Sprite

/**
 * Created by User: xdev
 * Date: 27.08.2010
 * Time: 19:58:52
 */

object TileManager extends LogHelper {

  def load(filename: String, tileW: Int, tileH: Int): TileMap = {
    debug("Load tile map : " + filename)
    val sourceImage : BufferedImage = ImageIO.read(ResourceRetriever.getResourceAsStream(filename))
    val size = (sourceImage.getWidth(), sourceImage.getHeight())
    debug("Image size (width, height) : " + size)

    val rows = size._1 / tileH
    val columns = size._2 / tileW

    debug("TileMap greed size (rows, columns) : (" + rows + ", " + columns + ")")

    val tiles = Array.ofDim[Sprite](rows, columns)

    for(r <- 0 until rows){
       for(c <- 0 until columns){
         val x = c * tileW
         val y = r * tileH
         tiles(r)(c) = new Sprite(TextureIO.newTexture(sourceImage.getSubimage(x, y, tileW, tileH), false))
       }
    }
    return new TileMap(rows, columns, tiles)
  }
}