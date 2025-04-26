package models;

public class MyHashTable<K,V> {

    private class HashNode<K,V>{
        private K key;
        private V value;
        private HashNode<K,V> next;

        public HashNode(K key,V value){
            this.key=key;
            this.value=value;
            this.next=null;
        }

        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K,V>[] chainArray;
    private int M ;
    private int size;

    public MyHashTable(){
        this.M=11;
        chainArray=new HashNode[M];
        size=0;
    }

    public MyHashTable(int M){
        this.M=M;
        chainArray=new HashNode[M];
        size=0;
    }

    public int hash(K key){
        return Math.abs(key.hashCode()) % M;
    }

    public void put(K key,V value){
        int index = hash(key);

        HashNode<K,V> current = chainArray[index];
        while (current!=null){
            if(current.key.equals(key)){
                current.value=value;
                return;
            }
            current=current.next;
        }

        HashNode<K,V> newNode = new HashNode<>(key, value);
        newNode.next=chainArray[index];
        chainArray[index]=newNode;
        size++;
    }

    public V get(K key){
        int index = hash(key);
        HashNode<K,V> current = chainArray[index];
        while (current!=null){
            if(current.key.equals(key)){
                return current.value;
            }
            current=current.next;
        }
        return null;
    }

    public V remove(K key){
        int index = hash(key);
        HashNode<K,V> current = chainArray[index];
        HashNode<K,V> prev = null;
        while (current!=null){
            if(current.key.equals(key)){
                if(prev==null){
                    chainArray[index]=current.next;
                }else{
                    prev.next=current.next;
                }
                size--;
                return current.value;
            }
            prev=current;
            current=current.next;
        }
        return null;
    }

    public boolean contains(V value){
        for(int i =0;i<M;i++){
            HashNode<K,V> current = chainArray[i];
            while (current!=null){
                if(current.value.equals(value)){
                    return true;
                }
                current=current.next;
            }
        }
        return false;
    }

    public K getKey(V value){
        for(int i =0;i<M;i++){
            HashNode<K,V> current = chainArray[i];
            while (current!=null){
                if(current.value.equals(value)){
                    return current.key;
                }
                current=current.next;
            }
        }
        return null;
    }

    public void printBucketSizes(){
        for (int i =0;i<M;i++){
            int count = 0;
            HashNode<K,V> current = chainArray[i];
            while (current!=null){
                count++;
                current=current.next;
            }
            System.out.println("Bucket" + i + ": " + count + "elements");
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

}
