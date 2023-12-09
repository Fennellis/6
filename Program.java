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
    private static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        HashSet<Laptop> catalog = CreateRandomSet(100);  // Исходный (случайный) каталог
        HashSet<Laptop> select;  // Каталог с фильтрами
        Laptop filter = new Laptop();

        String option;
        LinkedList<String> options = new LinkedList<>(Arrays.asList("1", "2", "3", "4", "5"));
        boolean escape = false;
        while (!escape) {
            MainMenu();
            option = sc.nextLine();
            System.out.println();
            if (options.contains(option)) {
                switch (option) {
                    case "1":  // Весь каталог
                        ShowCatalog(catalog);
                        System.out.printf("Всего позиций: %d\n\n\n", catalog.size());
                        break;
                    case "2":  // Каталог с фильтрами
                        select = Selection(catalog, filter);
                        ShowFilter(filter);
                        ShowCatalog(select);
                        break;
                    case "3":  // Показать фильтры
                        ShowFilter(filter);
                        break;
                    case "4":  // Настройка фильтров
                        filter = GetStandart(filter);
                        break;
                    case "5":  // Выход
                        escape = true;
                        break;
                }
            }
            else{
                System.out.println("Некорректный ввод команды. Повторите попытку.\n");
            }
        }
    }

    private static HashSet<Laptop> CreateRandomSet(int n){
        ArrayList<String> manufactur = new ArrayList<>(Arrays.asList("ASUS", "HP", "Lenovo", "MSI", "Apple"));
        ArrayList<Integer> ram = new ArrayList<>(Arrays.asList(8, 16, 32));
        ArrayList<Integer> hdCap = new ArrayList<>(Arrays.asList(256, 512, 1024, 2048));
        ArrayList<String> os = new ArrayList<>(Arrays.asList("Linux", "Windows"));
        ArrayList<String> color = new ArrayList<>(Arrays.asList("Black", "White", "Gold", "Pink", "Gray"));

        Random rnd = new Random();
        HashSet<Laptop> laptops = new HashSet<>();
        for (int i = 0; i < n; i++) {
            Laptop current = new Laptop();
            current.setManufacturer(manufactur.get(rnd.nextInt(manufactur.size())));
            current.setRAM(ram.get(rnd.nextInt(ram.size())));
            current.setHDCap(hdCap.get(rnd.nextInt(hdCap.size())));
            if(current.getManufacturer().equals("Apple"))
                current.setOS("IOS");
            else
                current.setOS(os.get(rnd.nextInt(os.size())));
            current.setColor(color.get(rnd.nextInt(color.size())));
            laptops.add(current);
        }

        return laptops;
    }

    private static HashSet<Laptop> Selection(HashSet<Laptop> set, Laptop standart){
        HashSet<Laptop> select = new HashSet<>();
        for (Laptop laptop : set) {
            if (laptop.getRAM() >= standart.getRAM() && laptop.getHDCap() >= standart.getHDCap() && 
                (laptop.getColor().equals(standart.getColor()) || standart.getColor() == null) &&
                (laptop.getOS().equals(standart.getOS()) || standart.getOS() == null) && 
                (laptop.getManufacturer().equals(standart.getManufacturer()) || standart.getManufacturer() == null))
                    select.add(laptop);
        }

        return select;
    }

    private static Laptop GetStandart(Laptop standart){
        String option = "";
        String value;
        LinkedList<String> options = new LinkedList<>(Arrays.asList("1", "2", "3", "4", "5", "6"));
        while (true) {
            SelectionMenu();
            option = sc.nextLine();
            if (!options.contains(option)){
                System.out.println("Некорректный ввод");
                continue;
            }
            if (option.equals("6"))
                break;
            System.out.println("Введите значение: ");
            value = sc.nextLine();

            switch (option) {
                case "1":
                    standart.setManufacturer(value);
                    break;
                case "2":
                    standart.setRAM(Integer.parseInt(value));
                    break;
                case "3":
                    standart.setHDCap(Integer.parseInt(value));
                    break;
                case "4":
                    standart.setOS(value);
                    break;
                case "5":
                    standart.setColor(value);
                    break;
            }
        }
        
        return standart;
    }

    private static void ShowFilter(Laptop filter){
        System.out.println("Текущие фильтры:\n" + filter.toString() + "\n");
    }

    private static void ShowCatalog(HashSet<Laptop> catalog){
        for (Laptop laptop : catalog) {
            System.out.println(laptop);
        }
        System.out.println();
    }

    private static void SelectionMenu(){
        System.out.println("Выберите параметр:");
        System.out.println("1 - Производитель");
        System.out.println("2 - ОЗУ");
        System.out.println("3 - Объем ЖД");
        System.out.println("4 - Операционная система");
        System.out.println("5 - Цвет");
        System.out.println("6 - Применить фильтры");
        System.out.print("-> ");
    }

    private static void MainMenu(){
        System.out.println("Главное меню.");
        System.out.println("1. Показать весь каталог.");
        System.out.println("2. Показать каталог с фильрами.");
        System.out.println("3. Показать текущие фильтры.");
        System.out.println("4. Настройка фильтров.");
        System.out.println("5. Выход\n");
        System.out.print("Введите номер опции -> ");
    }
}