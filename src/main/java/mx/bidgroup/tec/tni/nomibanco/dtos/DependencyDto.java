package mx.bidgroup.tec.tni.nomibanco.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DependencyDto {

    private Long id;
    private String dependencyName;
    private String logo;
    private String address;
    private String rfc;
    private String directorHRName;
    private String ResponsibleCSName;
    private String ResponsibleBSName;
    private String ResponsibleISName;
    private Integer employeesNumber;
    private boolean administeredDecentralized;
    private Integer payrollsNumber;
    private Integer TNISNumber;
    private BigDecimal retainedPeriodAmount;
    private int TNISTimePay;
    private String liquidCapacityFormula;
    private String recoveryFormula;
    private boolean negativeViewLiquidCapacity;
    private boolean authorizationLetterAccessDataEmployees;
    private boolean more1CreditEmployee;
    private boolean creditsPreviousDateReinstatements;
    private boolean sameTNISCentralDecentralized;
    private boolean termOrBalance;
    private boolean globalBalances;
    private int maximumActiveReserveDays;
    private boolean authorizationLetterReserve;
    private int reserveBlockNumber;
    private boolean oldNewMovements;
    private boolean partialDiscounts;
    private boolean debtPurchase;
    private boolean discountsTermsSystemDependency;
    private boolean simulationModule;
    private boolean registrationOrGrouped;
    private boolean variousRecordsTNISWorker;
    private boolean registrationDiscountsCredits;
    private boolean haveEmployeesNumber;
    private boolean thirdPartiesLoadPreviousFiles;
    private int businessDaysLoadFiles;
    private String requiredDocumentConformationFile;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
        

}
