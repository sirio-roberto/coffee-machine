package machine;

class Latte extends CoffeeType {

    @Override
    Integer getRequiredWater() {
        return 350;
    }

    @Override
    Integer getRequiredMilk() {
        return 75;
    }

    @Override
    Integer getRequiredBeans() {
        return 20;
    }

    @Override
    Integer getRequiredPrice() {
        return 7;
    }
}
