package org.example;

import java.util.Objects;

public class HashNode<K,V> {
    //ключ узла
    private K key;
    //значение узла
    private V value;
    //ссылка на следующий узел
    private HashNode<K,V> next = null;

    HashNode(K _key, V _value){
        key = _key;
        value = _value;
    }
    public int hash(){
        var hashKey = Objects.hashCode(key);
        var hashValue = Objects.hashCode(value);
        int hash = hashKey ^ hashValue;
        return  hash;
    }

    public void printInformation(){
        System.out.println("NODE || Key: "+key+"|| Value: "+value+"|| "+getCountNextNode());
        HashNode node = next;
        while (node!=null){
            System.out.println("NODE || Key: "+node.getKey()+"|| Value: "+node.getValue());
            var buf = node;
            node = buf.getNextNode();
        }
    }

    public boolean hasNextNode(){
        if(next==null)
            return false;
        else
            return true;
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
    public void setNextNode(HashNode value){
        //System.out.println("Add next node");
        next = value;
    }
    public K getKey(){
        return key;
    }
    public V getValue(){
        return value;
    }
}
