package rabin_karp;

import java.util.*;
import java.util.stream.Collectors;

public class RabinKarpExtended {
    private String text;
    private final TreeMap<Integer, ArrayList<Integer>> number2position;
    Set<Character> characterSet;

    public RabinKarpExtended(String text) {
        this.text = text;
        number2position = new TreeMap<>();
        createIndex();
    }

    public List<Integer> search(String query) {
        ArrayList<Integer> indices = new ArrayList<>();
        int queryIntFormat;
        int i = 0;

        for (char el :
                characterSet) {
            query = query.replace(el, String.valueOf(i).charAt(0));
            i++;
        }
        queryIntFormat = Integer.parseInt(query);
//        System.out.println(queryIntFormat);

        if (number2position.containsKey(queryIntFormat)) {
            return number2position.get(queryIntFormat);
        }

        for (int j = 0; j < text.length() - query.length(); j++) {
            if (Integer.parseInt(text.substring(j, j + query.length())) == queryIntFormat) {
                indices.add(j);
            }
        }
        number2position.put(queryIntFormat, indices);
        return indices;
    }

    private void createIndex() {
        characterSet = text.chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
        int i = 0;
        if (characterSet.size() > 9) {
            throw new IllegalArgumentException();
        }
        for (char el :
                characterSet) {
            text = text.replace(el, String.valueOf(i).charAt(0));
            i++;
        }
//        System.out.println(text);
    }
}