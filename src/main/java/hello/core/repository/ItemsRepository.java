package hello.core.repository;

import hello.core.model.Item;

import java.util.List;

public interface ItemsRepository {

    List<Item> getAllItems();
    Item create(Item newItem);

    Item edit(long id, Item newItem);
}
