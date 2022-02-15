package pl.bpiotrowski.crm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.bpiotrowski.crm.dto.CustomerDto;
import pl.bpiotrowski.crm.service.CustomerService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static sun.plugin2.util.PojoUtil.toJson;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CustomerService customerService;

    @Test
    public void shouldReturnCustomersPage() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("customers"))
                .andExpect(model().attributeExists("customersList"))
                .andExpect(model().attributeExists("settings"));
    }

    @Test
    public void shouldReturnCustomersAddPage() throws Exception {
        mvc.perform(get("/customer/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("addCustomer"))
                .andExpect(model().attributeExists("customerForm"))
                .andExpect(model().attributeExists("settings"));
    }

    @Test
    public void shouldAddCustomer() throws Exception {
        CustomerDto dto = new CustomerDto();
        dto.setName("nameTest");

        mvc.perform(post("/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(dto)))
                .andExpect(status().isOk());

        verify(customerService).createCustomer(any(CustomerDto.class));
    }

    @Test
    public void shouldReturnEditPageWhenCustomerIsNotValid() throws Exception {
        CustomerDto customerDto = new CustomerDto();

        mvc.perform(post("/customer/add")
                .content(toJson(customerDto)))
                .andExpect(status().isOk())
                .andExpect(view().name("addCustomer"))
                .andExpect(model().attributeExists("customerDto"));

    }

    @Test
    public void shouldReturnCustomerEditPage() throws Exception {
        CustomerDto dto = new CustomerDto();
        dto.setId(0L);
        dto.setName("nameTest");

        when(customerService.createCustomer(Mockito.any(CustomerDto.class))).thenReturn(dto);
        when(customerService.findById(Mockito.any(Long.class))).thenReturn(dto);

        mvc.perform(get("/customer/edit/" + dto.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("editCustomer"))
                .andExpect(model().attribute("customerForm", dto))
                .andExpect(model().attributeExists("settings"));
    }

    @Test
    public void shouldEditCustomer() throws Exception {
        CustomerDto dto = new CustomerDto();
        dto.setName("nameTest");

        mvc.perform(post("/customer/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(dto)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnEditPageWhenCustomerIsNotValidAfterPreviousEdit() throws Exception {
        CustomerDto customerDto = new CustomerDto();

        mvc.perform(post("/customer/edit")
                        .content(toJson(customerDto)))
                .andExpect(status().isOk())
                .andExpect(view().name("editCustomer"))
                .andExpect(model().attributeExists("customerDto"))
                .andExpect(model().attributeExists("settings"));
    }

}
