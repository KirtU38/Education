package core;

public class Car {

    // Поменял доступ на private, слышал это хорошая практика
    private String number; // Переменная типа String
    private int height; // Переменная типа int
    private double weight; // Переменная типа double
    private boolean hasVehicle; // Переменная типа boolean
    private boolean isSpecial; // Переменная типа boolean

    public String toString() {

        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : ""; // Переменная типа String
        return "\n=========================================\n" +
                special + "Автомобиль с номером " + number +
                ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isHasVehicle() {
        return hasVehicle;
    }

    public void setHasVehicle(boolean hasVehicle) {
        this.hasVehicle = hasVehicle;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public void setSpecial(boolean special) {
        isSpecial = special;
    }
}