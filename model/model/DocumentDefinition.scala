package model

import scala.collection.mutable

case class Document(docId : Int, tokens : Seq[String])

case class DocumentStore(private val map : mutable.Map[Int, Document]){

  def addDocument(docId : Int, document : Document) : (Int, Document) = {
    map.update(docId, document)
    (docId, document)
  }

  def searchToken(token : String) : Seq[Int] = {
    for{
      document <- map.values.toSeq
      tokenExists = document.tokens.contains(token)
      if(tokenExists)
    } yield document.docId
  }
}
