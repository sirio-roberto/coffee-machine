package machine;

class Espresso extends CoffeeType {

    @Override
    Integer getRequiredWater() {
        return 250;
    }

    @Override
    Integer getRequiredMilk() {
        return 0;
    }

    @Override
    Integer getRequiredBeans() {
        return 16;
    }

    @Override
    Integer getRequiredPrice() {
        return 4;
    }
}
