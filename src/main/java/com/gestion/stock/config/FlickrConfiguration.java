package com.gestion.stock.config;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.auth.Auth;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Configuration
public class FlickrConfiguration {

    @Value("${flickr.apiKey}")
    private String apiKey;

    @Value("${flickr.apiSecret}")
    private String apiSecret;


    @Bean
    public Flickr getFlickr() throws InterruptedException, ExecutionException, IOException, FlickrException {
        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());

        OAuth10aService service = new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .build(FlickrApi.instance(FlickrApi.FlickrPerm.DELETE));

        final Scanner scanner = new Scanner(System.in);
        final OAuth1RequestToken requestToken = service.getRequestToken();
        final String authUrl = service.getAuthorizationUrl(requestToken);
        System.out.println(authUrl);
        System.out.println("Paste it here >> ");
        final String authVerifier = scanner.nextLine();

        OAuth1AccessToken auth1AccessToken = service.getAccessToken(requestToken, authVerifier);
        System.out.println("appKey: " + auth1AccessToken.getToken());
        System.out.println("appSecret: " + auth1AccessToken.getTokenSecret());

        Auth auth = flickr.getAuthInterface().checkToken(auth1AccessToken);
        System.out.println("-------------------- check token is valid ----------------------------");
        System.out.println("appKey: " + auth.getToken());
        System.out.println("appSecret: " + auth.getTokenSecret());

        return flickr;
    }
}
