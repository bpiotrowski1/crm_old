package pl.bpiotrowski.crm.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SettingsDto {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    private String field1;

    @NotNull
    @Size(min = 3, max = 255)
    private String field2;

    @NotNull
    @Size(min = 3, max = 255)
    private String field3;

    @NotNull
    @Size(min = 3, max = 255)
    private String field4;

    private String field5;

    private String field6;

    private String field7;

    private String check1;

    private String check2;

    private String check3;

}
