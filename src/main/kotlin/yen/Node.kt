package yen

import yen.Edge
import java.util.*

open class Node {
  var label: String? = null
  var neighbors: HashMap<String, Double> = HashMap()

  val adjacencyList: Set<String>
    get() = neighbors.keys

  val edges: LinkedList<Edge>
    get() {
      val edges = LinkedList<Edge>()
      for (toNodeLabel in neighbors.keys) {
        edges.add(Edge(label!!, toNodeLabel, neighbors[toNodeLabel]!!))
      }

      return edges
    }

  constructor(label: String) {
    this.label = label
    neighbors = HashMap()
  }

  fun addEdge(toNodeLabel: String, weight: Double) {
    neighbors[toNodeLabel] = weight
  }

  fun removeEdge(toNodeLabel: String): Double {
    if (neighbors.containsKey(toNodeLabel)) {
      val weight = neighbors[toNodeLabel]
      neighbors.remove(toNodeLabel)
      return weight!!
    }

    return java.lang.Double.MAX_VALUE
  }

  override fun toString(): String {
    val nodeStringB = StringBuilder()
    nodeStringB.append(label)
    nodeStringB.append(": {")
    val adjacencyList = this.adjacencyList
    val alIt = adjacencyList.iterator()
    val neighbors = this.neighbors
    while (alIt.hasNext()) {
      val neighborLabel = alIt.next()
      nodeStringB.append(neighborLabel)
      nodeStringB.append(": ")
      nodeStringB.append(neighbors[neighborLabel])
      if (alIt.hasNext())
        nodeStringB.append(", ")
    }
    nodeStringB.append("}")
    nodeStringB.append("\n")

    return nodeStringB.toString()
  }
}
