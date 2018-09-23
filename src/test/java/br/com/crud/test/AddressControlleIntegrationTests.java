package br.com.crud.test;

import br.com.crud.test.entity.Address;
import br.com.crud.test.service.AddressService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringApplication.class)
@AutoConfigureMockMvc
public class AddressControlleIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AddressService addressService;


    @Test
    public void addAddressTest_validDataThen201Code() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , "São Paulo", "São Paulo"
                        , "apto 102", "Santana");

        mvc.perform(post("/address/add")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void addAddressTest_validDataWithoutNeighborhoodThen201Code() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , "São Paulo", "São Paulo"
                        , "apto 102", null);

        mvc.perform(post("/address/add")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void addAddressTest_validDataWithoutComplementThen201Code() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , "São Paulo", "São Paulo"
                        , null, "Santana");

        mvc.perform(post("/address/add")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void addAddressTest_invalidStreetThen400Code() throws Exception {

        Address address =
                new Address(null, 2393, "02022-010"
                        , "São Paulo", "São Paulo"
                        , "apto 102", "Santana");

        mvc.perform(post("/address/add")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("details", containsString("The street field is required")));
    }

    @Test
    public void addAddressTest_invalidNumberThen400Code() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", null, "02022-010"
                        , "São Paulo", "São Paulo"
                        , "apto 102", "Santana");


        mvc.perform(post("/address/add")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("details", containsString("The number field is required")));

    }

    @Test
    public void addAddressTest_invalidPostalCodeThen400Code() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, null
                        , "São Paulo", "São Paulo"
                        , "apto 102", "Santana");


        mvc.perform(post("/address/add")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("details", containsString("The postal code field is required")));

    }

    @Test
    public void addAddressTest_invalidCityThen400Code() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , null, "São Paulo"
                        , "apto 102", "Santana");


        mvc.perform(post("/address/add")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("details", containsString("The city field is required")));

    }

    @Test
    public void addAddressTest_invalidStateThen400Code() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , "São Paulo", null
                        , "apto 102", "Santana");


        mvc.perform(post("/address/add")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("details", containsString("The state field is required")));

    }


    @Test
    public void updateAddressTest_validDataThen201Code() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , "São Paulo", "São Paulo"
                        , "apto 102", "Santana");

        address = addressService.addAddress(address);
        address.setStreet("Dr. Cesar");

        mvc.perform(put("/address/update")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("street", containsString("Dr. Cesar")));
    }

    @Test
    public void updateAddressTest_validDataWithoutComplement201Code() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , "São Paulo", "São Paulo"
                        , "apto 102", "Santana");

        address = addressService.addAddress(address);
        address.setStreet("Dr. Cesar");
        address.setComplement(null);

        mvc.perform(put("/address/update")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("street", containsString("Dr. Cesar")))
                .andExpect(jsonPath("complement", nullValue()));
    }

    @Test
    public void updateAddressTest_validDataWithoutNeighborhood201Code() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , "São Paulo", "São Paulo"
                        , "apto 102", "Santana");

        address = addressService.addAddress(address);
        address.setStreet("Dr. Cesar");
        address.setNeighborhood(null);

        mvc.perform(put("/address/update")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("street", containsString("Dr. Cesar")))
                .andExpect(jsonPath("neighborhood", nullValue()));
    }


    @Test
    public void updateAddressTest_invalidStreetThen400Code() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , "São Paulo", "São Paulo"
                        , "apto 102", "Santana");

        address = addressService.addAddress(address);
        address.setStreet(null);

        mvc.perform(put("/address/update")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("details", containsString("The street field is required")));
    }

    @Test
    public void updateAddressTest_invalidNumberThen400Code() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , "São Paulo", "São Paulo"
                        , "apto 102", "Santana");

        address = addressService.addAddress(address);
        address.setNumber(null);

        mvc.perform(put("/address/update")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("details", containsString("The number field is required")));
    }

    @Test
    public void updateAddressTest_invalidPostalCodeThen400Code() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , "São Paulo", "São Paulo"
                        , "apto 102", "Santana");

        address = addressService.addAddress(address);
        address.setPostalCode(null);

        mvc.perform(put("/address/update")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("details", containsString("The postal code field is required")));
    }

    @Test
    public void updateAddressTest_invalidCityThen400Code() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , "São Paulo", "São Paulo"
                        , "apto 102", "Santana");

        address = addressService.addAddress(address);
        address.setCity(null);

        mvc.perform(put("/address/update")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("details", containsString("The city field is required")));
    }

    @Test
    public void updateAddressTest_invalidStateThen400Code() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , "São Paulo", "São Paulo"
                        , "apto 102", "Santana");

        address = addressService.addAddress(address);
        address.setState(null);

        mvc.perform(put("/address/update")
                .content(asJsonString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("details", containsString("The state field is required")));
    }

    @Test
    public void deleteAllAddressTest() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , "São Paulo", "São Paulo"
                        , "apto 102", "Santana");

        addressService.addAddress(address);

        mvc.perform(delete("/address/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertThat(addressService.getAllAddress().size()).isZero();
    }

    @Test
    public void deleteAddressTest() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , "São Paulo", "São Paulo"
                        , "apto 102", "Santana");

        address = addressService.addAddress(address);

        mvc.perform(delete("/address/{id}", String.valueOf(address.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertThat(addressService.getAddressById(address.getId())).isEmpty();
    }

    @Test
    public void getAllAddressTest() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , "São Paulo", "São Paulo"
                        , "apto 102", "Santana");

        addressService.addAddress(address);

        MvcResult result = mvc.perform(get("/address/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        List<Address> actual = mapper.readValue(
                result.getResponse().getContentAsString(), new TypeReference<List<Address>>() {});

        assertThat(actual.size()).isGreaterThan(0);
    }

    @Test
    public void getAddressTest() throws Exception {

        Address address =
                new Address("Avenida Brás Leme", 2393, "02022-010"
                        , "São Paulo", "São Paulo"
                        , "apto 102", "Santana");

        address = addressService.addAddress(address);

        MvcResult result = mvc.perform(get("/address/{id}", String.valueOf(address.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonString = result.getResponse().getContentAsString();
        assertThat(asJsonString(address)).isEqualTo(jsonString);
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
