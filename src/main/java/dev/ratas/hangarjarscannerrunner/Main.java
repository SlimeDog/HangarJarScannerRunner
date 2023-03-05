package dev.ratas.hangarjarscannerrunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.papermc.hangar.scanner.HangarJarScanner;
import io.papermc.hangar.scanner.model.ScanResult;

public final class Main {

    private static InputStream getInputStream(String filename) {
        try {
            return new FileInputStream(new File(filename));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static void showResults(List<ScanResult> results) {
        for (ScanResult result : results) {
            System.out.println("" + result);
        }
    }

    public static void main(String[] args) {
        HangarJarScanner scanner = new HangarJarScanner();
        for (String jarName : args) {
            System.out.println("Checking " + jarName);
            InputStream jar;
            try {
                jar = getInputStream(jarName);
            } catch (IllegalArgumentException e) {
                System.err.println("Could not load JAR:");
                e.printStackTrace();
                continue;
            }
            List<ScanResult> results;
            try {
                results = scanner.scanJar(jar, jarName);
            } catch (IOException e) {
                System.err.println("Issue loading JAR:");
                e.printStackTrace();
                continue;
            }
            showResults(results);
        }
    }

}
