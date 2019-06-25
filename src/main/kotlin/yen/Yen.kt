package yen

import yen.Dijkstra
import yen.Edge
import yen.Path
import java.util.*

class Yen {

  fun ksp(graph: Graph, sourceLabel: String, targetLabel: String, K: Int): List<Path> {
    val ksp = ArrayList<Path>()
    val candidates = PriorityQueue<Path>()

    try {
      var kthPath = Dijkstra.shortestPath(graph, sourceLabel, targetLabel)
      ksp.add(kthPath!!)

      for (k in 1 until K) {
        val previousPath = ksp[k - 1]
        for (i in 0 until previousPath.size()) {
          val removedEdges = LinkedList<Edge>()

          val spurNode = previousPath.edges!![i].fromNode

          val rootPath = previousPath.cloneTo(i)

          for (p in ksp) {
            val stub = p.cloneTo(i)
            if (rootPath.equals(stub)) {
              val re = p.edges!![i]
              graph.removeEdge(re.fromNode!!, re.toNode!!)
              removedEdges.add(re)
            }
          }

          for (rootPathEdge in rootPath.edges!!) {
            val rn = rootPathEdge.fromNode
            if (rn != spurNode) {
              removedEdges.addAll(graph.removeNode(rn!!))
            }
          }

          val spurPath = Dijkstra.shortestPath(graph, spurNode!!, targetLabel)

          if (spurPath != null) {
            val totalPath = rootPath.clone()
            totalPath.addPath(spurPath)
            if (!candidates.contains(totalPath))
              candidates.add(totalPath)
          }

          graph.addEdges(removedEdges)
        }

        var isNewPath: Boolean
        do {
          kthPath = candidates.poll()
          isNewPath = true
          if (kthPath != null) {
            for (p in ksp) {
              if (p.equals(kthPath)) {
                isNewPath = false
                break
              }
            }
          }
        } while (!isNewPath)

        if (kthPath == null) {
          break
        }

        ksp.add(kthPath)
      }
    } catch (e: Exception) {
      println(e)
      e.printStackTrace()
    }

    return ksp
  }
}
