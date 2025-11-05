package org.ebuitra10.smarorderaiproject.msvc.users.domain.order;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
/**
 * Clase utilizada como objeto de transferencia de datos (DTO) para representar
 * información resumida de una orden.
 * <p>
 * Esta clase se emplea en el contexto de comunicación entre microservicios o controladores,
 * con el fin de enviar únicamente los datos necesarios y evitar exponer toda la entidad.
 * </p>
 */
@Getter
@Setter
public class OrderResponse {

    /**
     * Identificador único de la orden.
     */
    private Integer id;

    /**
     * Identificador del usuario asociado a la orden.
     * En este caso, corresponde al número de documento del usuario.
     */
    private Integer userId;

    /**
     * Fecha en que se realizó la orden.
     * Se representa solo con fecha (año-mes-día) sin información de hora.
     */
    private LocalDate date;
}