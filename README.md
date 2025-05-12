# Sporty Assignment

## How to Run and Test the Application Locally

### Prerequisites
1. **Java Development Kit (JDK)**: Ensure JDK 17 or later is installed.
2. **Maven**: Ensure Apache Maven is installed and added to your system's PATH.
3. **Kafka**: Install and run Apache Kafka locally (localhost:9092).

### Steps to Run the Application

1. **Clone the Repository**
   ```bash
   git clone https://github.com/prakharraj/sporty.git
   cd sporty
    ```
2. **Build the Application**
   ```bash
   mvn clean install
   ``` 
3. **Run the Kafka server locally**
   Run Kafka Start the Kafka server locally:
    
    Start Zookeeper
    ```bash
   bin/zookeeper-server-start.sh config/zookeeper.properties
   ```

    Start Kafka
    ```bash
    bin/kafka-server-start.sh config/server.properties
    ```
4. **Run the Application with non prod profile (com.sporty.SportyApplication)** 


5. **Test the Application**
   - Use Postman or any other API testing tool to test the endpoints.
   - The application exposes a REST API for various operations.
   - Path - http://localhost:8080/api/events/outcome
   ```json
   {
      "eventId": 2001,
      "eventName": "Championship Final",
      "eventWinnerId": 67890
   }
    ```
   - Sample predefined data available - 
     - `Bet(betId=1, userId=1001, eventId=2001, eventMarketId=3001, eventWinnerId=4001, betAmount=50.00)`
     - `Bet(betId=2, userId=1002, eventId=2002, eventMarketId=3002, eventWinnerId=4002, betAmount=75.50)`
     - `Bet(betId=3, userId=1003, eventId=2003, eventMarketId=3003, eventWinnerId=4003, betAmount=120.00)`