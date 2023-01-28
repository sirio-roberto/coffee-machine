package machine;

enum MachineState {
    STARTING("start"),
    WAITING_USER_INPUT("waitInput"),
    BUYING("buy"),
    FILLING("fill"),
    TAKING_MONEY("take"),
    SHOWING_INFO("remaining"),
    EXITING("exit");

    private String value;
    MachineState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    static MachineState fromValue(String givenString) {
        for (MachineState state: values()) {
            if (state.value.equals(givenString)) {
                return state;
            }
        }
        return WAITING_USER_INPUT;
    }
}
