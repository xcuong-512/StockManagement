package controller;

import Model.Stock;
import service.StockService;

import java.util.List;
import java.util.Scanner;

public class StockController {

    private StockService stockService;
    private Scanner scanner = new Scanner(System.in);

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    public void start(){
//        scanner.nextLine();
        while(true){
            System.out.println("=====MENU=====");
            System.out.println("1. Show all stocks");
            System.out.println("2. Add stock");
            System.out.println("3. Update price");
            System.out.println("4. Delete stock");
            System.out.println("5. Search stock");
            System.out.println("6. Sort by price");
            System.out.println("0. Exit");
            System.out.println("=====MENU=====");

            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice){
                case 1 -> showAllStocks();
                case 2 -> addStock();
                case 3 -> updateStock();
                case 4 -> deleteStock();
                case 5 -> searchStock();
                case 6 -> sortStock();
                case 0 -> { return; }
            }
        }

    }

    private void showAllStocks(){

        List<Stock> stocks = stockService.getAllStocks();

        if(stocks.isEmpty()){
            System.out.println("Stock list is empty.");
            return;
        }

        printStockTable(stocks);
    }

    private void addStock(){
        System.out.print("Code: ");
        String code = scanner.nextLine();

        System.out.print("Stock name: ");
        String name = scanner.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Sector: ");
        String type = scanner.nextLine();

        stockService.addStock(new Stock(code, name, price, type));
        System.out.println("Stock added successfully!");
    }

    private void updateStock(){
        System.out.print("Stock code: ");
        String code = scanner.nextLine();

        System.out.print("New price: ");
        double price = Double.parseDouble(scanner.nextLine());

        stockService.updateStock(code, price);
    }

    private void deleteStock(){
        System.out.print("Enter stock code: ");
        String code = scanner.nextLine();
        stockService.deleteStock(code);
        System.out.println("Stock deleted successfully!");
    }

    private void searchStock(){
        System.out.println("1. Search by code: ");
        System.out.println("2. Search by name: ");

        int c = Integer.parseInt(scanner.nextLine());

        List<Stock> result;

        if (c == 1){
            System.out.print("Enter stock code: ");
            result = stockService.searchByCode(scanner.nextLine());
        }
        else {
            System.out.print("Enter stock name: ");
            result = stockService.searchByName(scanner.nextLine());
        }
        if (result.isEmpty()) {
            System.out.println("Stock not found.");
        }
        else {
            printStockTable(result);
        }
    }

    private void sortStock(){
        List<Stock> list = stockService.sortByPrice();
        printStockTable(list);
    }

    private void printStockTable(List<Stock> stocks) {

        System.out.println("+----------+------------------------------+------------+-------------+");
        System.out.printf("| %-8s | %-28s | %-10s | %-11s |\n",
                "Code", "Company", "Price", "Sector");
        System.out.println("+----------+------------------------------+------------+-------------+");

        for (Stock s : stocks) {
            System.out.printf("| %-8s | %-28s | %-10.2f | %-11s |\n",
                    s.getCode(),
                    s.getName(),
                    s.getPrice(),
                    s.getType());
        }

        System.out.println("+----------+------------------------------+------------+-------------+");
    }

}
