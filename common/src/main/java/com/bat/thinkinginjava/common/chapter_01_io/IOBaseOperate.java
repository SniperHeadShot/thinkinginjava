package com.bat.thinkinginjava.common.chapter_01_io;

import java.io.File;
import java.util.List;

/**
 * IO 基本操作
 *
 * @author ZhengYu
 * @version 1.0 2020/8/21 16:42
 **/
public interface IOBaseOperate {

    /**
     * 指定目录下创建一个新目录
     *
     * @param path    路径
     * @param dirName 文件名
     * @author ZhengYu
     */
    boolean createDir(String path, String dirName);

    /**
     * 指定目录下创建一个新文件
     *
     * @param path     路径
     * @param fileName 文件名
     * @author ZhengYu
     */
    File createFile(String path, String fileName);

    /**
     * 指定路径文件写入内容
     *
     * @param path     文件路径
     * @param fileName 文件名称
     * @param context  文件内容
     * @author ZhengYu
     */
    boolean writeToFile(String path, String fileName, String context);

    /**
     * 读取路径下的所有文件集合
     *
     * @param path 路径
     * @author ZhengYu
     */
    List<String> readFileNameList(String path);

    /**
     * 删除路径下的所有文件及文件夹
     *
     * @param path 路径
     * @author ZhengYu
     */
    boolean deleteFiles(String path);
}
