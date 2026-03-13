package Model;

import java.util.List;

public class Stock {
    private String name;
    private String code;
    private double price;
    private String type;

    public Stock(){};

    public Stock(String code, String name, double price, String type){
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCode(){
        return this.code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return String.format("%-10s %-30s %-12.2f %-15s \r\n",
                code, name, price, type);
    }
}
