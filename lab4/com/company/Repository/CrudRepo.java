//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.company.Repository;

public interface CrudRepo<E> {
    E findOne(Long var1);

    Iterable<E> findAll();

    E save(E var1);

    E delete(Long var1);

    E update(E var1);
}