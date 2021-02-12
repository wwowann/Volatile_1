public class ToggleSwitch {
    volatile private boolean onOff;

    public boolean getSwitch() {
        return onOff;
    }

    public boolean clickSwitch() {

        return onOff = !onOff;
    }
}
