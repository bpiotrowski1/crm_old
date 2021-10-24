package pl.bpiotrowski.crm.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String field1;

    @Column
    private String field2;

    @Column
    private String field3;

    @Column
    private String field4;

    @Column
    private String field5;

    @Column
    private String field6;

    @Column
    private String field7;

    @Column(nullable = false)
    private boolean check1;

    @Column(nullable = false)
    private boolean check2;

    @Column(nullable = false)
    private boolean check3;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String lastUpdate;

}
