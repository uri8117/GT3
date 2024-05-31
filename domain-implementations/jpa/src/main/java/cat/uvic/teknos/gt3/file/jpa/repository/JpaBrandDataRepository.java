package cat.uvic.teknos.gt3.file.jpa.repository;

import cat.uvic.teknos.gt3.domain.models.BrandData;
import cat.uvic.teknos.gt3.domain.repositories.BrandDataRepository;
import cat.uvic.teknos.gt3.file.jpa.models.BrandDataImpl;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaBrandDataRepository implements BrandDataRepository {
    private final EntityManager entityManager;

    public JpaBrandDataRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void save(BrandData model) {
        try{
            entityManager.getTransaction().begin();
            if(model.getId() <= 0){
                entityManager.persist(model);
            } else if(!entityManager.contains(model)){
                entityManager.merge(model);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace(); // Handle exception appropriately
        }
    }

    @Override
    public void delete(BrandData model) {
        try{
            entityManager.getTransaction().begin();
            var brandData = entityManager.find(cat.uvic.teknos.gt3.domain.models.BrandData.class, model.getId());
            entityManager.remove(brandData);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace(); // Handle exception appropriately
        }
    }

    @Override
    public BrandData get(Integer id) {
        return entityManager.find(cat.uvic.teknos.gt3.domain.models.BrandData.class, id);
    }

    @Override
    public Set<BrandData> getAll() {
        List<BrandData> brandDataList = entityManager.createQuery("SELECT u FROM BrandDataImpl u", BrandData.class).getResultList();
        return new HashSet<>(brandDataList);
    }
}
