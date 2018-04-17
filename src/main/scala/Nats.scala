

object Nats {
  import shapeless._
  import shapeless.nat._
  import shapeless.ops.nat._

  /* what is 0?*/
  _0

  /**type _1 = Succ[_0]**/

  Succ[_1]

  toInt(Succ[_1])

  Pred[_3]

  toInt(_3)

  /**
   * But why?
   *
   *
   *  Consider a cluster plan again..
   */

  case class CreateCluster(name: String, version: String, nodes: Int, zones: Int)
  case class Cluster(version: String, nodes: Int, zones: Int)
  val create = CreateCluster("my-cluster", "7.0.0", 1, 3)

  /**
   * we can now create a generic represenation of CreateCluster as a Heterogenous List
   */
  val gen = Generic[CreateCluster]
  gen.to(create)

  /**
   * for case classes it might be handy to keep the field names around thus
   */
  val createGen = LabelledGeneric[CreateCluster]
  val clusterGen = LabelledGeneric[Cluster]

  /**
   * type safe generic conversions between cases classes
   */
  val createRec = createGen.to(create)
  val withoutName = createRec.tail
  val created = clusterGen.from(withoutName)

  import syntax.singleton._

  val clusterRec = clusterGen.to(created)
  createGen.from(('name ->> "another create") :: clusterRec)

  /**
   * what has this to do with nats?
   */

  import shapeless.labelled._
  clusterRec.at(_1)

  /**
   * because in shapeless 1.2.4 (our version) the way to define a lens was as follows
   */

  lens[Cluster] >> _0
  //which is in recent versions of shapeless equivalent to
  lens[Cluster].version
}
