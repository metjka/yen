package yen.server

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import yen.Graph
import yen.Path
import yen.Yen

@CrossOrigin()
@RestController("/yen")
class YenSilver {

  @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
  fun solveYen(@RequestBody arrayGraph: String?, @RequestParam from: String?, @RequestParam to: String?): List<Path> {
    if (arrayGraph === null || from === null || to === null) {
      throw Throwable("'From' or 'to' was not provided")
    }
    val graph = Graph()
    graph.readFromString(arrayGraph)

    val yenAlgorithm = Yen()
    val ksp = yenAlgorithm.ksp(graph, from, to, 50)
    val stringBuilder = StringBuilder()
    println("k) cost: [path]")
    ksp.forEachIndexed { index, path ->
      stringBuilder.append((index + 1).toString() + ") " + path + "\n")
      println((index + 1).toString() + ") " + path)
    }
    return ksp
  }

  @GetMapping()
  fun ping(): String {
    return "pong"
  }
}
