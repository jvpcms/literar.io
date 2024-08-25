package com.literario.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.literario.api.model.Book;

@RepositoryRestResource
public interface BookRepo extends JpaRepository<Book, String> {


    @Query("SELECT b FROM Book b WHERE b.year = :year")
    List<Book> findBooksByYear(@Param("year") Integer year);
}


/**
 * Repositório para a entidade Book.
 * 
 * Este repositório fornece várias funcionalidades padrão para manipulação de entidades Book no banco de dados.
 * Ele estende JpaRepository, que fornece métodos CRUD e outras operações comuns de banco de dados.
 * 
 * Funcionalidades padrão:
 * 
 * - Salvar uma Entidade:
 *   - save(S entity): Salva a entidade fornecida no banco de dados.
 * 
 * - Salvar Várias Entidades:
 *   - saveAll(Iterable<S> entities): Salva todas as entidades fornecidas no banco de dados.
 * 
 * - Buscar uma Entidade por ID:
 *   - findById(ID id): Retorna uma entidade pelo seu ID.
 * 
 * - Verificar Existência por ID:
 *   - existsById(ID id): Verifica se uma entidade com o ID fornecido existe.
 * 
 * - Buscar Todas as Entidades:
 *   - findAll(): Retorna todas as entidades.
 * 
 * - Buscar Todas as Entidades por IDs:
 *   - findAllById(Iterable<ID> ids): Retorna todas as entidades cujos IDs são fornecidos.
 * 
 * - Contar Entidades:
 *   - count(): Retorna o número total de entidades.
 * 
 * - Deletar uma Entidade:
 *   - delete(T entity): Deleta a entidade fornecida.
 * 
 * - Deletar uma Entidade por ID:
 *   - deleteById(ID id): Deleta a entidade com o ID fornecido.
 * 
 * - Deletar Várias Entidades:
 *   - deleteAll(Iterable<? extends T> entities): Deleta todas as entidades fornecidas.
 * 
 * - Deletar Todas as Entidades:
 *   - deleteAll(): Deleta todas as entidades.
 * 
 * - Deletar Todas as Entidades em um Lote:
 *   - deleteAllInBatch(): Deleta todas as entidades em um único lote.
 * 
 * - Buscar Todas as Entidades com Paginação:
 *   - findAll(Pageable pageable): Retorna todas as entidades em uma página específica.
 * 
 * - Buscar Todas as Entidades com Ordenação:
 *   - findAll(Sort sort): Retorna todas as entidades ordenadas conforme especificado.
 */
