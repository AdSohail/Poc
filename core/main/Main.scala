package main

import executor.Executor
import model.{Command, DocumentStore}

import scala.collection.mutable
import scala.util.{Failure, Success}

object Main extends Executor{

  def main(args: Array[String]): Unit = {

    val emptyDocumentStore = DocumentStore(mutable.Map())


    args.length > 0 match {
      case true =>
        val tryCommand = Command(args)
        tryCommand match{
          case Success(command) =>
            val executionStatus = executeCommand( command, emptyDocumentStore )
            println(executionStatus.msg)
          case Failure(ex) => println(ex.getMessage)
        }
      case false => print("number of arguments should be greater then 0 <index/ query params>")
    }
  }
}
