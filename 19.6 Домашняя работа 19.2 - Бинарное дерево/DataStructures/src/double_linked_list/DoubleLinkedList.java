package double_linked_list;

public class DoubleLinkedList {
    private ListItem head;
    private ListItem tail;

    public ListItem getHeadElement() {
        return head;
    }

    public ListItem getTailElement() {
        return tail;
    }

    public ListItem popHeadElement() {
        ListItem item = head;
        if (head != null) {
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
                item.setNext(null);
            }
        }
        return item;
    }

    public ListItem popTailElement() {
        ListItem item = tail;
        if (tail != null) {
            tail = tail.getPrev();
            if (tail != null) {
                tail.setNext(null);
                item.setPrev(null);
            }
        }
        return item;
    }

    public void removeHeadElement() {
        if (head != null) {
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
            }
        }
    }

    public void removeTailElement() {
        if (tail != null) {
            tail = tail.getPrev();
            if (tail != null) {
                tail.setNext(null);
            }
        }
    }

    public void addToHead(ListItem item) {
        if (head == null & tail == null) {
            tail = item;
        } else {
            item.setNext(head);
            head.setPrev(item);
        }
        head = item;
    }

    public void addToTail(ListItem item) {
        if (head == null & tail == null) {
            head = item;
        } else {
            item.setPrev(tail);
            tail.setNext(item);
        }
        tail = item;
    }
}