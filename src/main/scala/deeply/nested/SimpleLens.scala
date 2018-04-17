package deeply.nested

object SimpleLens {

  import shapeless._

  case class Topology(version: String)

  val topology = Topology(version = "1.0.0")

  val versionLens = lens[Topology].version

  println(topology.copy(version = "7.0.0"))

  println(versionLens.set(topology)("7.0.0"))

}

