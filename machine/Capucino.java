package machine;

class Cappuccino extends CoffeeType {

    @Override
    Integer getRequiredWater() {
        return 200;
    }

    @Override
    Integer getRequiredMilk() {
        return 100;
    }

    @Override
    Integer getRequiredBeans() {
        return 12;
    }

    @Override
    Integer getRequiredPrice() {
        return 6;
    }
}
