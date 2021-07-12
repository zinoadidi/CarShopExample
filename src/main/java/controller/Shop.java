package controller;

import entity.Car;
import entity.CarType;
import entity.User;
import java.util.ArrayList;
import java.util.Scanner;


public class Shop {
  private User user;
  private ArrayList<Car> cars = new ArrayList<>();
  private ArrayList<Sale> salesHistory = new ArrayList<>();
  private Scanner scanner = new Scanner(System.in);

  public void setActiveUser(User activeUser) {
    this.user = activeUser;
  }

  public void addCar() {
    Car car = collectCarInfo();
    cars.add(car);
    showSuccessMessage("Car Added");
  }

  private void showSuccessMessage(String message) {
    System.out.println(message + " Successfully!");
  }

  private void showFailedMessage(String message) {
    System.out.println("Failed! - "+ message);
  }

  private Car collectCarInfo() {
    Car newCar = new Car();
    try {
      System.out.println("===ADD CAR===");
      System.out.println("Enter Car Name:");
      newCar.setName(scanner.nextLine());
      System.out.println("Enter Car Price:");
      newCar.setPrice(Double.parseDouble(scanner.nextLine()));
      System.out.println("Quantity:");
      newCar.setQuantity(Integer.parseInt(scanner.nextLine()));
      System.out.println("Enter Car Type (SEDAN, COUPE, TRUCK):");
      newCar.setCarType(CarType.valueOf(scanner.nextLine().toUpperCase()));
      newCar.setId(generateCarId());
    }catch (Exception exception) {
      exception.printStackTrace();
      collectCarInfo();
    }

    return newCar;
  }

  private int generateCarId() {
    return (cars.size() < 1) ? 0 : cars.get(cars.size() - 1).getId() + 1;
  }

  public void removeCar() {
    System.out.println("Enter car id to remove");
    int carId = Integer.parseInt(scanner.nextLine());
    try {
      this.cars.remove(carId);
      showSuccessMessage("Car Removed");
    }catch (IndexOutOfBoundsException ex){
      System.out.println("Sorry, car not found");
    }
  }

  public void viewCars() {
    System.out.println("Name | \tPrice | \tQuantity | \tType");
    cars.forEach(System.out::println);
  }

  public void viewSalesHistory() {
    System.out.println("Name | \tQuantity | \tAmount");
    for (Sale sale: salesHistory){
      Car car = cars.get(sale.getCarId());
      System.out.println(car.getName() + "\t| " + sale.getQuantity()
          + "\t| " + sale.getTotal());
    }
  }

  public User getActiveUser() {
    return this.user;
  }

  public void sellCar() {
    System.out.println("Enter the id of the car you want to buy");
    int carId = scanner.nextInt();

    Car car = findCarById(carId);
    boolean userCanBuyCar = false;

    try{
      userCanBuyCar = userCanBuyCar(car, user.getBudget());
    }catch (Exception ex){
      System.out.println(ex.getMessage());
    }

    if (userCanBuyCar){
      car.setQuantity(car.getQuantity() - 1);
      addSalesHistory(car.getId(), 1, car.getPrice());
      updateUserBalance(user.getBudget() - car.getPrice());
      updateCar(car);
      showSuccessMessage("Car Purchased");
      return;
    }

    showFailedMessage("Unable to complete car purchase");

  }

  private void updateUserBalance(double newBalance) {
    this.user.setBudget(newBalance);
  }

  private boolean userCanBuyCar(Car car, double budget) throws Exception {
    if (car == null) throw new Exception("Invalid car selection");
    if (budget < car.getPrice()) throw new Exception("You do not have enough money - " + budget);
    if (car.getQuantity() < 1) throw new Exception("Not enough cars left");
    return true;
  }

  private void updateCar(Car car) {
    cars.set(car.getId(), car);
    /*Another way to update array object*/
    /*for (Car car1: this.cars){
      if (car1.getId() == car.getId()){
        car1.setQuantity(car.getQuantity());
        car1.setCarType(car.getCarType());
        car1.setPrice(car.getPrice());
      }
    }*/
  }

  private void addSalesHistory(int carId, int quantity, double total) {
    this.salesHistory.add(new Sale(carId, total, quantity));
  }

  private Car findCarById(int carId) {
    for (Car car: this.cars){
      if (car.getId() == carId) {
        return car;
      }
    }
    return null;
  }
}
