package com.miaolegemitong.demo.hadoop.fs.read;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * @author miaolegemitong
 */
public class FileSystemAPICat {
    public static void main(String[] args) throws IOException {
        String uri = "hdfs://localhost:9000/user/wordcount";
        Configuration conf = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(uri), conf);
        InputStream inputStream = null;
        try {
            inputStream = fileSystem.open(new Path(uri));
            IOUtils.copyBytes(inputStream, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(inputStream);
        }
    }
}
