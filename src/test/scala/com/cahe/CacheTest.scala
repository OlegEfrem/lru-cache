package com.cahe

import org.scalatest.OptionValues
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class CacheTest extends AnyFreeSpec with Matchers with OptionValues {

  private val cache: Cache = new LruCache(2)

  "put" in {
    val key = 1
    val v = 2
    cache.put(key, v)
    cache.get(key).map(_.`value`) shouldBe Some(v)
  }

  "get missing value" in {
    cache.get(3) shouldBe None
  }

  "put and get" in {
    /**
     * var lRUCache = new Cache(2)
     * lRUCache.put(1, 1) // cache is {1=1}
     * lRUCache.put(2, 2) // cache is {1=1, 2=2}
     * lRUCache.get(1)    // return 1
     * lRUCache.put(3, 3) // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
     * lRUCache.get(2)    // returns -1 (not found)
     * lRUCache.put(4, 4) // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
     * lRUCache.get(1)    // return -1 (not found)
     * lRUCache.get(3)    // return 3
     * lRUCache.get(4)    // return 4
     * */

    /*var lRUCache = new Cache(2)
    lRUCache.put(1, 1) // cache is {1=1}*/
    cache.put(1, 1)
    cache.put(2, 2)
    cache.get(1).map(_.`value`) shouldBe Some(1)
    cache.put(3, 3)
    cache.get(2) shouldBe None
    cache.put(4, 4)
    cache.get(1) shouldBe None
    cache.get(3).map(_.`value`) shouldBe Some(3)
    cache.get(4).map(_.`value`) shouldBe Some(4)
  }
}

/**
 * Write a cache. Initialized with capacity: an integer. When we reach the limit we need to evict on an LRU (least recently used).
 * Implement two methods:
 * * put
 * * get
 * Type of elements in cache is Int: Int
 * */
