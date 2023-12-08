// Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
// Создать множество ноутбуков.
// Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру.
// Критерии фильтрации можно хранить в Map.
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
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        HashSet<Laptop> set = CreateRandomSet(100);
        HashSet<Laptop> select = Selection(set);

        System.out.println("Все ноутбуки");
        for (Laptop laptop : set) {
            System.out.println(laptop);
        }
        System.out.println("Выборка");
        for (Laptop laptop : select) {
            System.out.println(laptop);
        }
    }

    private static HashSet<Laptop> CreateRandomSet(int n){
        ArrayList<Integer> ram = new ArrayList<>(Arrays.asList(8, 16, 32));
        ArrayList<Integer> hdCap = new ArrayList<>(Arrays.asList(256, 512, 1024, 2048));
        ArrayList<String> os = new ArrayList<>(Arrays.asList("Linux", "Windows", "IOS"));
        ArrayList<String> color = new ArrayList<>(Arrays.asList("Black", "White", "Gold", "Pink", "Gray"));

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

    private static HashSet<Laptop> Selection(HashSet<Laptop> set){
        Laptop standart = GetStandart();
        System.out.println(standart.toString());
        HashSet<Laptop> select = new HashSet<>();
        for (Laptop laptop : set) {
            if (laptop.ram >= standart.ram && laptop.hdCap >= standart.hdCap && 
                (laptop.color.equals(standart.color) || standart.color == null) &&
                (laptop.os.equals(standart.os) || standart.os == null)){
                select.add(laptop);
            }
        }

        return select;
    }

    private static Laptop GetStandart(){
        Scanner sc = new Scanner(System.in);
        String option = "";
        String value;
        LinkedList<String> options = new LinkedList<>(Arrays.asList("1", "2", "3", "4", "5"));
        Laptop standart = new Laptop();
        while (true) {
            Menu();
            option = sc.nextLine();
            if (!options.contains(option)){
                System.out.println("Некорректный ввод");
                continue;
            }
            if (option.equals("5"))
                break;
            System.out.println("Введите значение: ");
            value = sc.nextLine();

            switch (option) {
                case "1":
                    standart.ram = Integer.parseInt(value);
                    break;
                case "2":
                    standart.hdCap= Integer.parseInt(value);
                    break;
                case "3":
                    standart.os = value;
                    break;
                case "4":
                    standart.color = value;
                    break;
            }
        }

        return standart;
    }

    private static void Menu(){
        System.out.println("Выберите параметр:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("5 - Применить фильтры");
        System.out.print("-> ");
    }
}