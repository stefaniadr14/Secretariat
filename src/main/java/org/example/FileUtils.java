package org.example;

import java.io.*;
import java.util.List;

public class FileUtils {
    // Metodă statică pentru citirea liniilor dintr-un fișier
    public static List<String> citesteLinii(File fisier) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fisier))) {
            return br.lines().toList();
        }
    }

    // Metodă statică pentru scrierea liniilor într-un fișier
    public static void scrieLinii(File fisier, List<String> linii) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fisier, true))) {
            for (String linie : linii) {
                bw.write(linie);
                bw.newLine();
            }
        }
    }
}
