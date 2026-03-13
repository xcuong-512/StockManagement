package repository;

import Model.Stock;

import java.util.List;

public interface StockRepository {
    List<Stock> load();
    void save(List<Stock> stocks);
}
