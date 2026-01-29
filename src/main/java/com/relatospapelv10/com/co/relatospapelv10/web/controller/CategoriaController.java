//DECLARACIÓN DE PAQUETES:
package com.relatospapelv10.com.co.relatospapelv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.relatospapelv10.com.co.relatospapelv10.dominio.dto.RespuestaDTO;
import com.relatospapelv10.com.co.relatospapelv10.dominio.dto.CategoriaDTO;
import com.relatospapelv10.com.co.relatospapelv10.dominio.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* @Autor HERNAN ADOLFO NUÑEZ GONZALEZ.
* @Since 28/01/2026.
* Declaración del controlador.
*/
@RestController//DECLARACIÓN DEL CONTROLADOR PARA LOS CRUDS.
@RequestMapping("/categoria")//DECLARACIÓN DE LA RESPUESTA PRINCIPAL DEL MAPEO DE LOS CRUDS.
public class CategoriaController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private CategoriaService categoriaService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    //LISTAR REGISTROS:
    @GetMapping("/listAllCategorias")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<CategoriaDTO>> listarCategorias(){
        return new ResponseEntity<>(categoriaService.listarCategorias(), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS ORDENADOS POR ID DE FORMA ASCENDENTE:
    @GetMapping("/listAllCategoriasOrderedbyIdAsc")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<CategoriaDTO>> listarCategoriasOrdenadosporIdAsc(){
        return new ResponseEntity<>(categoriaService.listarCategoriasOrdenadosporIdAsc(), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS ORDENADOS POR ID DE FORMA ASCENDENTE CON PAGINACIÓN:
    @GetMapping("/listAllCategoriasOrderedbyIdAscPag")
    public ResponseEntity<Slice<CategoriaDTO>> listarCategoriasOrdenadosporIdAscPag(
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<CategoriaDTO> categorias = categoriaService.listarCategoriasOrdenadosporIdAscPag(pageable);
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }
    
    //2. LISTADO DE REGISTROS FILTRADOS.
    //LISTAR REGISTROS FILTRADOS POR PALABRA CLAVE Y ORDENADOS POR ID DE FORMA ASCENDENTE:
    @GetMapping("/listAllCategoriasbyKeywordAndOrderedbyIdAsc/{keyword}")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<CategoriaDTO>> listarCategoriasporPalabraClaveyOrdenadosporIdAsc(@PathVariable String keyword){
        return new ResponseEntity<>(categoriaService.listarCategoriasporPalabraClaveyOrdenadosporIdAsc(keyword), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS POR PALABRA CLAVE Y ORDENADOS POR ID DE FORMA ASCENDENTE CON PAGINACIÓN:
    @GetMapping("/listAllCategoriasbyKeywordAndOrderedbyIdAscPag/{keyword}")
    public ResponseEntity<Slice<CategoriaDTO>> listarCategoriasporPalabraClaveyOrdenadosporIdAscPag(
           @RequestParam(name = "page", defaultValue = "0") int page,
           @RequestParam(name = "size", defaultValue = "10") int size,
           @PathVariable("keyword") String keyword
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<CategoriaDTO> categorias = categoriaService.listarCategoriasporPalabraClaveyOrdenadosporIdAscPag(pageable, keyword);
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/createCategoria")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    //@PutMapping("/createCategoria")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearCategoria(@RequestBody CategoriaDTO categoriaDTO){
        System.out.println(categoriaDTO);
        return categoriaService.crearCategoria(categoriaDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/getCategoriabyId/{idCategoria}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarCategoriabyId(@PathVariable Long idCategoria){
        return categoriaService.consultarCategoriaporId(idCategoria);
    }
    
    //LEER CONSULTA DE REGISTRO POR NOMBRE:
    @GetMapping("/getCategoriabyNombre/{nombreCategoria}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarCategoriabyNombre(@PathVariable String nombreCategoria){
        return categoriaService.consultarCategoriaporNombre(nombreCategoria);
    }
    
    //MODIFICAR REGISTRO:
    //@PostMapping("/updateCategoria")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    @PutMapping("/updateCategoria")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarCategoria(@RequestBody CategoriaDTO categoriaDTO){
        return categoriaService.actualizarCategoria(categoriaDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/deleteCategoria/{idCategoria}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarCategoria(@PathVariable Long idCategoria){
        return categoriaService.eliminarCategoria(idCategoria);
    }
}
