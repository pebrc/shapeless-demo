package deeply.nested
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

import shapeless._

@State(Scope.Benchmark)
class MicroBenchmark {

  case class Topology(version: String)

  val topology = Topology(version = "1.0.0")

  val versionLens = lens[Topology].version

  @Benchmark
  def caseClassCopy = topology.copy(version = "7.0.0")

  @Benchmark
  def lensSet = versionLens.set(topology)("7.0.0")

  @Benchmark
  def reflectiveSet = topology.getClass.getMethods.find(_.getName == "copy").get.invoke(topology, "7.0.0".asInstanceOf[AnyRef])

}

