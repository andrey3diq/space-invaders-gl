package com.xdev.si

import com.xdev.engine.sprite.Sprite


/**
 * Created by User: xdev
 * Date: 27.08.2010
 * Time: 20:02:08
 */

class TileMap (rows: Int, columns: Int, tiles: Array[Array[Sprite]]) {
  def get(r: Int, c: Int): Sprite = tiles(r)(c)
  def get(r: Int): Array[Sprite] = tiles(r)
  def toLine(): Array[Sprite] = for{line <- tiles; sprite <- line} yield sprite
}