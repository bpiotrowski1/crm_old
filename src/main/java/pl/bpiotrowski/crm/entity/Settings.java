package pl.bpiotrowski.crm.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "settings")
public class Settings {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String field1;

    @Column(nullable = false)
    private String field2;

    @Column(nullable = false)
    private String field3;

    @Column(nullable = false)
    private String field4;

    @Column
    private String field5;

    @Column
    private String field6;

    @Column
    private String field7;

    @Column
    private String check1;

    @Column
    private String check2;

    @Column
    private String check3;

}
