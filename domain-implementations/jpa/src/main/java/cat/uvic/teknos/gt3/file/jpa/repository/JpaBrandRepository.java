package cat.uvic.teknos.gt3.file.jpa.repository;


import cat.uvic.teknos.gt3.domain.models.Brand;
import cat.uvic.teknos.gt3.domain.repositories.BrandRepository;
import cat.uvic.teknos.gt3.file.jpa.models.BrandImpl;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaBrandRepository implements BrandRepository {
    private final EntityManager entityManager;

    public JpaBrandRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public void save(Brand model) {

        try{
            entityManager.getTransaction().begin();
            if(model.getId()<=0){
                //INSERT PLAYLIST
                entityManager.persist(model);
            }else if(!entityManager.contains(model)){
                //UPDATE PLAYLIST
                entityManager.merge(model);
            }
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Brand model) {
        try{
            entityManager.getTransaction().begin();
            var playlist = entityManager.find(cat.uvic.teknos.gt3.domain.models.Brand.class, model.getId());
            entityManager.remove(playlist);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            System.out.println("ERROR");
        }
    }

    @Override
    public Brand get(Integer id) {
        return entityManager.find(cat.uvic.teknos.gt3.domain.models.Brand.class, id);

    }

    @Override
    public Set<Brand> getAll() {
        List<Brand> playlistList = entityManager.createQuery("SELECT u FROM BrandImpl u", Brand.class).getResultList();
        return new HashSet<>(playlistList);
    }
}
