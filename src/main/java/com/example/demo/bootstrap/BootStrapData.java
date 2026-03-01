package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

            System.out.println("Loading sample data...");

            // STEP 1: Create and save all parts first
            InhousePart strings = new InhousePart();
            strings.setName("Acoustic Guitar Strings Set");
            strings.setPrice(12.99);
            strings.setInv(50);
            strings.setMin(10);
            strings.setMax(100);
            strings.setPartId(1001);
            partRepository.save(strings);

            InhousePart tuners = new InhousePart();
            tuners.setName("Machine Head Tuners Set");
            tuners.setPrice(24.99);
            tuners.setInv(30);
            tuners.setMin(5);
            tuners.setMax(50);
            tuners.setPartId(1002);
            partRepository.save(tuners);

            OutsourcedPart pickups = new OutsourcedPart();
            pickups.setName("Humbucker Pickup Set");
            pickups.setCompanyName("Seymour Duncan");
            pickups.setPrice(149.99);
            pickups.setInv(20);
            pickups.setMin(5);
            pickups.setMax(40);
            partRepository.save(pickups);

            OutsourcedPart neck = new OutsourcedPart();
            neck.setName("Maple Guitar Neck");
            neck.setCompanyName("Warmoth");
            neck.setPrice(199.99);
            neck.setInv(15);
            neck.setMin(3);
            neck.setMax(25);
            partRepository.save(neck);

            InhousePart bridge = new InhousePart();
            bridge.setName("Tune-O-Matic Bridge");
            bridge.setPrice(45.00);
            bridge.setInv(25);
            bridge.setMin(5);
            bridge.setMax(50);
            bridge.setPartId(1003);
            partRepository.save(bridge);

            // STEP 2: Create products
            Product acoustic = new Product("Classic Acoustic Guitar", 299.99, 8);
            Product electric = new Product("Standard Electric Guitar", 549.99, 5);
            Product beginnerPack = new Product("Beginner Guitar Pack", 199.99, 12);
            Product bass = new Product("4-String Bass Guitar", 429.99, 4);
            Product custom = new Product("Custom Electric Guitar", 899.99, 2);

            // STEP 3: Save products first
            productRepository.save(acoustic);
            productRepository.save(electric);
            productRepository.save(beginnerPack);
            productRepository.save(bass);
            productRepository.save(custom);

            // STEP 4: Clear any existing associations to avoid duplicates
            strings.getProducts().clear();
            tuners.getProducts().clear();
            pickups.getProducts().clear();
            neck.getProducts().clear();
            bridge.getProducts().clear();

            // STEP 5: Add associations - each part to its products
            // Acoustic Guitar (only needs strings)
            acoustic.getParts().add(strings);
            strings.getProducts().add(acoustic);
            
            // Electric Guitar (needs all parts)
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

            // Beginner Pack (strings and tuners)
            beginnerPack.getParts().add(strings);
            beginnerPack.getParts().add(tuners);
            strings.getProducts().add(beginnerPack);
            tuners.getProducts().add(beginnerPack);

            // Bass Guitar (strings, neck, tuners, bridge)
            bass.getParts().add(strings);
            bass.getParts().add(neck);
            bass.getParts().add(tuners);
            bass.getParts().add(bridge);
            strings.getProducts().add(bass);
            neck.getProducts().add(bass);
            tuners.getProducts().add(bass);
            bridge.getProducts().add(bass);

            // Custom Electric Guitar (all parts)
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

            // STEP 6: Save everything ONE TIME at the end
            productRepository.save(acoustic);
            productRepository.save(electric);
            productRepository.save(beginnerPack);
            productRepository.save(bass);
            productRepository.save(custom);
            
            partRepository.save(strings);
            partRepository.save(tuners);
            partRepository.save(pickups);
            partRepository.save(neck);
            partRepository.save(bridge);

            System.out.println("Sample data loaded successfully!");
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products: " + productRepository.count());
        System.out.println("Number of Parts: " + partRepository.count());
    }
}