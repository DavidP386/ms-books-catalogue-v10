//DECLARACIÓN DE PAQUETES:
package com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.repository;

//IMPORTACIÓN DE LIBRERIAS:
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Libro;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 02/02/2026.
* DECLARACIÓN DE LA CLASE INTERFACE DEL REPOSITORIO QUIEN ES EL QUE HACE EL ENLACE DIRECTO HACIA LA BASE DE DATOS.
*/
public interface LibroRepository extends JpaRepository<Libro,Long> {

    //MÉTODO PARA BUSCAR LIBRO POR ID:
    Optional<Libro> findByIdLibro(Long idLibro);

    //LISTAR TODOS LOS LIBROS CON ORDENAMIENTO DINÁMICO:
    @Query(value = "SELECT * FROM tbl_libros ORDER BY " +
          "CASE WHEN :orderBy = 'titulo' THEN tbl_libros.titulo_libro END ASC, " +
          "CASE WHEN :orderBy = 'titulo' AND :orderMode = 'desc' THEN tbl_libros.titulo_libro END DESC, " +
          "CASE WHEN :orderBy = 'fecha' THEN tbl_libros.fecha_publicacion_libro END ASC, " +
          "CASE WHEN :orderBy = 'fecha' AND :orderMode = 'desc' THEN tbl_libros.fecha_publicacion_libro END DESC, " +
          "CASE WHEN :orderBy = 'precio' THEN tbl_libros.precio_libro END ASC, " +
          "CASE WHEN :orderBy = 'precio' AND :orderMode = 'desc' THEN tbl_libros.precio_libro END DESC, " +
          "CASE WHEN :orderBy IS NULL OR :orderBy = 'id' THEN tbl_libros.id_libro END ASC, " +
          "CASE WHEN :orderBy = 'id' AND :orderMode = 'desc' THEN tbl_libros.id_libro END DESC",
          nativeQuery = true)
    Slice<Libro> findAllLibrosWithOrder(@Param("orderBy") String orderBy, @Param("orderMode") String orderMode, Pageable pageable);

    //BUSCAR LIBROS POR KEYWORD CON ORDENAMIENTO DINÁMICO:
    @Query(value = "SELECT * FROM tbl_libros WHERE " +
          "(tbl_libros.titulo_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.fecha_publicacion_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.sinopsis_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.codigo_isbn_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "CAST(tbl_libros.precio_libro AS CHAR) LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.formato_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.estado_libro LIKE CONCAT('%', :keyword, '%') OR " +
          "tbl_libros.id_autor IN (SELECT id_autor FROM tbl_autores WHERE " +
          "nombres_autor LIKE CONCAT('%', :keyword, '%') OR " +
          "primer_apellido_autor LIKE CONCAT('%', :keyword, '%') OR " +
          "segundo_apellido_autor LIKE CONCAT('%', :keyword, '%'))) " +
          "ORDER BY " +
          "CASE WHEN :orderBy = 'titulo' THEN tbl_libros.titulo_libro END ASC, " +
          "CASE WHEN :orderBy = 'titulo' AND :orderMode = 'desc' THEN tbl_libros.titulo_libro END DESC, " +
          "CASE WHEN :orderBy = 'fecha' THEN tbl_libros.fecha_publicacion_libro END ASC, " +
          "CASE WHEN :orderBy = 'fecha' AND :orderMode = 'desc' THEN tbl_libros.fecha_publicacion_libro END DESC, " +
          "CASE WHEN :orderBy = 'precio' THEN tbl_libros.precio_libro END ASC, " +
          "CASE WHEN :orderBy = 'precio' AND :orderMode = 'desc' THEN tbl_libros.precio_libro END DESC, " +
          "CASE WHEN :orderBy IS NULL OR :orderBy = 'id' THEN tbl_libros.id_libro END ASC, " +
          "CASE WHEN :orderBy = 'id' AND :orderMode = 'desc' THEN tbl_libros.id_libro END DESC",
          nativeQuery = true)
    Slice<Libro> findLibrosByKeywordWithOrder(@Param("keyword") String keyword, @Param("orderBy") String orderBy, @Param("orderMode") String orderMode, Pageable pageable);
}
