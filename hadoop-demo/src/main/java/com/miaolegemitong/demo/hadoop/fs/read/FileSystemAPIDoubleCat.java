package com.miaolegemitong.demo.hadoop.fs.read;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;

/**
 * @author miaolegemitong
 */
public class FileSystemAPIDoubleCat {
    public static void main(String[] args) throws IOException {
        String uri = "hdfs://localhost:9000/user/wordcount";
        Configuration conf = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(uri), conf);
        FSDataInputStream inputStream = null;
        try {
            inputStream = fileSystem.open(new Path(uri));
            IOUtils.copyBytes(inputStream, System.out, 4096, false);
            inputStream.seek(10); //回退，因为FSDataInputStream实现了
            IOUtils.copyBytes(inputStream, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(inputStream);
        }
    }
}
