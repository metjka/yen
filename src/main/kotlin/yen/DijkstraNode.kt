package yen

import java.util.*

class DijkstraNode(label: String) : Node(label), Comparable<DijkstraNode> {
  var dist: Double = 0.toDouble()
  var depth: Int = 0

  var parent: String?
    get() {
      val neighborLabels = super.neighbors.keys
      if (neighborLabels.size > 1) {
        return null
      }
      return if (neighborLabels.size < 1) {
        null
      } else super.neighbors.keys.iterator().next()
    }
    set(parent) {
      super.neighbors = HashMap()
      super.neighbors[parent!!] = 0.0
    }


  init {
    this.dist = 0.0
  }

  override fun compareTo(comparedNode: DijkstraNode): Int {
    val distance1 = this.dist
    val distance2 = comparedNode.dist
    if (distance1 == distance2)
      return 0
    return if (distance1 > distance2) 1 else -1
  }

  fun equals(comparedNode: DijkstraNode): Boolean {
    return this.label == comparedNode.label
  }
}
