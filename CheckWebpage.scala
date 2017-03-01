import org.jsoup.nodes.Document
import org.jsoup.Jsoup
import com.github.takezoe.scala.jdbc._
import java.sql.{Connection, DriverManager}

object CheckWebpage {

  def main(arguments: Array[String]) {
    val websiteURL = arguments(0)
    val doc = Jsoup.connect(websiteURL).get()
    println(doc.select("title").text)

    Class.forName("org.postgresql.Driver")
    val databaseConnection = DriverManager.getConnection("jdbc:postgresql://localhost/link_project",
      "link_project_user", "banana")

    val db = DB.apply(databaseConnection)
    db.update(sql"INSERT INTO crawls (id, url) VALUES (1, $websiteURL)")

    printLinks(doc, websiteURL)

    databaseConnection.close()
  }

  def printLinks(document: Document, websiteURL: String): Unit = {
    val links = document.select("a[href]")
    // println(links + "\n")
    val linksAsScalaList = scala.collection.JavaConversions.asScalaBuffer(links).toList
    linksAsScalaList.foreach { a =>
      val href = a.attr("href")
      val url =
        if (href.startsWith("http"))
          href
        else
          websiteURL + href
      println("Checking URL " + url + " ...")
      try {
        val response = Jsoup.connect(url).ignoreHttpErrors(true).execute()
        println(response.statusCode())
      } catch {
        case error: javax.net.ssl.SSLProtocolException => println("Error!")
        case error: java.net.UnknownHostException => println("A different error")
      }
      "INSERT INTO links ()"
    }
  }

//  def splitName(fullName: String): (String, String) = {
//    val words = fullName.split(" ")
//    if (words.length != 2) {
//      throw new Exception("fullName must have exactly two words")
//    }
//    (words(0), words(1))
//  }
}
