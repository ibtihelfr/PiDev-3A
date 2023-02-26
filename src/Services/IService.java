/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tunmix.services;

import java.util.List;

/**
 *
 * @author Asma Laaribi
 * @param <R>
 */
public interface IService<R> {
    void insert(R r);
    void delete(R r);
    void update(R r);
    List<R> readAll();
    R readById(int idReclamation);    
}

    

