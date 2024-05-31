package cat.uvic.teknos.gt3.file.jpa.repository;

import cat.uvic.teknos.gt3.domain.models.CarData;
import cat.uvic.teknos.gt3.domain.repositories.CarDataRepository;
import cat.uvic.teknos.gt3.file.jpa.models.CarDataImpl;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaCarDataRepository implements CarDataRepository {
    private final EntityManager entityManager;

    public JpaCarDataRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void save(CarData model) {
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
    public void delete(CarData model) {
        try{
            entityManager.getTransaction().begin();
            var carData = entityManager.find(cat.uvic.teknos.gt3.domain.models.CarData.class, model.getId());
            entityManager.remove(carData);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace(); // Handle exception appropriately
        }
    }

    @Override
    public CarData get(Integer id) {
        return entityManager.find(cat.uvic.teknos.gt3.domain.models.CarData.class, id);
    }

    @Override
    public Set<CarData> getAll() {
        List<CarData> carDataList = entityManager.createQuery("SELECT u FROM CarDataImpl u", CarData.class).getResultList();
        return new HashSet<>(carDataList);
    }
}
