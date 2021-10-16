package alpha.project.controller;

import alpha.project.service.ExchangeRatesRetrieveService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Value("${giphy.rich}")
    private String richTag;
    @Value("${giphy.broke}")
    private String brokeTag;
    @Value("${giphy.zero}")
    private String whatTag;
    @Value("${giphy.error}")
    private String errorTag;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ExchangeRatesRetrieveService exchangeRatesService;
    @Autowired
    private  MainController mainController;

    @Test
    public void whenReturnRichGif() throws Exception {
        //prepare
        Mockito.when(exchangeRatesService.retrieveRatesAndCompare())
                .thenReturn(true);
        //when
        mainController.getGif();
        //assert no exception

    }

    @Test
    public void whenReturnBrokeGif() throws Exception {

        Mockito.when(exchangeRatesService.retrieveRatesAndCompare())
                .thenReturn(false);

        mainController.getGif();
    }
}