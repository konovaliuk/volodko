package com.grubjack.cinema.model;

import java.io.Serializable;
import java.util.Objects;

public class Ticket implements Serializable {
    private int id;
    private int line;
    private int seat;
    private int price;
    private boolean sold;

    public Ticket() {
    }

    public Ticket(boolean sold) {
        this.sold = sold;
    }

    public Ticket(int line, int seat, int price) {
        this.line = line;
        this.seat = seat;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return line == ticket.line &&
                seat == ticket.seat &&
                price == ticket.price &&
                sold == ticket.sold;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, seat, price, sold);
    }
}
