package com.br.springjpademo.repository;

import com.br.springjpademo.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeStartingWith(String start);

    List<Cliente> findByIdadeOrderByNomeAsc(Integer idade);

    @Transactional
    void deleteByNomeIgnoreCaseOrIdade(String nome, Integer idade);

    @Query("select c from Cliente c where c.nome like ?1%")
    List<Cliente> nomeComecandoCom(String start);

    @Query("select c from Cliente c where c.idade = ?1 order by c.nome asc")
    List<Cliente> idadeIgualOrdemNome(Integer idade);

    @Query("delete from Cliente c where (UPPER(c.nome) = UPPER(?1)) or (c.idade = ?2)")
    @Transactional
    @Modifying
    void deletarPorNomeOuIdade(String nome, Integer idade);


}
