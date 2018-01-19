package com.kkyeer.java.utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 0:34 2018/1/20
 * @Modified By:
 */
public class FileUtil {

    /**
     * @Description: 清理指定文件夹中文件的文件名，删除指定的文件名头
     * @Author: kkyeer
     * @Date: 2018/1/20 0:35
     * @param dirPath 待清理文件名的文件夹路径
     * @param fileNameStrToDel 统一清理的文件名字符串
     * @ReturnType void
     *
     */
    public static void cleanHeader(Path dirPath, String fileNameStrToDel) throws FileCleanException {
        if (!Files.isDirectory(dirPath)){
            throw new FileCleanException("input path is not directory");
        }
        try {
            Stream<Path> pathStream = Files.walk(dirPath);
            pathStream.parallel().forEach(path -> {
                if (!Files.isDirectory(path)){
                    String sourceFileName = path.getFileName().toString();
                    String cleanedFileName = sourceFileName.replaceAll(fileNameStrToDel,"");
                    try {
                        Files.move(path,path.resolveSibling(cleanedFileName));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description:
     * @Author: kkyeer
     * @Date: 2018/1/20 0:41
     * @param dirPathString  待清理文件名的文件夹路径
     * @param fileNameStrToDel 统一清理的文件名字符串
     * @throws FileCleanException 发生错误时，抛出异常
     */
    public static void cleanHeader(String dirPathString, String fileNameStrToDel) throws FileCleanException {
        Path dirPath = Paths.get(dirPathString);
        cleanHeader(dirPath,fileNameStrToDel);
    }


    /**
     * @Description: 应用举例
     * @Author: kkyeer
     * @Date: 2018/1/20 0:43
     * @param args 无参数
     * @throws FileCleanException 抛出异常
     */
    public static void main(String[] args) throws FileCleanException {
        cleanHeader("input source file path here","header string to clean");
		dirTreePrinter("input rootPath here");
    }

    /**
     * @Description: 打印文件夹结构
     * @Author: kkyeer
     * @Date: 2018/1/20 0:45
     * @param rootPathString 待打印的文件夹根路径
     */
	public static void dirTreePrinter(String rootPathString){
        try {
            Path rootPath = Paths.get(rootPathString);
            System.out.println(rootPath);
            String blank = "    ";
            String director = "|--→";
            Files.walkFileTree(rootPath,new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    printPlaceHolder(blank,director,dir.getNameCount()-rootPath.getNameCount());
                    System.out.println(dir.getName(dir.getNameCount()-1));
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.SKIP_SUBTREE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    printPlaceHolder(blank,director,file.getNameCount()-rootPath.getNameCount());
                    System.out.println(file.getFileName().toString());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printPlaceHolder(String blank,String director, int times) {
        for (int i = 0;i<times-1;i++) {
            System.out.print(blank);
        }
        System.out.print(director);
    }
}
