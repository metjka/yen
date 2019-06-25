package yen

import yen.Edge
import java.io.BufferedReader
import java.io.FileReader
import java.io.StringReader
import java.util.*
import kotlin.collections.HashMap

class Graph {
  var nodes: HashMap<String, Node> = HashMap()

  constructor() {
    this.nodes = HashMap()
  }

  fun addNode(label: String) {
    if (!nodes.containsKey(label))
      nodes[label] = Node(label)
  }

  fun addEdge(label1: String, label2: String, weight: Double) {
    if (!nodes.containsKey(label1))
      addNode(label1)
    if (!nodes.containsKey(label2))
      addNode(label2)
    nodes[label1]!!.addEdge(label2, weight)
  }

  fun addEdge(edge: Edge) {
    addEdge(edge.fromNode!!, edge.toNode!!, edge.weight)
  }

  fun addEdges(edges: List<Edge>) {
    for (edge in edges) {
      addEdge(edge)
    }
  }

  fun removeEdge(label1: String, label2: String): Edge? {
    if (nodes.containsKey(label1)) {
      val weight = nodes[label1]!!.removeEdge(label2)
      if (weight != java.lang.Double.MAX_VALUE) {
        return Edge(label1, label2, weight)
      }
    }

    return null
  }

  fun removeNode(label: String): List<Edge> {
    val edges = LinkedList<Edge>()
    if (nodes.containsKey(label)) {
      val node = nodes.remove(label)
      edges.addAll(node!!.edges)
      edges.addAll(removeEdgesToNode(label))
    }

    return edges
  }

  fun removeEdgesToNode(label: String): List<Edge> {
    val edges = LinkedList<Edge>()
    for (node in nodes.values) {
      if (node.adjacencyList.contains(label)) {
        val weight = node.removeEdge(label)
        edges.add(Edge(node.label!!, label, weight))
      }
    }
    return edges
  }

  fun readFromString(graphString: String) {
    try {
      val `in` = BufferedReader(StringReader(graphString))

      var line: String? = `in`.readLine()

      while (line != null) {
        val edgeDescription = line.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (edgeDescription.size == 3) {
          addEdge(edgeDescription[0], edgeDescription[1], java.lang.Double.parseDouble(edgeDescription[2]))
        }
        line = `in`.readLine()
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  override fun toString(): String {
    val graphStringB = StringBuilder()
    val it = nodes.keys.iterator()
    while (it.hasNext()) {
      val nodeLabel = it.next()
      graphStringB.append(nodeLabel)
      graphStringB.append(": {")
      val node = nodes[nodeLabel]
      val adjacencyList = node!!.adjacencyList
      val alIt = adjacencyList.iterator()
      val neighbors = node.neighbors
      while (alIt.hasNext()) {
        val neighborLabel = alIt.next()
        graphStringB.append(neighborLabel)
        graphStringB.append(": ")
        graphStringB.append(neighbors[neighborLabel])
        if (alIt.hasNext())
          graphStringB.append(", ")
      }
      graphStringB.append("}")
      graphStringB.append("\n")
    }

    return graphStringB.toString()
  }
}
