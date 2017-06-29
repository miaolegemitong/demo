package com.miaolegemitong.demo.hadoop.fs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

/**
 * @author miaolegemitong
 */
public class ListFileStatusByPattern {
    public static void main(String[] args) throws IOException {
        String directoryPath = "hdfs://localhost:9000/user";
        String pattern = "/*/*";
        Configuration conf = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(directoryPath), conf);
        FileStatus[] statuses = fileSystem.globStatus(new Path(pattern));
        for (FileStatus status : statuses) {
            ShowFileStatus.printInfo(status);
        }
    }
}
