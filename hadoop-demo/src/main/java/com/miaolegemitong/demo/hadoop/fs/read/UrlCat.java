package com.miaolegemitong.demo.hadoop.fs.read;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author miaolegemitong
 */
public class UrlCat {
    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public static void main(String[] args) {
        InputStream inputStream = null;
        try {
            inputStream = new URL("hdfs://localhost:9000/user/wordcount").openStream();
            IOUtils.copyBytes(inputStream, System.out, 4096, false);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(inputStream);
        }
    }
}
