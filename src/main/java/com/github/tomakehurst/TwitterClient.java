package com.github.tomakehurst;

import com.google.common.io.ByteStreams;
import com.jayway.jsonpath.JsonPath;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class TwitterClient {

    private static final String SIR_BONAR_LATEST_TWEET_URL =
            "%s/search.json?q=from:sirbonar&result_type=recent&rpp=1";

    private String twitterApiRoot;

    public TwitterClient(String twitterApiRoot) {
        this.twitterApiRoot = twitterApiRoot;
    }

    public String getSirBonarsLatestTweet() {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet();

        String url = String.format(SIR_BONAR_LATEST_TWEET_URL, twitterApiRoot);
        get.addHeader("Accept", "application/json");
        get.setURI(URI.create(url));

        try {
            HttpResponse response = client.execute(get);
            String json = getStringFrom(response.getEntity().getContent());
            System.out.println(json);
            return JsonPath.read(json, "$.results[0].text");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getStringFrom(InputStream in) {
        try {
            return new String(ByteStreams.toByteArray(in));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
