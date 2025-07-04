package service;

import serviceContracts.IShippableItem;
import serviceContracts.IShippingService;

import java.util.List;

public class ShippingService implements IShippingService {
    @Override
    public void shipItems(List<IShippableItem> items) {
        if (items == null || items.isEmpty()) {
            System.out.println("No items to ship.");
            return;
        }

        System.out.println("** Shipment notice **");
        double totalWeight = 0;

        for (var item : items) {
            System.out.println(item.getName());
            totalWeight += item.getWeight();
        }

        System.out.printf("Total package weight: %.2f kg\n", totalWeight / 1000.0);

    }
}
