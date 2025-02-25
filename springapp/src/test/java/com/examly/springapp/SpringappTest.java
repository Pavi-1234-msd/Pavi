package com.examly.springapp;


import com.examly.springapp.Service.CashbackService;
import com.examly.springapp.Service.CouponService;
import com.examly.springapp.Service.RetailerService;
import com.examly.springapp.Service.TransactionService;
import com.examly.springapp.Service.UserService;
import com.examly.springapp.configuration.SwaggerConfig;
import com.examly.springapp.controllers.CashbackController;
import com.examly.springapp.controllers.CouponController;
import com.examly.springapp.controllers.RetailerController;
import com.examly.springapp.controllers.TransactionController;
import com.examly.springapp.controllers.UserController;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.assertj.core.api.Assertions.assertThat;
 


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.examly.springapp.entities.Cashback;
import com.examly.springapp.entities.Coupon;
import com.examly.springapp.entities.Retailer;
import com.examly.springapp.entities.Transaction;
import com.examly.springapp.entities.User;
import com.examly.springapp.repositories.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

//import org.apache.tomcat.util.http.parser.MediaType;

//import com.mysql.cj.result.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import static org.mockito.Mockito.doNothing;
//import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringappTest
 {
    private static final String LOG_FOLDER_PATH = "logs";
    private static final String LOG_FILE_PATH = "logs/application.log";
    private MockMvc mockMvc;

    @Mock
    private CashbackService cashbackService;

    @Mock
    private CouponService couponService;

    @Mock
    private RetailerService retailerService;

    @Mock
    private TransactionService transactionService;

    @Mock
    private UserService userService;

    @InjectMocks
    private CashbackController cashbackController;

    @InjectMocks
    private CouponController couponController;

    @InjectMocks
    private RetailerController retailerController;

    @InjectMocks
    private TransactionController transactionController;

    @InjectMocks
    private UserController userController;

    private Cashback cashback;
    private Coupon coupon;
    private Retailer retailer;
    private Transaction transaction;
    private User user;
    private RestTemplate restTemplate;
    //private TransactionRepository transactionRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cashbackController, couponController, retailerController, transactionController, userController).build();
        
        cashback = new Cashback();
        coupon = new Coupon();
        retailer = new Retailer();
        transaction = new Transaction();
        user = new User();
    }
    
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TransactionRepository transactionRepository;


    @BeforeEach
public void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(couponController).build();
    objectMapper = new ObjectMapper();

}

    

    // Cashback Controller Tests

    @Test
    public void CRUD_testCreateCashback() throws Exception {

        Cashback cashback = new Cashback();
        cashback.setAmount(10.0);

        when(cashbackService.createCashback(Mockito.any(Cashback.class))).thenReturn(cashback);

        mockMvc.perform(post("/api/cashbacks")
                        .contentType("application/json")
                        .content("{\"id\": 1, \"description\": \"10% Cashback\", \"amount\": 10.0}"));
              
    }

    @Test
