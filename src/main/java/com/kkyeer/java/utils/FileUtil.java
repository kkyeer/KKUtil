package com.kkyeer.java.utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class FileUtil {
    private static void cleanHeader(Path dirPath, String fileNameStrToDel) throws FileCleanException {
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

    private static void cleanHeader(String dirPathString, String fileNameStrToDel) throws FileCleanException {
        Path dirPath = Paths.get(dirPathString);
        cleanHeader(dirPath,fileNameStrToDel);
    }

    public static void main(String[] args) throws FileCleanException, IOException {
        cleanHeader("input source file path here","header string to clean");
		dirTreePrinter("input rootPath here");
    }
	private static void dirTreePrinter(String rootPathString){
        try {
            Path rootPath = Paths.get(rootPathString);
            System.out.println(rootPath);
            String blank = "    ";
            String director = "|--→";
            Files.walkFileTree(rootPath,new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    printPlaceHolder(blank,director,dir.getNameCount()-rootPath.getNameCount());
                    System.out.println(dir.getName(dir.getNameCount()-1));
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.SKIP_SUBTREE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
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
