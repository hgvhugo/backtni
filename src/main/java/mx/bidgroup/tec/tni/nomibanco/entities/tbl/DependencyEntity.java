package mx.bidgroup.tec.tni.nomibanco.entities.tbl;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "tbl_Dependencia")
public class DependencyEntity implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_Dependencia")
    private Long id;
    
    @Column(name = "Nombre_dependencia")
    private String dependencyName;
 
    @Column(name = "logo")
    private String logo;
    
    @Column(name = "Domicilio")
    private String address;

    @Column(name = "RFC")
    private String rfc;

    @Column(name = "Nombre_director_recursos_humanos")
    private String  directorHRName;

    @Column(name = "Responsable_sueldos_compensaciones")
    private String ResponsibleCSName;

    @Column(name = "Responsable_servicios_beneficios")
    private String ResponsibleBSName;

    @Column(name = "Responsable_sistemas_informaticos")
    private String ResponsibleISName;

    @Column(name = "Numero_Empleados")
    private Integer employeesNumber;

    @Column(name = "Descentralizados_Administrados")
    private boolean administeredDecentralized;

    @Column(name = "Numero_Nominas")
    private Integer payrollsNumber;

    @Column(name = "Numero_TNIS")
    private Integer TNISNumber;

    @Column(name = "Monto_retenido_periodo")
    private BigDecimal retainedPeriodAmount;

    @Column(name = "Tiempo_pago_TNIS")
    private int TNISTimePay;

    @Column(name = "Formula_Capacidad_Liquida")
    private String liquidCapacityFormula;

    @Column(name = "Formula_Recuperacion")
    private String recoveryFormula;

    @Column(name = "Permitir_Vistanegativo_Capacidad_Liquida")
    private boolean negativeViewLiquidCapacity;

    @Column(name = "Carta_Autorizacion_Acceso_DatosEmpleados")
    private boolean authorizationLetterAccessDataEmployees;

    @Column(name = "Mas1credito_empleado")
    private boolean more1CreditEmployee;

    @Column(name = "Reinstalaciones_creditos_fechaanterior")
    private boolean creditsPreviousDateReinstatements;

    @Column(name = "Mismas_TNIS_central_descentralizada")
    private boolean sameTNISCentralDecentralized;

    @Column(name = "Plazo_o_Saldo")
    private boolean termOrBalance;

    @Column(name = "Saldos_globales")
    private boolean globalBalances;

    @Column(name = "Maximo_Dias_reserva_activa")
    private int maximumActiveReserveDays;

    @Column(name = "Carta_Autorizacion_Reserva")
    private boolean authorizationLetterReserve;

    @Column(name = "Numero_bloqueo_reserva")
    private int reserveBlockNumber;

    @Column(name = "Movimientos_viejo_nuevo")
    private boolean oldNewMovements;

    @Column(name = "Descuentos_Parciales")
    private boolean partialDiscounts;

    @Column(name = "Compra_deuda")
    private boolean debtPurchase;

    @Column(name = "Descuentos_plazos_sistema_o_Dependencia")
    private boolean discountsTermsSystemDependency;

    @Column(name = "Modulo_Simulacion")
    private boolean simulationModule;

    @Column(name = "Registro_o_Agrupados")
    private boolean registrationOrGrouped;

    @Column(name = "Varios_registros_TNIS_mismo_trabajador")
    private boolean variousRecordsTNISWorker;

    @Column(name = "Registro_descuentos_creditos")
    private boolean registrationDiscountsCredits;

    @Column(name = "Cuentan_Con_Numero_empleados")
    private boolean haveEmployeesNumber;

    @Column(name = "Terceros_cargan_expedientes_anteriores")
    private boolean thirdPartiesLoadPreviousFiles;

    @Column(name = "Dias_habiles_carga_expedientes")
    private int businessDaysLoadFiles;

    @Column(name = "Documento_requeridos_conformacion_expediente")
    private String requiredDocumentConformationFile;

    @Column(name = "Fecha_Alta")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "Fecha_Modificacion")
    @UpdateTimestamp
    private LocalDateTime modificationDate;
        
}