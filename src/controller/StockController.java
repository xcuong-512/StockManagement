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
            System.out.println("1. Hiển thị toàn bộ mã");
            System.out.println("2. Thêm cổ phiếu");
            System.out.println("3. Cập nhật giá");
            System.out.println("4. Xóa mã cổ phiếu");
            System.out.println("5. Tìm kiếm cổ phiếu");
            System.out.println("6. Sắp xếp theo giá");
            System.out.println("0. Thoát");
            System.out.println("=====MENU=====");

            System.out.println("Nhâp lựa chọn: ");
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
            System.out.println("Danh sách cổ phiếu trống.");
            return;
        }

        printStockTable(stocks);
    }

    private void addStock(){
        System.out.print("Code: ");
        String code = scanner.nextLine();

        System.out.print("Tên cổ phiếu: ");
        String name = scanner.nextLine();

        System.out.print("Đơn giá: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Nhóm Ngành: ");
        String type = scanner.nextLine();

        stockService.addStock(new Stock(code, name, price, type));
        System.out.println("Đã thêm cổ phiếu!");
    }

    private void updateStock(){
        System.out.print("Mã cổ phiếu: ");
        String code = scanner.nextLine();

        System.out.print("Giá mới: ");
        double price = Double.parseDouble(scanner.nextLine());

        stockService.updateStock(code, price);
    }

    private void deleteStock(){
        System.out.print("Nhập mã cổ phiếu: ");
        String code = scanner.nextLine();
        stockService.deleteStock(code);
        System.out.println("Đã xóa thành công cổ phiếu");
    }

    private void searchStock(){
        System.out.println("1. Tìm bằng mã: ");
        System.out.println("2. Tìm bằng tên: ");

        int c = Integer.parseInt(scanner.nextLine());

        List<Stock> result;

        if (c == 1){
            System.out.println("Nhập mã cổ phiếu: ");
            result = stockService.searchByCode(scanner.nextLine());
        }
        else {
            System.out.println("Nhập tên cổ phiếu: ");
            result = stockService.searchByName(scanner.nextLine());
        }
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy cổ phiếu.");
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
                "Mã CP", "Công ty", "Giá", "Nhóm Ngành");
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
