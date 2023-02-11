package main;

import main.model.Doing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {
    private static int currentId = 1;
    private static HashMap<Integer, Doing> doings = new HashMap<>();

    public static List<Doing> getAllDoings() {
        ArrayList<Doing> todoList = new ArrayList<>();
        todoList.addAll(doings.values());
        return todoList;
    }

    public synchronized static int addDoing(Doing doing) {
        int id = currentId++ ;
        doing.setId(id);
        doings.put(id, doing);
        return id;
    }

    public static Doing getDoing(int id) {
        if (doings.containsKey(id)) {
            return doings.get(id);
        }
        return null;
    }

    public synchronized static int deleteDoing(int id) {
        if (doings.containsKey(id)) {
            doings.remove(id);
            return id;
        }
        return 0;
    }

    public static void deleteAllDoings() {
        doings.clear();
    }

    public static synchronized int editDoing(int id, String doing, String who) {
        if (doings.containsKey(id)) {
            Doing todo = doings.get(id);
            todo.setDoing(doing);
            todo.setWho(who);
            return id;
        }
        return 0;
    }
}
