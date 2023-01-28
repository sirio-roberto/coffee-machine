package machine;

import java.util.Scanner;

import static machine.MachineState.*;

public class CoffeeMachineEntity {
    final static Scanner scan = new Scanner(System.in);

    private Integer remainingWater;
    private Integer remainingMilk;
    private Integer remainingCoffeeBeans;
    private Integer disposableCups;
    private Integer money;

    private MachineState state;

    public CoffeeMachineEntity() {
    }

    public void addWater(Integer remainingWater) {
        this.remainingWater += remainingWater;
    }

    public void addMilk(Integer remainingMilk) {
        this.remainingMilk += remainingMilk;
    }

    public void addCoffeeBeans(Integer remainingCoffeeBeans) {
        this.remainingCoffeeBeans += remainingCoffeeBeans;
    }

    public void addDisposableCups(Integer disposableCups) {
        this.disposableCups += disposableCups;
    }

    public Integer getMoney() {
        return money;
    }

    public void takeMoney() {
        System.out.println("I gave you $" + getMoney());
        this.money = 0;
        this.state = WAITING_USER_INPUT;
    }

    public void start() {
        this.remainingWater = 400;
        this.remainingMilk = 540;
        this.remainingCoffeeBeans = 120;
        this.disposableCups = 9;
        this.money = 550;
        this.state = STARTING;
        while (this.state != EXITING) {
            handleState();
        }
    }

    private void handleState() {
        switch (this.state) {
            case STARTING, WAITING_USER_INPUT -> {
                System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                String action = scan.nextLine();
                this.state = fromValue(action);
                System.out.println();
            }
            case SHOWING_INFO -> {
                System.out.println(this);
                this.state = WAITING_USER_INPUT;
            }
            case BUYING -> buyCoffee();
            case FILLING -> fillMachine();
            case TAKING_MONEY -> takeMoney();
        }
    }

    public void buyCoffee() {
        this.state = WAITING_USER_INPUT;
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String stringOption = scan.nextLine();
        if (stringOption.equals("back")) {
            return;
        }
        int option = Integer.parseInt(stringOption);
        CoffeeType coffeeType;
        if (option == 1) {
            coffeeType = new Espresso();
        } else if(option == 2) {
            coffeeType = new Latte();
        } else {
            coffeeType = new Cappuccino();
        }

        boolean hasResources = checkResources(coffeeType);
        if (hasResources) {
            this.remainingWater -= coffeeType.getRequiredWater();
            this.remainingMilk -= coffeeType.getRequiredMilk();
            this.remainingCoffeeBeans -= coffeeType.getRequiredBeans();
            this.disposableCups--;
            this.money += coffeeType.getRequiredPrice();
        }
    }

    private boolean checkResources(CoffeeType coffeeType) {
        if (coffeeType.getRequiredWater() > this.remainingWater) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (coffeeType.getRequiredMilk() > this.remainingMilk) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        if (coffeeType.getRequiredBeans() > this.remainingCoffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        }
        if (this.disposableCups == 0) {
            System.out.println("Sorry, not enough cups!");
            return false;
        }
        System.out.println("I have enough resources, making you a coffee!");
        return true;
    }

    private void fillMachine() {
        System.out.println("Write how many ml of water you want to add:");
        addWater(Integer.parseInt(scan.nextLine()));
        System.out.println("Write how many ml of milk you want to add:");
        addMilk(Integer.parseInt(scan.nextLine()));
        System.out.println("Write how many grams of coffee beans you want to add:");
        addCoffeeBeans(Integer.parseInt(scan.nextLine()));
        System.out.println("Write how many disposable cups you want to add:");
        addDisposableCups(Integer.parseInt(scan.nextLine()));
        this.state = WAITING_USER_INPUT;
    }

    @Override
    public String toString() {
        return String.format("""
                        The coffee machine has:
                        %d ml of water
                        %d ml of milk
                        %d g of coffee beans
                        %d disposable cups
                        $%d of money""", remainingWater, remainingMilk, remainingCoffeeBeans, disposableCups, money);
    }
}

