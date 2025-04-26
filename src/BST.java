import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class BST <K extends Comparable<K>,V>{
    private Node root;
    private int size=0;

    private class Node{
        private K key;
        private V value;
        private Node right,left;
        public Node(K key,V value){
            this.key=key;
            this.value=value;
            right=null;
            left=null;
        }
    }

    private class Entry implements Map.Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }


    public int size(){
        return size;
    }

    public void put(K key,V value){
        if(root == null){
            root = new Node(key,value);
            size++;
            return;
        }
        Node current = root;
        Node parent = null;

        while (current!=null){
            parent=current;
            int cmp = (key.compareTo(current.key));
            if(cmp<0){
                current=current.left;
            } else if (cmp>0) {
                current=current.right;
            }else{
                current.value=value;
                return;
            }
        }

        int cmp = (key.compareTo(parent.key));
        if (cmp<0){
            parent.left = new Node(key, value);
        }else{
            parent.right = new Node(key, value);
        }
        size++;
    }

    public V get(K key){
        Node current = root;
        while (current!=null){
            int cmp = (key.compareTo(current.key));
            if(cmp<0){
                current=current.left;
            } else if (cmp>0) {
                current=current.right;
            }else{
                return current.value;
            }
        }
        return null;
    }

    public void delete(K key){
        Node current = root;
        Node parent = null;
        boolean isLeftChild = false;

        while (current!=null){
            int cmp = (key.compareTo(current.key));
            if (cmp==0){break;}
            parent=current;
            if(cmp<0){
                current=current.left;
                isLeftChild=true;
            } else {
                current=current.right;
                isLeftChild=false;
            }
        }

        if(current==null){
            return;
        }

        if(current.right==null && current.left==null){
            if(current==root){
                root=null;
            } else if (isLeftChild) {
                parent.left=null;
            }else {
                parent.right=null;
            }

        } else if (current.left==null) {
            if(current==root){
                root=current.right;
            } else if (isLeftChild) {
                parent.left=current.right;
            }else {
                parent.right=current.right;
            }
        }

        else if (current.right==null){
            if(current==root){
                root=current.left;
            } else if (isLeftChild) {
                parent.left=current.left;
            }else {
                parent.right=current.left;
            }
        }

        else {
            Node min = minValue(current);
            if(current==root){
                root=min;
            } else if (isLeftChild) {
                parent.left=min;
            }else {
                parent.right=min;
            }
            min.left=current.left;
        }
        size--;
    }

    private Node minValue(Node node){
        Node parentOfMin = node;
        Node min = node.right;
        while (min.left!=null){
            parentOfMin=min;
            min=min.left;
        }

        if(min!=node.right){
            parentOfMin.left=min.right;
            min.right=node.right;
        }
        return min;
    }

    public Iterator<Map.Entry<K,V>> iterator() {
        return new Iterator<Map.Entry<K,V>>() {
            private Stack<Node> stack = new Stack<>();

            {
                Node current = root;
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public Map.Entry<K,V> next() {
                Node node = stack.pop();
                K key = node.key;
                V value = node.value;

                Node current = node.right;
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }

                return new Entry(key, value);
            }
        };
    }


}
