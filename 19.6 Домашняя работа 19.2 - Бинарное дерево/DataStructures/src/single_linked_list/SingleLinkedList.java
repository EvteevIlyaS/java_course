package single_linked_list;

public class SingleLinkedList {
    private ListItem top;

    public void push(ListItem item) {
        if (top != null) {
            item.setNext(top);
        }
        top = item;
    }

    public ListItem pop() {
        ListItem item = top;
        if (top != null) {
            top = top.getNext();
            item.setNext(null);
        }
        return item;
    }

    public void removeTop() {
        if (top != null) {
            top = top.getNext();
        }
    }

    public void removeLast() {
        ListItem item = top;
        ListItem tmpItem;
        if (top != null)
            if (top.getNext() == null) {
                top = null;
                return;
            }
            while (true) {
                tmpItem = item;
                item = item.getNext();
                if (item.getNext() == null) {
                    tmpItem.setNext(null);
                    return;
                }
            }
    }
}