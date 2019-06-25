package yen

import java.util.*

class ShortestPathTree {
  var nodes: HashMap<String, DijkstraNode>? = null
  val root: String

  constructor(root: String) {
    this.nodes = HashMap()
    this.root = root
  }

  fun add(newNode: DijkstraNode) {
    nodes?.set(newNode.label!!, newNode)
  }

  fun getParentOf(node: String): String? {
    return if (nodes!!.containsKey(node))
      nodes!![node]!!.parent
    else
      null
  }
}
