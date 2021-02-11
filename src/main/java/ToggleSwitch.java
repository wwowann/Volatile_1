public class ToggleSwitch {
    private boolean onOff;

    public boolean getSwitch() {
        return onOff;
    }

    public boolean clickSwitch() {

        return onOff = !onOff;
    }
}
