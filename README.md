# slicetestexamples

examples for the slice test in Spring Boot with groovy

Till now i am unable to get the spock test for caching. it is unable to get the Spring Mock Bean.<br/>
These tests are ignored for now.

Real change is:
`@RunWith(SpringRunner.class)` will be changed to `@ContextConfiguration(classes = [<Application class name>])`