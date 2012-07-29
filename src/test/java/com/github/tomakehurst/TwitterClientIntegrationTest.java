package com.github.tomakehurst;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TwitterClientIntegrationTest {

    @Test
    public void pullsTweetFromTwitter() {
        TwitterClient twitterClient = new TwitterClient("http://search.twitter.com");
        String tweet = twitterClient.getSirBonarsLatestTweet();

        assertNotNull(tweet);
        System.out.println(tweet);
    }
}
