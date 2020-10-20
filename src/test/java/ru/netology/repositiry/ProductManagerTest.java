package ru.netology.repositiry;

import org.junit.jupiter.api.Test;
import ru.netology.product.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.product.Product;
import ru.netology.product.SmartPhone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {


    Book firstBook = new Book(1, "One", 100, "King");
    Book secondBook = new Book(2, "Rock", 500, "Keysi");
    Book thirdBook = new Book(3, "Space", 1000, "Nokia");

    SmartPhone firstSmartPhone = new SmartPhone(4, "One", 100, "Honor");
    SmartPhone secondSmartPhone = new SmartPhone(5, "Rock", 500, "Samsung");
    SmartPhone thirdSmartPhone = new SmartPhone(6, "Space", 1000, "Nokia");


    @Test
    void shouldDeleteIfExists() {
        ProductRepository repository = new ProductRepository();
        repository.save(firstBook);
        repository.save(secondBook);
        repository.save(thirdBook);
        repository.save(firstSmartPhone);
        repository.save(secondSmartPhone);
        repository.save(thirdSmartPhone);
        repository.removeById(1);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{
                secondBook, thirdBook, firstSmartPhone, secondSmartPhone, thirdSmartPhone};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldThrowIfNotExist() {
        ProductRepository repository = new ProductRepository();
        repository.save(firstBook);
        repository.save(secondBook);
        repository.save(thirdBook);
        repository.save(firstSmartPhone);
        repository.save(secondSmartPhone);
        repository.save(thirdSmartPhone);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            repository.removeById(7);
        });

        String expectedMessage = "Element with id: 7 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
