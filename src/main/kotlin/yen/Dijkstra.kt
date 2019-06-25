package yen

import java.util.*

object Dijkstra {

  @Throws(Exception::class)
  fun shortestPath(graph: Graph, sourceLabel: String, targetLabel: String): Path? {
    val nodes = graph.nodes
    val predecessorTree = ShortestPathTree(sourceLabel)
    val pq = PriorityQueue<DijkstraNode>()
    for (nodeLabel in nodes.keys) {
      val newNode = DijkstraNode(nodeLabel)
      newNode.dist = java.lang.Double.MAX_VALUE
      newNode.depth = Integer.MAX_VALUE
      predecessorTree.add(newNode)
    }
    val sourceNode = predecessorTree.nodes!![predecessorTree.root]!!
    sourceNode.dist = 0.0
    sourceNode.depth = 0
    pq.add(sourceNode)

    while (!pq.isEmpty()) {
      val current = pq.poll()
      val currLabel = current.label
      if (currLabel == targetLabel) {
        val shortestPath = Path()
        var currentN = targetLabel
        var parentN = predecessorTree.getParentOf(currentN)
        while (parentN != null) {
          shortestPath.addFirst(Edge(parentN, currentN, nodes[parentN]!!.neighbors[currentN]!!))
          currentN = parentN
          parentN = predecessorTree.getParentOf(currentN)
        }
        return shortestPath
      }
      val neighbors = nodes[currLabel]!!.neighbors
      for (currNeighborLabel in neighbors.keys) {
        val neighborNode = predecessorTree.nodes!![currNeighborLabel]
        val currDistance = neighborNode!!.dist
        val newDistance = current.dist + nodes[currLabel]!!.neighbors[currNeighborLabel]!!
        if (newDistance < currDistance) {
          val neighbor = predecessorTree.nodes!![currNeighborLabel]!!

          pq.remove(neighbor)
          neighbor.dist = newDistance
          neighbor.depth = current.depth + 1
          neighbor.parent = currLabel
          pq.add(neighbor)
        }
      }
    }

    return null
  }
}
