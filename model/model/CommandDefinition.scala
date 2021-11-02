package model

import scala.util.{Success, Try}


sealed trait Command

object Command{
  def apply(args : Array[String]) : Try[Command] = {
    args(0).trim.toLowerCase() match {
      case "index" => IndexCommand(args(1), args.drop(2))
      case "query" => Success(QueryCommand(args))
      case _ => Success( InvalidCommand("supported commands are query and index") )
    }
  }
}

case class IndexCommand(docId : Int, tokens : Seq[String]) extends Command

object IndexCommand{
  def apply(docId: String, tokens: Seq[String]): Try[IndexCommand] = Try{
    new IndexCommand(docId.toInt, tokens)
  }
}

case class QueryCommand(tokens : Seq[String]) extends Command

case class InvalidCommand(msg : String) extends Command