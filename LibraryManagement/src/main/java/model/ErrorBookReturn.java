/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class ErrorBookReturn {
    private ErrorBook errorBook;
    private int price;

    public ErrorBookReturn() {
    }

    public ErrorBookReturn(ErrorBook errorBook, int price) {
        this.errorBook = errorBook;
        this.price = price;
    }

    public ErrorBook getErrorBook() {
        return errorBook;
    }

    public void setErrorBook(ErrorBook errorBook) {
        this.errorBook = errorBook;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
