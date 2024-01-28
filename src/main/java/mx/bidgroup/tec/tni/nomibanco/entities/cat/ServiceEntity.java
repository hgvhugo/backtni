package mx.bidgroup.tec.tni.nomibanco.entities.cat;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "cat_servicio")
public class ServiceEntity implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "fecha_alta")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "fecha_modificacion")
    @UpdateTimestamp
    private LocalDateTime  modificationDate;


    @Column(name = "fecha_baja")
    private LocalDateTime deletionDate;

    @Column(name = "baja_logica")
    private Boolean lowLogic;

    
}