package cat.uvic.teknos.gt3.file.jpa.repository;

import cat.uvic.teknos.gt3.domain.models.Circuit;
import cat.uvic.teknos.gt3.domain.repositories.CircuitRepository;
import cat.uvic.teknos.gt3.file.jpa.models.CircuitImpl;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaCircuitRepository implements CircuitRepository {
    private final EntityManager entityManager;

    public JpaCircuitRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void save(Circuit model) {
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
    public void delete(Circuit model) {
        try{
            entityManager.getTransaction().begin();
            var circuit = entityManager.find(cat.uvic.teknos.gt3.domain.models.Circuit.class, model.getId());
            entityManager.remove(circuit);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace(); // Handle exception appropriately
        }
    }

    @Override
    public Circuit get(Integer id) {
        return entityManager.find(cat.uvic.teknos.gt3.domain.models.Circuit.class, id);
    }

    @Override
    public Set<Circuit> getAll() {
        List<Circuit> circuitList = entityManager.createQuery("SELECT u FROM CircuitImpl u", Circuit.class).getResultList();
        return new HashSet<>(circuitList);
    }
}
