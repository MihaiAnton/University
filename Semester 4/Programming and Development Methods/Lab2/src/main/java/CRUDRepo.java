public interface CRUDRepo<ID, E> {

    E save(E entity);
    E update(E entity);
    E delete(E entity);
    E findOne(ID id);
}
