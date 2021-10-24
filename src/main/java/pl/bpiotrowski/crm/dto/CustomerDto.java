package pl.bpiotrowski.crm.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CustomerDto {

    @NotNull
    private Long id;

    @Size(min = 3, max = 255)
    private String name;

    @Size(max = 255)
    private String field1;

    @Size(max = 255)
    private String field2;

    @Size(max = 255)
    private String field3;

    @Size(max = 255)
    private String field4;

    @Size(max = 255)
    private String field5;

    @Size(max = 255)
    private String field6;

    @Size(max = 255)
    private String field7;

    @NotNull
    private boolean check1;

    @NotNull
    private boolean check2;

    @NotNull
    private boolean check3;

    @Size(max = 1000)
    private String description;

    private String lastUpdate;

}
