package core

import twitter4j.conf.ConfigurationBuilder
import twitter4j.TwitterStreamFactory
import java.io.File

object TwitterStreamer {

  def getStream(file: File) = {
    val config = TwitterConfig.twitterConfig(file)
    val twitterStream = new TwitterStreamFactory(config).getInstance
    twitterStream
  }
}
