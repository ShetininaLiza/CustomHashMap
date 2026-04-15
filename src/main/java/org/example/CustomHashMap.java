package org.example;

import java.util.Arrays;
import java.util.Objects;


public class CustomHashMap <K, V> {
    //сам список
    private HashNode<K,V>[] table;
    //Коэффициент загрузки для хэш-таблицы
    //т.е то значение, после, которого происходит переопределение (увеличение) таблицы
    double load;
    CustomHashMap(){
        int baseSize = 15;
        table = new HashNode[baseSize];
        load = 0.75f;
    }
    //метод для добавления
    public void put(K key, V value){
        //сперва проверяем надо ли увеличивать размер
        if(checkResize()){
            resize();
        }
        HashNode node = new HashNode(key, value);
        var hash = key.hashCode();
        //определяем индекс
        int index = (table.length-1) & hash;

        if(index<table.length){
            //если по индексу ничего не лежит, то добавляем узел
            if(table[index]==null){
                table[index] = node;
            }else{
                //если в корзине (по индексу) что-то лежит, т.е возникла коллизия,
                //то решаем ее с помощью метода цепочек (в корзине находится связный список)
                HashNode bufNode = table[index];
                while (bufNode.hasNextNode()){
                    bufNode = bufNode.getNextNode();
                }
                bufNode.setNextNode(node);
            }
        }
    }
    //метод для печати таблицы (вспомогательный метод)
    public void printTable(){
        for(int i=0;i<table.length; i++){
            if(table[i]!=null) {
                System.out.println("PrintTable || Index: "+i);
                table[i].printInformation();
            }
        }
    }
    //метод для получения записи
    public V get(K key){
        var hash = key.hashCode();
        //определяем индекс
        int index = (table.length-1) & hash;
        //System.out.println("GET || Key: "+key+" || index: "+index);
        //в таблице нет такого индекса
        if(table[index]==null){
            return null;
        }else{
            var node = table[index];
            if(node!=null) {
                var keyNode = node.getKey();
                //если значение одно единственное
                if (!node.hasNextNode()) {
                    //если ключи точно совпали
                    if (Objects.equals(keyNode, key)) {
                        return node.getValue();
                    } else {
                        return null;
                    }
                } else {
                    HashNode<K,V>findNode=null;
                    while (node!=null){
                        keyNode = node.getKey();
                        if(Objects.equals(keyNode, key)){
                            findNode = node;
                            break;
                        }else {
                            var buf = node;
                            node = buf.getNextNode();
                        }
                    }
                    if(findNode!=null)
                        return findNode.getValue();
                    else
                        return null;
                }
            }else{
                return null;
            }
        }
    }
    public void remove (K deleteKey) {
        var hash = deleteKey.hashCode();
        //определяем индекс
        int index = (table.length - 1) & hash;
        var node = table[index];
        if (node != null) {
            /*
            var node = table[index];
            var keyNode = node.getKey();
            //если значение одно единственное
            if(!node.hasNextNode()){
                if(Objects.equals(keyNode, deleteKey)){
                    table[index]=null;
                }
            }else{
                HashNode previous = null;
                HashNode nextNode = null;
                HashNode findNode = null;

                //пока не найдем нужный узел
                while (!Objects.equals(keyNode, deleteKey)){
                    findNode = node;
                    node = findNode.getNextNode();
                    keyNode = node.getKey();
                }

                System.out.println("Del");
                findNode.printInformation();
            }
            */

                var keyNode = node.getKey();
                //если значение одно единственное
                if (!node.hasNextNode()) {
                    //если ключи точно совпали
                    if (Objects.equals(keyNode, deleteKey)) {
                        table[index] = null;
                    }
                } else {
                    //если значений несколько
                    HashNode previous = null;
                    HashNode<K, V> findNode = null;
                    while (node != null) {
                        keyNode = node.getKey();
                        if (Objects.equals(keyNode, deleteKey)) {
                            findNode = node;
                            break;
                        } else {
                            var buf = node;
                            previous = buf;
                            node = buf.getNextNode();
                        }
                    }

                    if (findNode != null) {
                        if (previous != null) {
                            previous.setNextNode(findNode.getNextNode());
                            findNode = null;
                        }
                    }

                }

        }
    }
    private void resize(){
        var buf = table.clone();
        int newSize = table.length*2;
        table = new HashNode[newSize];
        for (HashNode node : buf) {
            if (node != null) {
                var hash = node.hashCode();
                //определяем индекс
                int index = (newSize - 1) & hash;
                table[index] = node;
            }
        }
    }
    private boolean checkResize(){
        float count = Arrays.stream(table).filter(Objects::nonNull).count();
        float currentLoad =  count/ table.length;
        return currentLoad >= load;
    }
}
