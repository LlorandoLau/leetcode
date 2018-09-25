class MyLinkedList {
    Node head;
    int sum;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        sum = 0;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index > sum - 1) return -1;
        Node temp = head;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node temp = new Node(val);
        if (head == null) head = temp;
        else {
            temp.next = head;
            head = temp;
        }
        sum++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        Node temp = new Node(val);
        Node tail = head;
        if (head == null) head = temp;
        else {
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = temp;
            tail = temp;
        }
        sum++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        Node temp = new Node(val);
        if (index == sum) addAtTail(val);
        else if (index < sum) {
            Node sub = head;
            for (int i = 0; i < index; i++) {
                sub = sub.next;
            }
            temp.next = sub.next;
            sub.next = temp;
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (0 < index && index < sum) {
            Node sub = head;
            for (int i = 0; i < index; i++) {
                sub = sub.next;
            }
            sub.next = sub.next.next;
        }
    }
}