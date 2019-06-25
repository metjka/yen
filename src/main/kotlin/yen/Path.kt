package yen

import java.util.*

class Path : Cloneable, Comparable<Path> {
  var edges: LinkedList<Edge>? = null
  var totalCost: Double = 0.toDouble()

  constructor() {
    edges = LinkedList()
    totalCost = 0.0
  }

  constructor(edges: LinkedList<Edge>) {
    this.edges = edges
    totalCost = 0.0
    for (edge in edges) {
      totalCost += edge.weight
    }
  }

  fun addFirst(edge: Edge) {
    edges!!.addFirst(edge)
    totalCost += edge.weight
  }

  fun add(edge: Edge) {
    edges!!.add(edge)
    totalCost += edge.weight
  }

  fun size(): Int {
    return edges!!.size
  }

  override fun toString(): String {
    val sb = StringBuilder()
    val numEdges = edges!!.size
    sb.append(totalCost)
    sb.append(": [")
    if (numEdges > 0) {
      for (i in edges!!.indices) {
        sb.append(edges!![i].fromNode!!.toString())
        sb.append("-")
      }

      sb.append(edges!!.last.toNode!!.toString())
    }
    sb.append("]")
    return sb.toString()
  }

  fun equals(path2: Path?): Boolean {
    if (path2 == null)
      return false

    val edges2 = path2.edges

    val numEdges1 = edges!!.size
    val numEdges2 = edges2!!.size

    if (numEdges1 != numEdges2) {
      return false
    }

    for (i in 0 until numEdges1) {
      val edge1 = edges!![i]
      val edge2 = edges2[i]
      if (edge1.fromNode != edge2.fromNode)
        return false
      if (edge1.toNode != edge2.toNode)
        return false
    }

    return true
  }

  override fun compareTo(path2: Path): Int {
    val path2Cost = path2.totalCost
    if (totalCost == path2Cost)
      return 0
    return if (totalCost > path2Cost) 1 else -1
  }

  public override fun clone(): Path {
    val edges = LinkedList<Edge>()

    for (edge in this.edges!!) {
      edges.add(edge.clone())
    }

    return Path(edges)
  }

  fun cloneTo(i: Int): Path {
    var i = i
    val edges = LinkedList<Edge>()
    val l = this.edges!!.size
    if (i > l)
      i = l

    //for (Edge edge : this.edges.subList(0,i)) {
    for (j in 0 until i) {
      edges.add(this.edges!![j].clone())
    }

    return Path(edges)
  }

  fun addPath(p2: Path) {
    this.edges!!.addAll(p2.edges!!)
    this.totalCost += p2.totalCost
  }
}
