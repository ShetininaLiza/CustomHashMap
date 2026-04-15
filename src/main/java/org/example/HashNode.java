package org.example;

import java.util.Objects;

public class HashNode<K,V> {
    //ключ узла
    private K key;
    //значение узла
    private V value;
    //ссылка на следующий узел
    private HashNode<K,V> next = null;
    //ссылка на предыдущий
    private HashNode<K,V> prev = null;

    HashNode(K _key, V _value){
        key = _key;
        value = _value;
    }
    public int hash(){
        var hashKey = Objects.hashCode(key);
        var hashValue = Objects.hashCode(value);
        return  hashKey ^ hashValue;
    }

    public void printInformation(){
        System.out.println("NODE || Key: "+key+"|| Value: "+value+"|| Count next node: "+getCountNextNode());
        HashNode node = next;
        while (node!=null){
            System.out.println("NODE || Key: "+node.getKey()+"|| Value: "+node.getValue());
            var buf = node;
            node = buf.getNextNode();
        }
    }

    public boolean hasPrevNode(){
        return prev != null;
    }

    public boolean hasNextNode(){
        return next != null;
    }
    private int getCountNextNode(){
        int count = 0;
        var node = next;
        while (node!=null){
            count++;
            var buf = node;
            node = buf.getNextNode();
        }
        return count;
    }
    public HashNode getNextNode(){
        return next;
    }
    public HashNode getPrevNode(){return prev;}
    public void setNextNode(HashNode value){
        //System.out.println("Add next node");
        next = value;
    }
    public void setPrevNode(HashNode value){
        prev = value;
    }
    public K getKey(){
        return key;
    }
    public V getValue(){
        return value;
    }
}