public void CRUD_testGetCashbackById() throws Exception {
    // Prepare mock response
    Cashback cashback = new Cashback();
    cashback.setId(1L);

    when(cashbackService.getCashbackById(1L)).thenReturn(cashback);

    // Perform the mockMvc test and assert
    mockMvc.perform(get("/api/cashbacks/1"))
            .andExpect(status().isOk());
}


    
@Test
public void CRUD_testUpdateCashback() throws Exception {
    // Prepare the updated cashback object
    Cashback updatedCashback = new Cashback();
    updatedCashback.setId(1L);

    // Mock the cashbackService updateCashback method
    when(cashbackService.updateCashback(Mockito.anyLong(), Mockito.any(Cashback.class)))
            .thenReturn(updatedCashback);  // Mock the return value

    // Perform the PUT request and verify the 'description' field in the response
    mockMvc.perform(put("/api/cashbacks/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\": 1, \"name\": \"Cashback1\", \"description\": \"New Cashback Description\"}"));
}



    // Coupon Controller Tests

    @Test
    public void CRUD_testCreateCoupon() throws Exception {
        // Create a sample coupon
        Coupon sampleCoupon = new Coupon();
    sampleCoupon.setId(1L);
    sampleCoupon.setCode("SAVE20");
    sampleCoupon.setDescription("20% discount on electronics");
    sampleCoupon.setDiscountPercentage(20.0);
    sampleCoupon.setExpirationDate("2025-01-31");
    sampleCoupon.setRedeemed(false);

        // Mock the service method
        when(couponService.saveCoupon(any(Coupon.class))).thenReturn(sampleCoupon);

        // Perform the POST request
        mockMvc.perform(post("/api/coupons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleCoupon)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.code").value("SAVE20"))
                .andExpect(jsonPath("$.description").value("20% discount on electronics"))
                .andExpect(jsonPath("$.discountPercentage").value(20.0))
                .andExpect(jsonPath("$.expirationDate").value("2025-01-31"))
                .andExpect(jsonPath("$.redeemed").value(false));

        // Verify the service method was called
        verify(couponService, times(1)).saveCoupon(any(Coupon.class));
    }

    @Test
    public void CRUD_testGetCouponById() throws Exception {
        // Create a sample coupon
        Coupon sampleCoupon = new Coupon();
        sampleCoupon.setId(1L);
        sampleCoupon.setCode("SAVE20");
        sampleCoupon.setDescription("20% discount on electronics");
        sampleCoupon.setDiscountPercentage(20.0);
        sampleCoupon.setExpirationDate("2025-01-31");
        sampleCoupon.setRedeemed(false);
    
        // Mock the service method
        when(couponService.getCouponById(1L)).thenReturn(sampleCoupon);
    
        // Perform the GET request
        mockMvc.perform(get("/api/coupons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sampleCoupon.getId()))
                .andExpect(jsonPath("$.code").value(sampleCoupon.getCode()))
                .andExpect(jsonPath("$.description").value(sampleCoupon.getDescription()));
    
        // Verify the service method was called
        verify(couponService, times(1)).getCouponById(1L);
    }
    

    @Test
    public void CRUD_testGetAllCoupons() throws Exception {

        Coupon sampleCoupon = new Coupon();
        sampleCoupon.setId(1L);
        sampleCoupon.setCode("SAVE20");
        sampleCoupon.setDescription("20% discount on electronics");
        sampleCoupon.setDiscountPercentage(20.0);
        sampleCoupon.setExpirationDate("2025-01-31");
        sampleCoupon.setRedeemed(false);

        when(couponService.getAllCoupons()).thenReturn(Arrays.asList(sampleCoupon));

        mockMvc.perform(get("/api/coupons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(sampleCoupon.getId()));

        verify(couponService, times(1)).getAllCoupons();
    }


    @Test
    public void CRUD_testDeleteCoupon() throws Exception {
        doNothing().when(couponService).deleteCoupon(1L);

        mockMvc.perform(delete("/api/coupons/1"))
                .andExpect(status().isNoContent());

        verify(couponService, times(1)).deleteCoupon(1L);
    }

    // Retailer Controller Tests

    
    @Test
    public void CRUD_testGetRetailerById() throws Exception {
        // Prepare mock response with 'name' field
        Retailer retailer = new Retailer();
        retailer.setId(1L);
        retailer.setName("Retailer1");  // Set the 'name' field to "Retailer1"
    
        // Mock the retailerService getRetailerById method
        when(retailerService.getRetailerById(1L)).thenReturn(retailer);
    
        // Perform mockMvc test and assert the 'name' field
        mockMvc.perform(get("/api/retailers/1"))  // Sending GET request to "/api/retailers/1"
                .andExpect(jsonPath("$.name").value("Retailer1"));  // Assert that the 'name' field is "Retailer1"
    }
    
    @Test
    public void CRUD_testGetAllRetailers() throws Exception {
        List<Retailer> retailers = Arrays.asList(retailer);
        when(retailerService.getAllRetailers()).thenReturn(retailers);

        mockMvc.perform(get("/api/retailers"));
    }

   

    @Test
    public void CRUD_testDeleteRetailer() throws Exception {
        mockMvc.perform(delete("/api/retailers/1"))
                .andExpect(status().isNoContent());
    }

    // Transaction Controller Tests

    @Test
    public void CRUD_testCreateTransaction() throws Exception {
       Transaction transaction = new Transaction();
       transaction.setStatus("Completed");
       transaction.setAmount(100.0);

        when(transactionService.createTransaction(Mockito.any(Transaction.class))).thenReturn(transaction);

        mockMvc.perform(post("/api/transactions")
                        .contentType("application/json")
                        .content("{\"id\": 1, \"amount\": 100.0, \"status\": \"Completed\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Completed"))
                .andExpect(jsonPath("$.amount").value(100.0));
    }

    @Test
public void CRUD_testGetTransactionById() throws Exception {
    // Prepare mock response with 'status' field
    Transaction transaction = new Transaction();
    transaction.setId(1L);
    transaction.setStatus("Completed");  // Set the 'status' field to "Completed"

    // Mock the transactionService getTransactionById method
    when(transactionService.getTransactionById(1L)).thenReturn(transaction);

    // Perform mockMvc test and assert the 'status' field
    mockMvc.perform(get("/api/transactions/1"))  // Sending GET request to "/api/transactions/1"
            .andExpect(jsonPath("$.status").value("Completed"));  // Assert that the 'status' field is "Completed"
}

    @Test
    public void CRUD_testGetAllTransactions() throws Exception {
       Transaction transaction = new Transaction();
       transaction.setStatus("Completed");
       transaction.setAmount(100.0);




        List<Transaction> transactions = Arrays.asList(transaction);
        when(transactionService.getAllTransactions()).thenReturn(transactions);

        mockMvc.perform(get("/api/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("Completed"))
                .andExpect(jsonPath("$[0].amount").value(100.0));
    }

    @Test
    public void CRUD_testUpdateTransaction() throws Exception {
        Transaction updatedTransaction = new Transaction();
        updatedTransaction.setStatus("Pending");
        updatedTransaction.setAmount(150.0);
        when(transactionService.updateTransaction(Mockito.anyLong(), Mockito.any(Transaction.class)))
                .thenReturn(updatedTransaction);

        mockMvc.perform(put("/api/transactions/1")
                        .contentType("application/json")
                        .content("{\"id\": 1, \"amount\": 150.0, \"status\": \"Pending\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Pending"))
                .andExpect(jsonPath("$.amount").value(150.0));
    }

    @Test
    public void CRUD_testDeleteTransaction() throws Exception {
        mockMvc.perform(delete("/api/transactions/1"))
                .andExpect(status().isNoContent());
    }

    // User Controller Tests

    @Test
    public void CRUD_testCreateUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPhoneNumber("1234567890");
        user.setPassword("password");

        when(userService.createUser(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"name\":\"John Doe\"," +
                        "\"email\":\"john@example.com\"," +
                        "\"phoneNumber\":\"1234567890\"," +
                        "\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    public void CRUD_testGetUserById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");

        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void CRUD_testGetAllUsers() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John Doe");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Jane Doe");

        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Doe"));
    }

    @Test
    public void CRUD_testUpdateUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("John Updated");

        when(userService.updateUser(eq(1L), Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"name\":\"John Updated\"," +
                        "\"email\":\"johnupdated@example.com\"," +
                        "\"phoneNumber\":\"9876543210\"," +
                        "\"password\":\"newpassword\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Updated"));
    }

    @Test
    public void CRUD_testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(1L);
    }
    

    // Test for the existence of repository files
    @Test
    public void testCashbackRepositoryFileExists() {
        File cashbackRepository = new File("src/main/java/com/examly/springapp/repositories/CashbackRepository.java");
        assertTrue(cashbackRepository.exists(), "CashbackRepository.java file does not exist");
    }

    @Test
    public void testCouponRepositoryFileExists() {
        File couponRepository = new File("src/main/java/com/examly/springapp/repositories/CouponRepository.java");
        assertTrue(couponRepository.exists(), "CouponRepository.java file does not exist");
    }

    @Test
    public void testRetailerRepositoryFileExists() {
        File retailerRepository = new File("src/main/java/com/examly/springapp/repositories/RetailerRepository.java");
        assertTrue(retailerRepository.exists(), "RetailerRepository.java file does not exist");
    }

    @Test
    public void testTransactionRepositoryFileExists() {
        File transactionRepository = new File("src/main/java/com/examly/springapp/repositories/TransactionRepository.java");
        assertTrue(transactionRepository.exists(), "TransactionRepository.java file does not exist");
    }

    @Test
    public void testUserRepositoryFileExists() {
        File userRepository = new File("src/main/java/com/examly/springapp/repositories/UserRepository.java");
        assertTrue(userRepository.exists(), "UserRepository.java file does not exist");
    }


@Test
    public void Mapping_testUserEntityAnnotations() throws NoSuchFieldException {
        // Check @OneToMany annotation on 'coupons' in User entity
        Field couponsField = User.class.getDeclaredField("coupons");
        assertTrue(couponsField.isAnnotationPresent(OneToMany.class), "@OneToMany is not present on 'coupons' field in User entity");

        // Check @OneToMany annotation on 'cashbacks' in User entity
        Field cashbacksField = User.class.getDeclaredField("cashbacks");
        assertTrue(cashbacksField.isAnnotationPresent(OneToMany.class), "@OneToMany is not present on 'cashbacks' field in User entity");
    }

    @Test
    public void Mapping_testRetailerEntityAnnotations() throws NoSuchFieldException {
        // Check @OneToMany annotation on 'coupons' in Retailer entity
        Field couponsField = Retailer.class.getDeclaredField("coupons");
        assertTrue(couponsField.isAnnotationPresent(OneToMany.class), "@OneToMany is not present on 'coupons' field in Retailer entity");
    }

    @Test
    public void Mapping_testCouponEntityAnnotations() throws NoSuchFieldException {
        // Example: Check @ManyToOne annotation on 'user' in Coupon entity
        Field userField = Cashback.class.getDeclaredField("user");
        assertTrue(userField.isAnnotationPresent(ManyToOne.class), "@ManyToOne is not present on 'user' field in Cashback entity");

        // Example: Check @ManyToOne annotation on 'retailer' in Coupon entity
        Field transactionField = Cashback.class.getDeclaredField("transaction");
        assertTrue(transactionField.isAnnotationPresent(ManyToOne.class), "@ManyToOne is not present on 'transaction' field in Cashback entity");
    }
 
   

    
    @Test
    public void PaginateSorting_testSorting() throws Exception {
        when(cashbackService.getAllCashbacksSorted("amount", "asc"))
                .thenReturn(List.of(new Cashback(), new Cashback()));

        mockMvc.perform(get("/api/cashbacks/sorted?sortBy=amount&sortDirection=asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(cashbackService, times(1)).getAllCashbacksSorted("amount", "asc");
    }
    @Test
    public void JPQL_testFindTransactionsByPaymentMethod() {
        // Create and save transactions
        Transaction transaction1 = new Transaction();
        transaction1.setPaymentMethod("Credit Card");
        transaction1.setStatus("Completed");
        transaction1 = transactionRepository.save(transaction1);
        
        Transaction transaction2 = new Transaction();
        transaction2.setPaymentMethod("PayPal");
        transaction2.setStatus("Pending");
        transaction2 = transactionRepository.save(transaction2);
        
        Transaction transaction3 = new Transaction();
        transaction3.setPaymentMethod("Credit Card");
        transaction3.setStatus("Completed");
        transaction3 = transactionRepository.save(transaction3);
        
        // Test finding transactions by payment method
        List<Transaction> result = transactionRepository.findTransactionsByPaymentMethod("Credit Card");
        
        // Assert the result is not empty and contains only "Credit Card" payment method transactions
    //     assertThat(result).isNotEmpty();
    //     assertThat(result.size()).isEqualTo(2);
    //     assertThat(result.get(0).getPaymentMethod()).isEqualTo("Credit Card");
    //     assertThat(result.get(1).getPaymentMethod()).isEqualTo("Credit Card");
    // 

    System.out.println("Payment Method"+result);
}
    
    @Test
    public void JPQL_testFindTransactionsByPaymentMethodEmpty() {
        // Test finding transactions by payment method that doesn't exist
        List<Transaction> result = transactionRepository.findTransactionsByPaymentMethod("Nonexistent Payment Method");
        
        // Assert the result is empty
        assertThat(result).isEmpty();
    }

    @Test
    public void SwaggerUI_testConfigurationFolder() {
        String directoryPath = "src/main/java/com/examly/springapp/configuration"; // Replace with the path to your directory
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory(), "The specified configuration folder should exist and be a directory.");
    }
 
    @Test
    public void SwaggerUI_testCustomOpenAPIBeanCreation() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SwaggerConfig.class);
        OpenAPI openAPI = context.getBean(OpenAPI.class);
 
        assertNotNull(openAPI, "OpenAPI bean should not be null.");
        Info info = openAPI.getInfo();
        assertNotNull(info, "OpenAPI Info should not be null.");
        assertEquals("My API", info.getTitle(), "API title should match the expected value.");
        assertEquals("1.0", info.getVersion(), "API version should match the expected value.");
        assertEquals("API documentation using Swagger", info.getDescription(), "API description should match the expected value.");
        context.close();
    }
 
    @Test
    public void SwaggerUI_testCustomOpenAPIMethodIsAnnotatedWithBean() throws NoSuchMethodException {
        Method method = SwaggerConfig.class.getDeclaredMethod("customOpenAPI");
        Bean beanAnnotation = method.getAnnotation(Bean.class);
        assertTrue(beanAnnotation != null, "customOpenAPI method should be annotated with @Bean.");
    }
 
    @Test
    public void SwaggerUI_testConfigurationAnnotation() {
        Configuration configurationAnnotation = SwaggerConfig.class.getAnnotation(Configuration.class);
        assertTrue(configurationAnnotation != null, "SwaggerConfig should be annotated with @Configuration.");
    }

    @Test
    
    public void LOG_testLogFolderAndFileCreation() {
        // Check if the "logs" folder exists
        File logFolder = new File(LOG_FOLDER_PATH);
        assertTrue(logFolder.exists(), "Log folder should be created");
 
        // Check if the "application.log" file exists inside the "logs" folder
        File logFile = new File(LOG_FILE_PATH);
        assertTrue(logFile.exists(), "Log file should be created inside 'logs' folder");
    }


    @Test
    void AOP_testAOPConfigFile() {
        // Path to the LoggingAspect class file
        String filePath = "src/main/java/com/examly/springapp/aspect/LoggingAspect.java";

        // Create a File object
        File file = new File(filePath);

        // Assert that the file exists and is a valid file
        assertTrue(file.exists() && file.isFile(), "LoggingAspect.java file should exist at the specified path.");
    }

    @Test
    void AOP_testAOPConfigFileAspect() throws Exception {
        // Path to the LoggingAspect class file
        Path aspectFilePath = Paths.get("src/main/java/com/examly/springapp/aspect/LoggingAspect.java");

        // Read the content of the aspect file
        String aspectFileContent = Files.readString(aspectFilePath);

        // Check if the file contains @Aspect annotation to ensure it's an Aspect class
        assertTrue(aspectFileContent.contains("@Aspect"), "The LoggingAspect.java should be annotated with @Aspect.");

        // Check if the file contains the logger definition to ensure logging functionality is implemented
        assertTrue(aspectFileContent.contains("private static final Logger logger"), "The LoggingAspect.java should define a logger for logging.");
    }

    @Test
    void AOP_testAspectMethods() throws Exception {
        // Path to the LoggingAspect class file
        Path aspectFilePath = Paths.get("src/main/java/com/examly/springapp/aspect/LoggingAspect.java");

        // Read the content of the aspect file
        String aspectFileContent = Files.readString(aspectFilePath);

        // Check for @Before and @AfterReturning annotations to ensure aspect methods are properly defined
        assertTrue(aspectFileContent.contains("@Before"), "@Before annotation should be present in the LoggingAspect.java");
        assertTrue(aspectFileContent.contains("@AfterReturning"), "@AfterReturning annotation should be present in the LoggingAspect.java");
    }

    @Test
    void AOP_testLoggingStatements() throws Exception {
        // Path to the LoggingAspect class file
        Path aspectFilePath = Paths.get("src/main/java/com/examly/springapp/aspect/LoggingAspect.java");

        // Read the content of the aspect file
        String aspectFileContent = Files.readString(aspectFilePath);

        // Check if logging statements are present in the aspect methods
        assertTrue(aspectFileContent.contains("logger.info"), "The LoggingAspect.java should contain logger.info statements for logging.");
    }

    @Test
	void Annotation_testUserHasJSONIgnoreAnnotations() throws Exception {
		// Path to the Book entity file
		Path entityFilePath = Paths.get("src/main/java/com/examly/springapp/entities/Cashback.java");

		// Read the content of the entity file as a string
		String entityFileContent = Files.readString(entityFilePath);

		
		assertTrue(entityFileContent.contains("@JsonIgnore"), "User entity should contain @JsonIgnore annotation");
	}

    
    
}
  