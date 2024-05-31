package cat.uvic.teknos.gt3.file.jpa.test;

import cat.uvic.teknos.gt3.domain.models.Brand;
import cat.uvic.teknos.gt3.file.jpa.models.BrandDataImpl;
import cat.uvic.teknos.gt3.file.jpa.models.BrandImpl;
import cat.uvic.teknos.gt3.file.jpa.models.CarImpl;
import cat.uvic.teknos.gt3.file.jpa.repository.JpaBrandRepository;
import cat.uvic.teknos.gt3.file.jpa.repository.JpaRepositoryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BrandRepositoryIntegrationTest {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    @BeforeAll
    static void setUp() throws IOException {
        var properties = new Properties();
        properties.load(JpaRepositoryFactory.class.getResourceAsStream("/persistence.properties"));
        entityManagerFactory = Persistence.createEntityManagerFactory("gt3_rep_mysql", properties);
        entityManager = entityManagerFactory.createEntityManager();
    }
    @AfterAll
    static void tearDown() {
        entityManagerFactory.close();
    }

    @Test
    void testSaveAndGet() {
        var repository = new JpaBrandRepository(entityManager);

        BrandImpl brand1 = new BrandImpl();
        brand1.setBrandName("TestBrand1");

        BrandImpl brand2 = new BrandImpl();
        brand1.setBrandName("TestBrand2");

        BrandDataImpl brandData1 = new BrandDataImpl();
        brandData1.setId(brand1.getId());
        brandData1.setCountryOfOrigin("Germany");
        brandData1.setContactInfo("contactTest@gmail.com");
        brandData1.setBrand(brand1);

        brand1.setBrandData(brandData1);

        BrandDataImpl brandData2 = new BrandDataImpl();
        brandData2.setId(brand2.getId());
        brandData2.setCountryOfOrigin("SPAIN");
        brandData2.setContactInfo("contactTest2@gmail.com");
        brandData2.setBrand(brand2);

        brand2.setBrandData(brandData2);

        CarImpl car1 = new CarImpl();
        car1.setId(1);
        CarImpl car2 = new CarImpl();
        car1.setId(3);
        CarImpl car3 = new CarImpl();
        car1.setId(5);

        var cars1 = new HashSet<cat.uvic.teknos.gt3.domain.models.Car>();
        cars1.add(car1);
        cars1.add(car2);

        var cars2 = new HashSet<cat.uvic.teknos.gt3.domain.models.Car>();
        cars2.add(car3);

        brand1.setCars(cars1);
        brand2.setCars(cars2);

        repository.save(brand1);
        repository.save(brand2);
    }

    @Test
    void getBrand(){
        var repository = new JpaBrandRepository(entityManager);

        BrandImpl brand = new BrandImpl();
        brand.setBrandName("TestBrand2");
        BrandDataImpl brandData = new BrandDataImpl();
        brandData.setCountryOfOrigin("SPAIN");
        brandData.setContactInfo("contactTest2@gmail.com");
        brand.setBrandData(brandData);

        repository.save(brand);

        System.out.println(repository.get(1).getBrandName());
    }

    @Test
    void getAllBrands(){
        var repository = new JpaBrandRepository(entityManager);

        BrandImpl brand = new BrandImpl();
        brand.setBrandName("TestBrand1");
        BrandImpl brand2 = new BrandImpl();
        brand2.setBrandName("TestBrand2");
        BrandDataImpl brandData = new BrandDataImpl();
        brandData.setCountryOfOrigin("SPAIN");
        brandData.setContactInfo("contactTest2@gmail.com");
        brand.setBrandData(brandData);

        repository.save(brand);
        repository.save(brand2);
    }
}
