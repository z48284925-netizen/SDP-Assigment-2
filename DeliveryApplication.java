package com.glamlogistics.app;

import com.glamlogistics.transport.Logistics;
import com.glamlogistics.ui.Button;
import com.glamlogistics.ui.Checkbox;
import com.glamlogistics.ui.GUIFactory;

public class DeliveryApplication {

    private final GUIFactory guiFactory;
    private final Logistics logistics;

    public DeliveryApplication(GUIFactory guiFactory, Logistics logistics) {
        this.guiFactory = guiFactory;
        this.logistics = logistics;
    }

    public void run(String cargo, String destination) {
        renderStorefront();
        logistics.planDelivery(cargo, destination);
    }

    private void renderStorefront() {
        Button button = guiFactory.createButton();
        Checkbox checkbox = guiFactory.createCheckbox();
        button.paint();
        checkbox.paint();
    }
}
