package service;

import Model.Stock;
import repository.StockRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StockService {
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    public void addStock(Stock stock){
        List<Stock> stocks = stockRepository.load();
        stocks.add(stock);
        stockRepository.save(stocks);
    }

    public void updateStock(String code, double price){
        List<Stock> stocks = stockRepository.load();
        for (Stock s : stocks){
            if (s.getCode().equals(code)) {
                s.setPrice(price);
            }
        }
        stockRepository.save(stocks);
    }

    public void deleteStock(String code){
        List<Stock> stocks = stockRepository.load();
        stocks.removeIf(s -> s.getCode().equals(code));
        stockRepository.save(stocks);
    }

    public List<Stock> searchByCode(String code){
        return stockRepository.load().stream().filter(s -> s.getCode().equals(code))
                .collect(Collectors.toList());
    }

    public List<Stock> searchByName(String name){
        return stockRepository.load().stream().filter(s -> s.getName().equals(name)).
                collect(Collectors.toList());
    }

    public List<Stock> searchByPrice(double price){
        return stockRepository.load().stream().filter(s -> s.getPrice() == price)
                .collect(Collectors.toList());
    }

    public List<Stock> sortByPrice(){
        return stockRepository.load().stream().sorted(Comparator.comparingDouble(Stock::getPrice))
                .collect(Collectors.toList());
    }

    public List<Stock> sortByType(){
        return stockRepository.load().stream().sorted(Comparator.comparing(Stock::getType))
                .collect(Collectors.toList());
    }

    public List<Stock> getAllStocks(){
        return stockRepository.load();
    }
}
