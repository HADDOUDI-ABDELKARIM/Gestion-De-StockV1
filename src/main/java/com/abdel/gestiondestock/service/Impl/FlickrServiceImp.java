package com.abdel.gestiondestock.service.Impl;

import com.abdel.gestiondestock.service.FlickrService;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.uploader.UploadMetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
public class FlickrServiceImp  implements FlickrService {


    @Value("${flickr.apiKey}")
    private String apiKey;

    @Value("${flickr.apiSecret}")
    private String apiSecret;

    @Value("${flickr.appKey}")
    private String appKey;

    @Value("${flickr.appSecret}")
    private String appSecret;
    private Flickr flickr;


    @Override
    public String savePhoto(InputStream photo, String title) throws FlickrException {
        Flickr flickr1=getFlickr();
        UploadMetaData uploadMetaData=new UploadMetaData();
        uploadMetaData.setTitle(title);

        String photoID=flickr.getUploader().upload(photo,uploadMetaData);

        return flickr.getPhotosInterface().getPhoto(photoID).getMedium640Url();
    }


    public  Flickr getFlickr(){
        flickr=new Flickr(apiKey,apiSecret,new REST());

        Auth auth=new Auth();
        auth.setPermission(Permission.DELETE);

        auth.setToken(appKey);

        auth.setTokenSecret(appSecret);

        RequestContext requestContext=RequestContext.getRequestContext();
        requestContext.setAuth(auth);

        flickr.setAuth(auth);
        return flickr;
    }



}
