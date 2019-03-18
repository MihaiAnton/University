package Repository;

import Domain.IdEntity;
import Exceptions.ValidationException;
import Validators.Validator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public abstract class GenericHashMapRepository<ID, E extends IdEntity<ID>> implements CrudRepository<ID, E> {

    /**
     *  Generic implementation of the CrudRepository interface
     *  @param <ID> type E must have an ID
     *  @param <E> must implement the interface IdEntity
     */

    Predicate<E> isNULLEntity = (x)->x==null;
    Predicate<ID> isNULLId = (x)->x==null;


    private final HashMap<ID, E> list;
    private final Validator<E> validator;

    public GenericHashMapRepository(Validator<E> valid){
        this.validator = valid;
        list = new HashMap<>();
    }

    @Override
    public E findOne(ID key) /*throws IllegalArgumentException*/{
        if(isNULLId.test(key)){
            throw new IllegalArgumentException("Null id in findOne.");
        }

        return list.get(key);
    }

    @Override
    public Iterable<E> findAll(){
        return list.values();
    }

    @Override
    public E save(E entity) /*throws ValidationException, IllegalArgumentException*/{
        if(isNULLEntity.test(entity)){
            throw new IllegalArgumentException("Null entity in save.");
        }

        validator.validate(entity);

        if(this.findOne(entity.getId()) != null){       //has the element
            return entity;
        }
        else{
            this.list.put(entity.getId(), entity);
            return null;
        }
    }

    @Override
    public E delete(ID id) /*throws IllegalArgumentException*/{
        if(isNULLId.test(id)){
            throw new IllegalArgumentException("Null id in delete.");
        }

        if(this.findOne(id) == null){
            return null;
        }
        else{
            E elem = this.findOne(id);
            this.list.remove(id);
            return elem;
        }
    }

    @Override
    public  E update(E entity) /*throws ValidationException, IllegalArgumentException*/{
        if(isNULLEntity.test(entity)){
            throw new IllegalArgumentException("Null entity in update.");
        }

        validator.validate(entity);

        if(this.findOne(entity.getId()) == null){   //doesn t exist
            return entity;
        }
        this.delete(entity.getId());
        this.save(entity);
        return null;
    }

    public Collection<E> getValues(){

        return this.list.values();
    }

    public int getSize(){
        return this.list.size();
    }



}























