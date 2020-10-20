package ru.netology.manager;

import ru.netology.product.Book;
import ru.netology.product.Product;
import ru.netology.product.SmartPhone;
import ru.netology.repository.ProductRepository;

public class ProductManager {
    ProductRepository repository;

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product product : repository.findAll()) {
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product product, String search) {
        if (product instanceof Book) {
            Book book = (Book) product;
            if (book.getName().equalsIgnoreCase(search)) {
                return true;
            }
            if (book.getAuthor().equalsIgnoreCase(search)) {
                return true;
            }
            return false;
        }

        if (product instanceof SmartPhone) {
            SmartPhone smartPhone = (SmartPhone) product;
            if (smartPhone.getName().equalsIgnoreCase(search)) {
                return true;
            }
            if (smartPhone.getProducer().equalsIgnoreCase(search)) {
                return true;
            }

            return false;
        }
        return false;
    }
}


