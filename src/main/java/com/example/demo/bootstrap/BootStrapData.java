package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;
    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository,
            OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository = outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Requirement E: Add a sample inventory appropriate for your chosen store
        // (Guitar Shop)
        // Add sample inventory only when both the part and product lists are empty.
        if (partRepository.count() == 0 && productRepository.count() == 0) {

            // 5 parts
            InhousePart strings = new InhousePart();
            strings.setName("Acoustic Guitar Strings Set");
            strings.setPrice(12.99);
            strings.setInv(50);
            strings.setMin(10);
            strings.setMax(100);
            strings.setPartId(1001);

            InhousePart tuners = new InhousePart();
            tuners.setName("Machine Head Tuners Set");
            tuners.setPrice(24.99);
            tuners.setInv(30);
            tuners.setMin(5);
            tuners.setMax(50);
            tuners.setPartId(1002);

            OutsourcedPart pickups = new OutsourcedPart();
            pickups.setName("Humbucker Pickup Set");
            pickups.setCompanyName("Seymour Duncan");
            pickups.setPrice(149.99);
            pickups.setInv(20);
            pickups.setMin(5);
            pickups.setMax(40);

            OutsourcedPart neck = new OutsourcedPart();
            neck.setName("Maple Guitar Neck");
            neck.setCompanyName("Warmoth");
            neck.setPrice(199.99);
            neck.setInv(15);
            neck.setMin(3);
            neck.setMax(25);

            InhousePart bridge = new InhousePart();
            bridge.setName("Tune-O-Matic Bridge");
            bridge.setPrice(45.00);
            bridge.setInv(25);
            bridge.setMin(5);
            bridge.setMax(50);
            bridge.setPartId(1003);

            partRepository.save(strings);
            partRepository.save(tuners);
            partRepository.save(pickups);
            partRepository.save(neck);
            partRepository.save(bridge);

            // 5 products (guitars) — set both sides of the relationship (Part owns the join table)
            Product acoustic = new Product("Classic Acoustic Guitar", 299.99, 8);
            acoustic.getParts().add(strings);
            strings.getProducts().add(acoustic);

            Product electric = new Product("Standard Electric Guitar", 549.99, 5);
            electric.getParts().add(strings);
            electric.getParts().add(pickups);
            electric.getParts().add(neck);
            electric.getParts().add(bridge);
            electric.getParts().add(tuners);
            strings.getProducts().add(electric);
            pickups.getProducts().add(electric);
            neck.getProducts().add(electric);
            bridge.getProducts().add(electric);
            tuners.getProducts().add(electric);

            Product beginnerPack = new Product("Beginner Guitar Pack", 199.99, 12);
            beginnerPack.getParts().add(strings);
            beginnerPack.getParts().add(tuners);
            strings.getProducts().add(beginnerPack);
            tuners.getProducts().add(beginnerPack);

            Product bass = new Product("4-String Bass Guitar", 429.99, 4);
            bass.getParts().add(strings);
            bass.getParts().add(neck);
            bass.getParts().add(tuners);
            bass.getParts().add(bridge);
            strings.getProducts().add(bass);
            neck.getProducts().add(bass);
            tuners.getProducts().add(bass);
            bridge.getProducts().add(bass);

            Product custom = new Product("Custom Electric Guitar", 899.99, 2);
            custom.getParts().add(strings);
            custom.getParts().add(pickups);
            custom.getParts().add(neck);
            custom.getParts().add(bridge);
            custom.getParts().add(tuners);
            strings.getProducts().add(custom);
            pickups.getProducts().add(custom);
            neck.getProducts().add(custom);
            bridge.getProducts().add(custom);
            tuners.getProducts().add(custom);

            productRepository.save(acoustic);
            productRepository.save(electric);
            productRepository.save(beginnerPack);
            productRepository.save(bass);
            productRepository.save(custom);

            // Re-save parts so the owner side (Part) persists the product_part join table
            // partRepository.save(strings);
            // partRepository.save(tuners);
            // partRepository.save(pickups);
            // partRepository.save(neck);
            // partRepository.save(bridge);
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products: " + productRepository.count());
        System.out.println("Number of Parts: " + partRepository.count());
    }
}
