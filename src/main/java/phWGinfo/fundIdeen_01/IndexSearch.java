package phWGinfo.fundIdeen_01_SimpleSearch;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IndexSearch {

    static char[] c = new char[512];
    static int cursor = 0, max=0;
    static Reader reader;


    public static void main(String[] args) throws Throwable {

        long anfangsZeit = System.currentTimeMillis();
        Map<String, List<Integer>> index = new HashMap<String, List<Integer>>();


        BufferedReader reader = new BufferedReader(new FileReader(new File(new File("data"), "text-43M.txt")));
        int position=0;

        String line = reader.readLine();//nextWord();
        while(line!=null) {
            if(position % 100000==0) {
                System.out.println("Indexing line " + position + " (index has "+index.size()+" terms).");
                System.gc();
            }
            for(String wort: line.split(" ")) {
                if(index.containsKey(wort)) {
                    List<Integer> positionen = index.get(wort);
                    positionen.add(position);
                } else {
                    List<Integer> positionen = new LinkedList<Integer>();
                    index.put(wort, positionen);
                    positionen.add(position);
                }
            }
            position = position+1;
            line = reader.readLine();//nextWord();
        }

        long indexEndeZeit = System.currentTimeMillis();
        System.out.println("Index gebaut in " + ((indexEndeZeit-anfangsZeit)/1000) + " Sekunden.");

        System.out.println("Has William? ");

        System.out.println(index.get("William"));

        System.out.println("Gesucht in " + ((System.currentTimeMillis()-indexEndeZeit)/1000) + " Sekunden.");

    }

}
