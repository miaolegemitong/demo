package com.miaolegemitong.demo.hadoop.fs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author miaolegemitong
 */
public class ShowFileStatus {
    public static void main(String[] args) throws IOException {
        String directoryPath = "hdfs://localhost:9000/user";
        String filePath = "hdfs://localhost:9000/user/wordcount";
        String notFoundPath = "hdfs://localhost:9000/user/wordcount1";
        Configuration conf = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(directoryPath), conf);
        FileStatus directoryStatus = fileSystem.getFileStatus(new Path(directoryPath));
        printInfo(directoryStatus);
        FileStatus fileStatus = fileSystem.getFileStatus(new Path(filePath));
        printInfo(fileStatus);
        System.out.println(fileSystem.exists(new Path(notFoundPath)));
        fileSystem.getFileStatus(new Path(notFoundPath)); //抛出FileNotFound异常
    }

    static void printInfo(FileStatus fileStatus) {
        System.out.println("----------start----------");
        System.out.println("路径：" + fileStatus.getPath().toUri().getPath());
        System.out.println("类型：" + (fileStatus.isDirectory() ? "目录" : "文件"));
        System.out.println("大小：" + fileStatus.getLen());
        System.out.println("修改时间："
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(fileStatus.getModificationTime())));
        System.out.println("副本数：" + fileStatus.getReplication());
        System.out.println("块大小：" + fileStatus.getBlockSize());
        System.out.println("所有者：" + fileStatus.getOwner());
        System.out.println("所属组：" + fileStatus.getGroup());
        System.out.println("权限：" + fileStatus.getPermission());
        System.out.println("----------end----------");
    }
}
