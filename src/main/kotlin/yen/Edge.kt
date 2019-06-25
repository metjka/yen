package yen

class Edge(fromNode: String, toNode: String, var weight: Double) : Cloneable {
  var fromNode: String? = fromNode
  var toNode: String? = toNode

  public override fun clone(): Edge {
    return Edge(fromNode!!, toNode!!, weight)
  }

  override fun toString(): String {
    val sb = StringBuilder()
    sb.append("(")
    sb.append(fromNode)
    sb.append(",")
    sb.append(toNode)
    sb.append("){")
    sb.append(weight)
    sb.append("}")

    return sb.toString()
  }

  fun equals(edge2: Edge): Boolean {
    return hasSameEndpoints(edge2) && weight == edge2.weight
  }

  fun hasSameEndpoints(edge2: Edge): Boolean {
    return fromNode == edge2.fromNode && toNode == edge2.toNode
  }
}
