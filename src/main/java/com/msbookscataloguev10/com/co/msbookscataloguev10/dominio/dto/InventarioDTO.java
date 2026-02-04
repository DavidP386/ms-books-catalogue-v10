package com.msbookscataloguev10.com.co.msbookscataloguev10.dominio.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class InventarioDTO {
  @Schema(description = "ID del inventario generado automáticamente", example = "1")
  private Long idInventario;
  @Schema(description = "ID del libro", example = "1")
  private Long idLibro;
  @Schema(description = "Tipo movimiento: ENTRADA/SALIDA", example = "10")
  private String tipoMovimiento;
  @Schema(description = "Fecha con la que se registra el inventario", example = "10")
  private String fechaInventario;
  @Schema(description = "Cantidad del movimiento", example = "10")
  private Integer cantidadInventario;
}
