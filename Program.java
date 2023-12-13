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
        HashSet<Laptop> catalog = StartCatalog();
        HashSet<Laptop> select;  // Каталог с фильтрами
        Laptop filter = new Laptop();

        String option;
        LinkedList<String> options = new LinkedList<>(Arrays.asList("1", "2", "3", "4", "5", "6"));
        boolean escape = false;
        while (!escape) {
            MainMenu();
            option = sc.nextLine();
            System.out.print("\033[H\033[J");
            if (options.contains(option)) {
                switch (option) {
                    case "1":  // Весь каталог
                        ShowCatalog(catalog);
                        break;
                    case "2":  // Показать фильтры
                        catalog = AddProduct(catalog);
                        break;
                    case "3":  // Каталог с фильтрами
                        select = Selection(catalog, filter);
                        ShowFilter(filter);
                        if (select.size() > 0){
                            System.out.println("Подходящие варианты:");
                            ShowCatalog(select);
                        }
                        else System.out.println("Нет подходящих вариантов.");
                        break;
                    case "4":  // Показать фильтры
                        ShowFilter(filter);
                        break;
                    case "5":  // Настройка фильтров
                        filter = UpdateFilter(filter);
                        break;
                    case "6":  // Выход
                        escape = true;
                        break;
                }
            }
            else{
                System.out.println("Некорректный ввод команды. Повторите попытку.\n");
            }
        }
    }

    private static HashSet<Laptop> CreateRandomSet(int maxAmount){
        ArrayList<String> manufactur = new ArrayList<>(Arrays.asList("ASUS", "HP", "LENOVO", "MSI", "APPLE"));
        ArrayList<Integer> ram = new ArrayList<>(Arrays.asList(8, 16, 32));
        ArrayList<Integer> hdCap = new ArrayList<>(Arrays.asList(256, 512, 1024, 2048));
        ArrayList<String> os = new ArrayList<>(Arrays.asList("LINUX", "WINDOWS"));
        ArrayList<String> color = new ArrayList<>(Arrays.asList("BLACK", "WHITE", "GOLD", "PINK", "GRAY"));

        Random rnd = new Random();
        HashSet<Laptop> laptops = new HashSet<>();
        for (int i = 0; i < maxAmount; i++) {
            Laptop current = new Laptop();
            current.setManufacturer(manufactur.get(rnd.nextInt(manufactur.size())));
            current.setRAM(ram.get(rnd.nextInt(ram.size())).toString());
            current.setHDCap(hdCap.get(rnd.nextInt(hdCap.size())).toString());
            if(current.getManufacturer().equals("APPLE"))
                current.setOS("IOS");
            else
                current.setOS(os.get(rnd.nextInt(os.size())));
            current.setColor(color.get(rnd.nextInt(color.size())));
            laptops.add(current);
        }

        return laptops;
    }

    private static HashSet<Laptop> AddProduct(HashSet<Laptop> catalog){
        Laptop newLaptop = new Laptop();
        String temp;

        System.out.println("Введите наименование производителя");
        System.out.print("-> ");
        temp = sc.nextLine().toUpperCase();
        if (!temp.equals(""))
            newLaptop.setManufacturer(temp);

        System.out.println("Введите объем ОЗУ");
        System.out.print("-> ");
        temp = sc.nextLine();
        if (TryParseInt(temp) != null)
            newLaptop.setRAM(temp);

        System.out.println("Введите объем ЖД");
        System.out.print("-> ");
        temp = sc.nextLine();
        if (TryParseInt(temp) != null)
            newLaptop.setHDCap(temp);
        
        if (!newLaptop.getManufacturer().equals("APPLE")){
            System.out.println("Введите наименование ОС");
            System.out.print("-> ");
            temp = sc.nextLine().toUpperCase();
            if (!temp.equals(""))
                newLaptop.setOS(temp);
        }
        else newLaptop.setOS("IOS");
        

        System.out.println("Введите цвет");
        System.out.print("-> ");
        temp = sc.nextLine().toUpperCase();
        if (!temp.equals(""))
            newLaptop.setColor(temp);

        if (!catalog.contains(newLaptop) && !newLaptop.isEmpty()){
            catalog.add(newLaptop);
            System.out.println("Успешно добавлено.\n");
        }
        else System.out.println("Продукт с указанными характеристиками уже имеется.\n");

        return catalog;
    }

    static Integer TryParseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static HashSet<Laptop> Selection(HashSet<Laptop> set, Laptop standart){
        HashSet<Laptop> select = new HashSet<>();
        for (Laptop laptop : set) {
            if ((standart.getRAM().equals("N/A") || Integer.parseInt(laptop.getRAM()) >= Integer.parseInt(standart.getRAM())) &&
                (standart.getHDCap().equals("N/A") || Integer.parseInt(laptop.getHDCap()) >= Integer.parseInt(standart.getHDCap())) && 
                (standart.getColor().equals("N/A") || laptop.getColor().equals(standart.getColor().toUpperCase())) &&
                (standart.getOS().equals("N/A") || laptop.getOS().equals(standart.getOS().toUpperCase())) && 
                (standart.getManufacturer().equals("N/A") || laptop.getManufacturer().equals(standart.getManufacturer().toUpperCase())))
                    select.add(laptop);
        }

        return select;
    }

    private static Laptop UpdateFilter(Laptop standart){
        String option = "";
        String value;
        LinkedList<String> options = new LinkedList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7"));
        while (true) {
            System.out.print("\033[H\033[J");
            FilterMenu();
            option = sc.nextLine();
            if (!options.contains(option)){
                System.out.println("Некорректный ввод");
                continue;
            }
            if (option.equals("6")){
                System.out.print("\033[H\033[J");
                break;
            }
            if (option.equals("7")){
                System.out.print("\033[H\033[J");
                standart = new Laptop();
                break;
            }
            
            System.out.println("Введите значение: ");
            value = sc.nextLine();
            System.out.println();

            switch (option) {
                case "1":
                    standart.setManufacturer(value);
                    break;
                case "2":
                    if (TryParseInt(value) != null)
                        standart.setRAM(value);
                    else {
                        System.out.println("Некорректное значение. Нажмите Enter для продолжения");
                        sc.nextLine();
                    }
                    break;
                case "3":
                    if (TryParseInt(value) != null)
                        standart.setHDCap(value);
                    else {
                        System.out.println("Некорректное значение. Нажмите Enter для продолжения");
                        sc.nextLine();
                    }
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
        if (filter.isEmpty())
            System.out.println("Фильтры не установлены.\n");
        else System.out.println("Текущие фильтры:\n" + filter.toString() + "\n");
    }

    private static void ShowCatalog(HashSet<Laptop> catalog){
        for (Laptop laptop : catalog) {
            System.out.println(laptop);
        }
        System.out.printf("\nВсего позиций: %d\n\n\n", catalog.size());
    }

    private static void FilterMenu(){
        System.out.println("Выберите параметр:");
        System.out.println("1 - Производитель");
        System.out.println("2 - ОЗУ");
        System.out.println("3 - Объем ЖД");
        System.out.println("4 - Операционная система");
        System.out.println("5 - Цвет");
        System.out.println("6 - Применить фильтры");
        System.out.println("7 - Сбросить фильтры");
        System.out.print("-> ");
    }

    private static void MainMenu(){
        System.out.println("Главное меню.");
        System.out.println("1. Показать весь каталог.");
        System.out.println("2. Добавить новый товар в каталог.");
        System.out.println("3. Показать каталог с фильрами.");
        System.out.println("4. Показать текущие фильтры.");
        System.out.println("5. Настройка фильтров.");
        System.out.println("6. Выход\n");
        System.out.print("Введите номер опции -> ");
    }

    private static HashSet<Laptop> StartCatalog(){
        HashSet<Laptop> catalog = new HashSet<>();

        System.out.print("Желаете создать случайный каталог? (y/n): ");
        String option;
        boolean stupid = true;
        while (stupid) {
            option = sc.nextLine().toLowerCase();
            switch (option) {
                case "y":
                    System.out.print("Введите максимальный размер: ");
                    int size = sc.nextInt();
                    catalog = CreateRandomSet(size);
                    stupid = false;
                    break;
                case "n":
                    stupid = false;
                    break;
                default:
                    System.out.print("Попробуйте еще раз: ");
                    break;
            }
        }
        System.out.print("\033[H\033[J");
        return catalog;
    }
}