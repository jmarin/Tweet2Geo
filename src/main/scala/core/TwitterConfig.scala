package core

import com.typesafe.config.{ Config, ConfigFactory }
import java.io.File
import twitter4j.conf.ConfigurationBuilder

object TwitterConfig {

  def twitterConfig(file: File) = {
    val configFile = ConfigFactory.parseFile(file)
    val consumerKey = configFile.getString("consumerKey")
    val consumerSecret = configFile.getString("consumerSecret")
    val accessToken = configFile.getString("authAccessToken")
    val accessTokenSecret = configFile.getString("authAccessTokenSecret")
    val config = new ConfigurationBuilder()
      .setOAuthConsumerKey(consumerKey)
      .setOAuthConsumerSecret(consumerSecret)
      .setOAuthAccessToken(accessToken)
      .setOAuthAccessTokenSecret(accessTokenSecret)
      .build
    config
  }
}
