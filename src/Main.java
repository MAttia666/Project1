import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.readString;

public class Main {
    private  static Scanner scanner;
    public static void main(String[] args) throws IOException {

        Map<String,String> variableMap = fillMap();
        Path path = Paths.get("D:\\testing_course\\fwd\\session 1\\student-data.txt");
        Stream<String> lines;
        try {
            lines = Files.lines(path, Charset.forName("UTF-8"));
            List<String> replacedLines = lines.map(line -> replaceTag(line,variableMap))
                    .collect(Collectors.toList());
            Files.write(path, replacedLines, Charset.forName("UTF-8"));
            lines.close();
            System.out.println("Find and replace done");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Map<String,String> fillMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("$", "\n");
        map.put("<<#>>", ", ");
        return map;
    }
    private static String replaceTag(String str, Map<String,String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (str.contains(entry.getKey())) {
                str = str.replace(entry.getKey(), entry.getValue());
            }
        }
        return str;
    }
}




