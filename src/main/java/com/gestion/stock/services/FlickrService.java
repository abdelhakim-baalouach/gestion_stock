package com.gestion.stock.services;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

public interface FlickrService {

    String savePhoto(InputStream image, String title) throws FlickrException;
}
