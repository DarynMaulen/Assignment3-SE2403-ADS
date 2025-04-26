package models;

import java.util.Iterator;
import java.util.Stack;

public class BST <K extends Comparable<K>,V> implements Iterable<BST<K, V>.NodeWrapper>{
    private Node root;
    private int size=0;

    private class Node {
        K key;
        V value;
        Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public class NodeWrapper {
        private final K key;
        private final V value;

        public NodeWrapper(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
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
        if(current==null){
            return;
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
        min.left=node.left;
        return min;
    }


    @Override
    public Iterator<NodeWrapper> iterator() {
        return new BSTIterator();
    }

    // Логика обхода in-order
    private class BSTIterator implements Iterator<NodeWrapper> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public NodeWrapper next() {
            Node node = stack.pop();
            pushLeft(node.right);
            return new NodeWrapper(node.key, node.value);
        }
    }
}
