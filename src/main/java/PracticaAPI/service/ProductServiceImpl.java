package PracticaAPI.service;

import PracticaAPI.domain.Jugador;
import PracticaAPI.exception.ProductNotFoundException;
import PracticaAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Implementación del Service de productos
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Set<Jugador> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Set<Jugador> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Optional<Jugador> findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Jugador addProduct(Jugador product) {
        return productRepository.save(product);
    }

    @Override
    public Jugador modifyProduct(long id, Jugador newProduct) {
        Jugador product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        newProduct.setId(product.getId());
        return productRepository.save(newProduct);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.deleteById(id);
    }
}
