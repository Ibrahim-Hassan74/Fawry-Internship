package serviceContracts;

import java.util.List;

public interface IShippingService {
    void shipItems(List<IShippableItem> items);
}
