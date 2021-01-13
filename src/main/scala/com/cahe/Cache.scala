package com.cahe

import collection.mutable.Map


trait Cache {
  /**
   * @return previous value if key was present, if it is a new key then None
   * */
  def put(key: Int, `value`: Int): Unit

  def get(key: Int): Option[CacheEntry]

}

class LruCache(maxSize: Int) extends Cache {
  private val map: Map[Int, CacheEntry] = Map.empty
  /**
   * @param timeStamp when last was get or put
   * */

  /**
   * @return previous value if key was present, if it is a new key then None
   * */
  override def put(key: Int, `value`: Int): Unit = {
    if (map.size >= maxSize) {
      val leastUsed = map.minBy(_._2.timeStamp)
      map.remove(leastUsed._1)
    }
    map.+=(key -> CacheEntry(System.currentTimeMillis(), `value`))
  }

  override def get(key: Int): Option[CacheEntry] = {
    for {
      v <- map.get(key)
      updated = v.copy(timeStamp = System.currentTimeMillis())
      _ <- map.put(key, updated)
    } yield updated
  }
}
