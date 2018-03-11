package hello.core.repository;

import hello.core.repository.ItemsRepository;
import hello.core.model.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirstItemsRepository implements ItemsRepository {

    private List<Item> items = new ArrayList<Item>();

    public FirstItemsRepository() {
        items.add(new Item(0, "hello world!"));
        items.add(new Item(1, "hello new world!"));
    }

    @Override
    public List<Item> getAllItems() {

        return Collections.unmodifiableList(items);
    }

    @Override
    public Item create(Item newItem) {
        items.add(newItem);
        return newItem;
    }

    @Override
    public Item edit(long id, Item newItem) {
        return new Item(id,newItem.getName());
    }

    @Override
    public Item gettingForId(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return  items.remove(gettingForId(id));
    }
}
