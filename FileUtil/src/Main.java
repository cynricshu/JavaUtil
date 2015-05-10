import java.io.*;
import java.util.*;

/**
 * User: Cynric
 * Date: 14-6-23
 * Time: 17:37
 */
public class Main {
    final static String filePath1 = "/Users/cynric/Downloads/20_Newsgroup";
    final static String filePath2 = "/Users/cynric/Downloads/20_Newsgroup/output";
    final static String filePath3 = "/Users/cynric/Downloads/20_Newsgroup/output2";


    public static void main(String[] args) {
//        step2_2();


        step2();
        step3();
        System.out.println("over");

    }

    public static void check(String path) {
        File outDir = new File(path);
        if (outDir.exists()) {

            File[] files = outDir.listFiles();
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }
        } else {
            outDir.mkdir();
        }
    }

    public static void step3() {
        File dir = new File(filePath2);
        HashSet<String> finalVocabSet = new HashSet<>();

        for (File vocabFile : dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().contains("txt") && !pathname.getName().equals("vocab.txt"))
                    return true;
                else
                    return false;
            }
        })) {
            try {
                String line;
                BufferedReader bufferedReader = new BufferedReader(new FileReader(vocabFile));
                while ((line = bufferedReader.readLine()) != null) {
                    if (!line.matches("\\s{1,}"))
                        finalVocabSet.add(line.trim());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        FileWriter fileWriter = null;
        List<String> articleList = new ArrayList<>(finalVocabSet);
        try {
            fileWriter = new FileWriter(filePath2 + "/vocab.txt");
            for (String word : articleList) {
                if (!word.matches("\\s{1,}") && !word.equals("") && !word.equals("\n")) {
                    fileWriter.append(word + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            fileWriter = new FileWriter(filePath2 + "/ap.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (File dataFile : dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().contains("dat") && !pathname.getName().equals("ap.dat"))
                    return true;
                else
                    return false;
            }
        })) {
            String line;
            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new FileReader(dataFile));
                while ((line = bufferedReader.readLine()) != null) {
                    if (!line.matches("\\s{1,}") && !line.equals("") && !line.equals("\n")) {
                        String[] words = line.split(" ");
                        StringBuilder newLine = new StringBuilder(words[0]);
                        List<Pair> list = new ArrayList<>();
                        for (int i = 1; i < words.length; i++) {
                            String[] pair = words[i].split(":");
                            int index = articleList.indexOf(pair[0]) + 1;
                            String count = pair[1];

                            Pair paira = new Pair(index, count);
                            list.add(paira);
                        }

                        Collections.sort(list, new Comparator<Pair>() {
                            @Override
                            public int compare(Pair o1, Pair o2) {
                                if (o1.index < o2.index)
                                    return -1;
                                if (o1.index == o2.index)
                                    return 0;
                                if (o1.index > o2.index)
                                    return 1;
                                return 0;
                            }
                        });
                        for (Pair pair : list) {
                            newLine.append(" ").append(pair.index + ":" + pair.count);
                        }
                        newLine.append("\n");
                        fileWriter.append(newLine.toString());
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    public static void step2() {
        check(filePath2);
        File firstDir = new File(filePath1);
        for (File secondDir : firstDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory() && !pathname.getName().contains("output"))
                    return true;
                else
                    return false;

            }
        })) {

            HashSet<String> vocab = new HashSet<>();
            File vocabFile = new File(filePath1 + "/output/" + secondDir.getName() + ".vocab.txt");
            File outputFile = new File(filePath1 + "/output/" + secondDir.getName() + ".ap.dat");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (File article : secondDir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if (pathname.getName().contains("txt"))
                        return true;
                    else
                        return false;
                }
            })) {
                try {
                    InputStreamReader isr = new InputStreamReader(new FileInputStream(article), "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(isr);
                    Map<String, Integer> articleMap = new HashMap<>();
                    String line;
                    int uniqueWordCount = 0;
                    StringBuilder outputString = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
//                        System.out.println(line);
                        String[] words = line.trim().split("\\s{1,}");
                        for (String word : words) {
                            if (word.contains("/") || word.contains("#") || word.contains("\\") ||
                                    word.contains("~") || word.contains("%") || word.contains("&") ||
                                    word.contains("$"))
                                continue;

                            if (articleMap.containsKey(word)) {
                                articleMap.put(word, articleMap.get(word) + 1);
                            } else {
                                articleMap.put(word, 1);
                            }

                            vocab.add(word);
                        }
                    }

                    uniqueWordCount = articleMap.keySet().size();
                    outputString.append(uniqueWordCount);

                    for (String word : articleMap.keySet()) {
                        int wordCount = articleMap.get(word);
                        outputString.append(" ").append(word + ":" + wordCount);
                    }

                    outputString.append("\n");
                    fileWriter.append(outputString.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileWriter = new FileWriter(vocabFile);
                for (String word : vocab) {
                    if (!word.equals("") && !word.matches("\\s{1,}") && !word.equals("\n"))
                        fileWriter.append(word + "\n");
                }
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void step2_2() {
        check(filePath3);
        File firstDir = new File(filePath1);
        for (File secondDir : firstDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory() && !pathname.getName().contains("output"))
                    return true;
                else
                    return false;

            }
        })) {
            Set<String> vocabSet = new HashSet<>();
            List<String> vocabList = new ArrayList<>();
            File vocabFile = new File(filePath1 + "/output2/" + secondDir.getName() + ".vocab.txt");
            File outputFile = new File(filePath1 + "/output2/" + secondDir.getName() + ".ap.dat");
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (File article : secondDir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if (pathname.getName().contains("txt"))
                        return true;
                    else
                        return false;
                }
            })) {
                HashSet<String> vocab = new HashSet<>();
                try {
                    InputStreamReader isr = new InputStreamReader(new FileInputStream(article), "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(isr);
                    Map<String, Integer> articleMap = new HashMap<>();
                    String line;
                    int uniqueWordCount = 0;
                    StringBuilder outputString = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
//                        System.out.println(line);
                        String[] words = line.trim().split("\\s{1,}");
                        for (String word : words) {
                            if (word.contains("/") || word.contains("#") || word.contains("\\") ||
                                    word.contains("~") || word.contains("%") || word.contains("&") ||
                                    word.contains("$"))
                                continue;

                            if (articleMap.containsKey(word)) {
                                articleMap.put(word, articleMap.get(word) + 1);
                            } else {
                                articleMap.put(word, 1);
                            }

                            vocab.add(word);
                        }
                    }

                    for (String word : vocab) {
                        if (!vocabSet.contains(word)) {
                            vocabList.add(word);
                            vocabSet.add(word);
                        }
                    }
                    uniqueWordCount = articleMap.keySet().size();
                    outputString.append(uniqueWordCount);

                    for (String word : articleMap.keySet()) {
                        int index = vocabList.indexOf(word);
//                        if (index == vocabList.size() - 1 && vocabList.size() > 8480) {
//                            index = 1;
//                        }
                        int wordCount = articleMap.get(word);

                        outputString.append(" ").append(index + ":" + wordCount);
                    }

                    outputString.append("\n");
                    fileWriter.append(outputString.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileWriter = new FileWriter(vocabFile);
                for (String word : vocabList) {
                    if (!word.equals("") && !word.matches("\\s{1,}") && !word.equals("\n"))
                        fileWriter.append(word + "\n");
                }
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
