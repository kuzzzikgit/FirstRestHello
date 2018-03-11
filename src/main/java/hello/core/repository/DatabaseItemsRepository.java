package hello.core.repository;

import hello.core.model.Item;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.UUID;

public class DatabaseItemsRepository  implements ItemsRepository{

    private JdbcOperations jdbcOperations;

    public DatabaseItemsRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Item> getAllItems() {
        return jdbcOperations.query(
                "SELECT id, name FROM item",
                (resultSet, i) -> {
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    return new Item(id, name);
                });
    }

    @Override
    public Item create(Item newItem) {
        long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        String name = newItem.getName();
        int rows = jdbcOperations.update(
                "INSERT INTO item (id, name) VALUES (?, ?)",
                id, name
        );
        return new Item(id, name);
    }

    @Override
    public Item edit(long id, Item newItem) {
        String name = newItem.getName();
        int rows = jdbcOperations.update(
                "UPDATE item SET name = ? WHERE id = ?",
                name, id);
        return new Item(id, name);
    }

    @Override
    public Item gettingForId(long id) {
        return jdbcOperations.queryForObject(
                "SELECT id, name FROM item WHERE id = ?",
                (resultSet, i) -> {
                    long rowId = resultSet.getLong(1);
                    String rowName = resultSet.getString(2);
                    return new Item(rowId, rowName);
                },
                id);
    }

    @Override
    public boolean delete(long id) {
        int rows = jdbcOperations.update(
                "DELETE FROM item WHERE id = ?",
                id
        );
        return rows > 0;
    }
}
