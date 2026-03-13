package repository;

import Model.Stock;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonStockRepository implements StockRepository{

    private final String FILE = "src/data/stock.json";

    @Override
    public List<Stock> load() {
        List<Stock> stocks = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE))){
            File file = new File(FILE);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            String line;
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                stocks.add(new Stock(
                        data[0], data[1], Double.parseDouble(data[2]), data[3]
                ));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return stocks;
    }

    @Override
    public void save(List<Stock> stocks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))){
            for(Stock stock : stocks){
                writer.write(
                        stock.getCode() + "," + stock.getName() + "," + stock.getPrice() + "," + stock.getType()
                );
                writer.newLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
