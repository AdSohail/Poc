package executor

import model._

case class ExecutionStatus(msg : String, status : Boolean)

trait Executor{
  def executeCommand(command: Command, documentStore: DocumentStore) : ExecutionStatus = {
    command match {
      case IndexCommand(docId, tokens) =>
        documentStore.addDocument(docId, Document(docId, tokens))
        ExecutionStatus(s"index $docId ${tokens.mkString(" ")}", true)

      case QueryCommand(tokens) =>
        val docIds = tokens.par.map( token => documentStore.searchToken(token) ).flatten
        docIds.size > 0 match {
          case true => ExecutionStatus(docIds.mkString(" "), true)
          case false => ExecutionStatus("no matching document found", false)
        }
      case InvalidCommand(msg) => ExecutionStatus(msg, false)
    }
  }
}
