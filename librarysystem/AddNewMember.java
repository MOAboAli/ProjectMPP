package librarysystem;

import javax.swing.*;
import java.util.HashMap;

public class AddNewMember extends JFrame implements LibWindow {
    private boolean isInitialized = false;

    @Override
    public void init() {

    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        this.isInitialized = val;
    }




}
