#### Daniel Barrell
## Whitbread Coding Challenge

To build: `mvn clean install`

To test: `mvn test`

To run: `mvn spring-boot:run`

- Application will be started at address `localhost:8080`
- An example request could be `localhost:8080/venues/london`
- The output will be a list of location names near the location you specified
- I have left my foursquare api-key in the properties file for your convenience. Fingers crossed it doesn't get crawled by somebody!
- This application is little more than a proxy. If I were to extend it further I would add Guava caching, SLF4J logging and Hystrix circuit-breaking



