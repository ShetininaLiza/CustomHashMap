package org.example;

import java.util.HashMap;

//TIP Для <b>запуска</b> кода нажмите <shortcut actionId="Run"/> или
// щелкните значок <icon src="AllIcons.Actions.Execute"/> в боковой области.
public class Main {
    public static void main(String[] args) {

        //TIP Нажмите <shortcut actionId="ShowIntentionActions"/>, поместив каретку на выделенный текст
        // чтобы увидеть, как GIGA IDE предлагает исправить это.
        CustomHashMap<String, String> customHashMap = new CustomHashMap<String, String>();
        customHashMap.put("a", "1");
        customHashMap.put("b", "2");
        customHashMap.put("c", "3");
        customHashMap.put("d", "4");
        customHashMap.put("e", "5");
        customHashMap.put("f", "6");
        customHashMap.put("g", "7");
        customHashMap.put("h", "8");
        customHashMap.put("j", "9");
        customHashMap.put("k", "10");
        customHashMap.put("l", "11");

        customHashMap.printTable();

        System.out.println();
        System.out.println("Value with key c: "+customHashMap.get("c"));
        System.out.println("Value with key d: "+customHashMap.get("d"));
        System.out.println("Value with key e: "+customHashMap.get("e"));
        System.out.println("Value with key vvv: "+customHashMap.get("vvv"));

        System.out.println();
        System.out.println("delete c");
        customHashMap.remove("c");
        customHashMap.printTable();
    }
}