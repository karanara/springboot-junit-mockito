# springboot-junit-mockito

@SpringBootTest(classes=smapleapplication.class)--generally the main class
   loads the applicationContext
   Spring Dependency injection
   can access data from application properties[@Value("${info.app.name}")]
   spring-boot-starter-test ===dependency

Mockito:
     technique of using test doubles-- mocking
     set expections with mock responses
     minimize the dependencies of DAO,DB
     setup--execute--assert--verify
steps:
     create Mock for DAO
     inject mock into service
     set up exceptations
     call method under test and assert results
     verify method calls
@Mock -- mocking the DAO
@InjectMocks --- injecting mock dependencies--will only inject dependencies annotated with @Mock or @Spy
when ().thenReturn
when().thenThrow
verify(methodName,times())
Instead of using Mockito: @Mock and @InjectMocks
Use Spring Boot support: @MockBean and @Autowired

@MockBean
• includes Mockito @Mock functionality
• also adds mock bean to Spring ApplicationContext
• if existing bean is there, the mock bean will replace it
• thus making the mock bean available for injection with @Autowired

ReflectionTestUtils --utilty class by Spring allows us to set/get methods of private field
                       invoke private methods too
                       ReflectionTestUtils.setField
                                           .getField
                                            .invokeMethod
