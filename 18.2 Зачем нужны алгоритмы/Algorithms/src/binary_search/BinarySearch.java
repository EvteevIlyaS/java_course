package binary_search;

import java.util.ArrayList;

public class BinarySearch {
    private ArrayList<String> list;

    public BinarySearch(ArrayList<String> list) {
        this.list = list;
    }

    public int search(String query) {
        return search(query, 0, list.size() - 1);
    }

    private int search(String query, int from, int to) {
        int mid = (from + to) / 2;
        if (query.compareTo(list.get(mid)) == 0) {
            return mid;
        }
        if (from == mid) {
            if (query.compareTo(list.get(to)) == 0) {
                return to;
            }
            else if (query.compareTo(list.get(from)) == 0) {
                return from;
            }
                return -1;
        }
        if (query.compareTo(list.get(mid)) < 0) {
            return search(query, from, mid);
        }
        if (query.compareTo(list.get(mid)) > 0) {
            System.out.println(from + " " + mid + " " + to + " " + list.get(mid));
            return search(query, mid, to);
        }
        return -1;
    }
}
