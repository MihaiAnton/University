package Repository;

import Domain.IdEntity;

public interface CRUDRepo<ID,E extends IdEntity<ID>> {

    E save(E entity);
    E update(E entity);
    E delete(ID id);
    E findOne(ID id);
    Iterable<E> findAll();
}
