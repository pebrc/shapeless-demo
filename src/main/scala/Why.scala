

object Why {

  case class Element(name: String)
  case class Elasticsearch(version: String)
  case class Topology(elasticsearch: Elasticsearch, elements: Seq[Element])
  case class Plan(topology: Topology, name: String)

  val plan = Plan(Topology(Elasticsearch("5.4.6"), elements = Seq.empty), "my-cluster")

  plan.copy(
    topology = plan.topology.copy(
      elasticsearch = plan.topology.elasticsearch.copy(
        version = "7.0.0")))

  /**
   *
   *
   *
   *
   *
   *
   *
   *
   *
   *
   */

  import shapeless._
  val versionLens = lens[Plan].topology.elasticsearch.version

  versionLens.set(plan)("7.0.0")
  versionLens.modify(plan)(_ + "-SNAPSHOT")
  versionLens.get(plan)

}

