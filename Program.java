// Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
// Создать множество ноутбуков.
// Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map.
// Например:
// “Введите цифру, соответствующую необходимому критерию:
// 1 - ОЗУ
// 2 - Объем ЖД
// 3 - Операционная система
// 4 - Цвет …
// Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
// Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Program {

    public static void main(String[] args) {
        HashSet<Laptop> set = CreateRandomSet(10);

        for (Laptop laptop : set) {
            System.out.println(laptop);
        }
    }

    private static HashSet<Laptop> CreateRandomSet(int n){
        ArrayList<Integer> ram = new ArrayList<>(Arrays.asList(8, 16));
        ArrayList<Integer> hdCap = new ArrayList<>(Arrays.asList(512, 1024, 2048));
        ArrayList<String> os = new ArrayList<>(Arrays.asList("Linux", "Windows"));
        ArrayList<String> color = new ArrayList<>(Arrays.asList("Black", "White", "Gold"));

        Random rnd = new Random();
        HashSet<Laptop> laptops = new HashSet<>();
        for (int i = 0; i < n; i++) {
            Laptop current = new Laptop();
            current.model = Integer.toString(i);
            current.ram = ram.get(rnd.nextInt(ram.size()));
            current.hdCap = hdCap.get(rnd.nextInt(hdCap.size()));
            current.os = os.get(rnd.nextInt(os.size()));
            current.color = color.get(rnd.nextInt(color.size()));
            laptops.add(current);
        }

        return laptops;
    }
}