//DECLARACIÓN DE PAQUETES:
package com.relatospapelv10.com.co.relatospapelv10.web.controller;

//IMPORTACIÓN DE LIBRERIAS:
import com.relatospapelv10.com.co.relatospapelv10.dominio.dto.RespuestaDTO;
import com.relatospapelv10.com.co.relatospapelv10.dominio.dto.AutorDTO;
import com.relatospapelv10.com.co.relatospapelv10.dominio.service.AutorService;
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
@RequestMapping("/autor")//DECLARACIÓN DE LA RESPUESTA PRINCIPAL DEL MAPEO DE LOS CRUDS.
public class AutorController {
    
    @Autowired//INYECTAMOS EL SERVICIO.
    private AutorService autorService;
    
    //CONTROLADORES DE CRUDS (CREACIÓN, LECTURA (LISTAR Y CONSULTAR), EDICIÓN Y ELIMINACIÓN DE UN REGISTRO).
    
    //1. LISTADO DE REGISTROS FILTRADOS.
    //LISTAR REGISTROS:
    @GetMapping("/listAllAutores")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<AutorDTO>> listarAutores(){
        return new ResponseEntity<>(autorService.listarAutores(), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS ORDENADOS POR ID DE FORMA ASCENDENTE:
    @GetMapping("/listAllAutoresOrderedbyIdAsc")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<AutorDTO>> listarAutoresOrdenadosporIdAsc(){
        return new ResponseEntity<>(autorService.listarAutoresOrdenadosporIdAsc(), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS ORDENADOS POR ID DE FORMA ASCENDENTE CON PAGINACIÓN:
    @GetMapping("/listAllAutoresOrderedbyIdAscPag")
    public ResponseEntity<Slice<AutorDTO>> listarAutoresOrdenadosporIdAscPag(
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<AutorDTO> autores = autorService.listarAutoresOrdenadosporIdAscPag(pageable);
        return new ResponseEntity<>(autores, HttpStatus.OK);
    }
    
    //2. LISTADO DE REGISTROS FILTRADOS.
    //LISTAR REGISTROS FILTRADOS POR PALABRA CLAVE Y ORDENADOS POR ID DE FORMA ASCENDENTE:
    @GetMapping("/listAllAutoresbyKeywordAndOrderedbyIdAsc/{keyword}")//DECLARACIÓN DEL MAPEO DEL CRUD LISTAR REGISTROS.
    public ResponseEntity<List<AutorDTO>> listarAutoresporPalabraClaveyOrdenadosporIdAsc(@PathVariable String keyword){
        return new ResponseEntity<>(autorService.listarAutoresporPalabraClaveyOrdenadosporIdAsc(keyword), HttpStatus.OK);
    }
    
    //LISTAR REGISTROS FILTRADOS POR PALABRA CLAVE Y ORDENADOS POR ID DE FORMA ASCENDENTE CON PAGINACIÓN:
    @GetMapping("/listAllAutoresbyKeywordAndOrderedbyIdAscPag/{keyword}")
    public ResponseEntity<Slice<AutorDTO>> listarAutoresporPalabraClaveyOrdenadosporIdAscPag(
           @RequestParam(name = "page", defaultValue = "0") int page,
           @RequestParam(name = "size", defaultValue = "10") int size,
           @PathVariable("keyword") String keyword
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<AutorDTO> autores = autorService.listarAutoresporPalabraClaveyOrdenadosporIdAscPag(pageable, keyword);
        return new ResponseEntity<>(autores, HttpStatus.OK);
    }
    
    //CREAR REGISTRO:
    @PostMapping("/createAutor")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    //@PutMapping("/createAutor")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    public RespuestaDTO crearAutor(@RequestBody AutorDTO autorDTO){
        System.out.println(autorDTO);
        return autorService.crearAutor(autorDTO);
    }
    
    //LEER CONSULTA DE REGISTRO POR ID:
    @GetMapping("/getAutorbyId/{idAutor}")//DECLARACIÓN DEL MAPEO DEL CRUD CONSULTAR REGISTRO.
    public RespuestaDTO consultarAutorbyId(@PathVariable Long idAutor){
        return autorService.consultarAutorporId(idAutor);
    }
    
    //MODIFICAR REGISTRO:
    //@PostMapping("/updateAutor")//DECLARACIÓN DEL MAPEO DEL CRUD CREAR REGISTRO.
    @PutMapping("/updateAutor")//DECLARACIÓN DEL MAPEO DEL CRUD MODIFICAR REGISTRO.
    public RespuestaDTO actualizarAutor(@RequestBody AutorDTO autorDTO){
        return autorService.actualizarAutor(autorDTO);
    }
    
    //ELIMINAR REGISTRO:
    @DeleteMapping("/deleteAutor/{idAutor}")//DECLARACIÓN DEL MAPEO DEL CRUD ELIMINAR REGISTRO.
    public RespuestaDTO eliminarAutor(@PathVariable Long idAutor){
        return autorService.eliminarAutor(idAutor);
    }
}
