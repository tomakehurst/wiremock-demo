package com.github.tomakehurst;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CompleteTwitterClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Test
    public void shouldReturnFirstTweetTextInResults() {
        stubFor(get(urlEqualTo(SEARCH_RELATIVE_URL)).willReturn(
                aResponse()
                        .withStatus(200)
                        .withBody(CONTENT)));

        TwitterClient twitterClient = new TwitterClient("http://localhost:8080");
        String tweet = twitterClient.getSirBonarsLatestTweet();

        assertThat(tweet, is("Stubbed tweet text"));

        verify(getRequestedFor(urlMatching(".*search.*")).withHeader("Accept", equalTo("application/json")));

    }

    private static final String SEARCH_RELATIVE_URL = "/search.json?q=from:sirbonar&result_type=recent&rpp=1";

    private static final String CONTENT =
            "    {\n" +
            "       \"completed_in\": 0.12,\n" +
            "       \"max_id\": 228773286266404860,\n" +
            "       \"max_id_str\": \"228773286266404864\",\n" +
            "       \"next_page\": \"?page=2&max_id=228773286266404864&q=from%3Asirbonar&rpp=1&result_type=recent\",\n" +
            "       \"page\": 1,\n" +
            "       \"query\": \"from%3Asirbonar\",\n" +
            "       \"refresh_url\": \"?since_id=228773286266404864&q=from%3Asirbonar&result_type=recent\",\n" +
            "       \"results\":\n" +
            "       [\n" +
            "           {\n" +
            "               \"created_at\": \"Fri, 27 Jul 2012 08:46:16 +0000\",\n" +
            "               \"from_user\": \"sirbonar\",\n" +
            "               \"from_user_id\": 52387673,\n" +
            "               \"from_user_id_str\": \"52387673\",\n" +
            "               \"from_user_name\": \"Sir Bonar Neville-K\",\n" +
            "               \"geo\": null,\n" +
            "               \"id\": 228773286266404860,\n" +
            "               \"id_str\": \"228773286266404864\",\n" +
            "               \"iso_language_code\": \"en\",\n" +
            "               \"metadata\":\n" +
            "               {\n" +
            "                   \"result_type\": \"recent\"\n" +
            "               },\n" +
            "               \"profile_image_url\": \"http://a0.twimg.com/profile_images/525192091/Bonar_normal.jpg\",\n" +
            "               \"profile_image_url_https\": \"https://si0.twimg.com/profile_images/525192091/Bonar_normal.jpg\",\n" +
            "               \"source\": \"<a href=\\\"http://www.tweetdeck.com\\\" rel=\\\"nofollow\\\">TweetDeck</a>\",\n" +
            "               \"text\": \"Stubbed tweet text\",\n" +
            "               \"to_user\": null,\n" +
            "               \"to_user_id\": 0,\n" +
            "               \"to_user_id_str\": \"0\",\n" +
            "               \"to_user_name\": null\n" +
            "           }\n" +
            "       ],\n" +
            "       \"results_per_page\": 1,\n" +
            "       \"since_id\": 0,\n" +
            "       \"since_id_str\": \"0\"\n" +
            "    }";
}
