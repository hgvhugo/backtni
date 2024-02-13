package mx.bidgroup.tec.tni.nomibanco.entities.tbl;

import jakarta.persistence.*;
import java.io.Serializable;
// import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
// import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import lombok.*;
import mx.bidgroup.tec.tni.nomibanco.entities.cat.ServiceEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_Solicitudes")
public class QuejasSolicitudesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Solicitud")
    private Long id;

    @Column(name = "ID_Servicio")
    private Long idServicio;

    @Column(name = "ID_TNI")
    private Long idTni;

    @Column(name = "ID_Usuario")
    private Long idUsuario;

    @Column(name = "Estatus")
    private Long estatus;

    @Column(name = "Fecha_Alta")
    @CreationTimestamp
    private LocalDateTime fecha_alta;

    @Column(name = "Fecha_Modificacion")
    @UpdateTimestamp
    private LocalDateTime fecha_modificacion;

    @MapsId("id_servicio")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_servicio", referencedColumnName = "id_servicio")
    private ServiceEntity servicio;
}