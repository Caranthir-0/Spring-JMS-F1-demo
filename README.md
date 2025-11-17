# Spring JMS F1 Telemetry & Pit-Stop Demo

This project demonstrates how to use **Spring Boot 3.x with Spring JMS**
to send and receive messages using **topics** and **queues** based on an
F1-inspired scenario.

It includes: continuous telemetry broadcasting from a car, multiple
subscribers (mechanics, driver, logger), routing logic and a
**request--response pit-stop approval flow**.

Messaging is powered by an **embedded ActiveMQ Artemis broker**.

## 🚀 Features

-   **JMS Topic** producer & multiple subscribers (F1 telemetry)
-   **JMS Queue** request--response messaging (Pit-Stop approval)
-   Custom telemetry and pit-stop message models
-   Scheduled periodic message sending
-   Message routing (producer → topic → router → consumers)
-   Embedded ActiveMQ Artemis (default) or support for external broker
-   JSON message conversion with Jackson
-   Async message listeners

## 🛠 Tech Stack

-   Java 17+
-   Spring Boot 3.x
-   Spring JMS
-   ActiveMQ Artemis (embedded)
-   Lombok
-   Jackson
-   Maven

## 📂 Project Structure

    src/main/java/edu/pja/sri/kobrebski/sri04jms/
    │
    ├── Sri04JmsApplication.java
    │
    ├── config/
    │   ├── JmsConfig.java .............. # JMS configuration, destinations, converters
    │   └── SchedulerConfig.java ........ # Async + scheduling configuration
    │
    ├── model/
    │   ├── MonitorPokladowyBolidu.java . # Telemetry model (engine, tyres, oil)
    │   ├── PitStopRequest.java ......... # Request for pit-stop
    │   └── PitStopResponse.java ........ # Response to pit-stop request
    │
    ├── producer/
    │   ├── MonitorPokladowyBoliduProducer.java  # Telemetry producer (topic)
    │   └── PitStopProducer.java ................ # Request–response producer (queue)
    │
    └── receiver/
        ├── MonitorPokladowyBoliduLogger.java ... # Basic topic subscriber
        ├── MonitorPokladowyBoliduRouter.java ... # Routes telemetry to driver/mechanics
        ├── DriverReceiver.java .................. # Driver receives routed messages
        ├── KrzysztofMechanicReceiver.java ....... # Mechanic #1
        └── RafałMechanicReceiver.java ............ # Mechanic #2

## ▶️ Running the Application

### 1. Build & Run

Using Maven wrapper:

    ./mvnw spring-boot:run

Or with installed Maven:

    mvn spring-boot:run

### 2. Requirements

No external broker required --- **Artemis runs embedded**.\
Configuration: `src/main/resources/application.properties`

## 📡 Messaging Scenarios

### 1. Telemetry Topic Messaging

Flow:

    Telemetry Producer
            ↓
       HELLO.TOPIC
            ↓
      Logger / Router / Driver / Mechanics

Classes involved:
- MonitorPokladowyBoliduProducer
- MonitorPokladowyBoliduLogger
- MonitorPokladowyBoliduRouter
- DriverReceiver
- KrzysztofMechanicReceiver 
- RafałMechanicReceiver

### 2. Pit-Stop Request--Response (Queue)

Flow:

    PitStopProducer
           ↓
     SEND_RECEIVE.QUEUE
           ↓
     PitStopReceiver   → approves/rejects → reply
           |                                    ↓
           ↑____________________________________|

Classes involved:
- PitStopProducer
- PitStopReceiver

## 🧪 Testing Messaging

Check console logs for: 
- Telemetry messages (every 10s) 
- Pit-stop request/response (every 15s) 
- Router forwarding 
- Multiple subscribers receiving data

## 🚀 Future Improvements

-   Add integration tests for JMS listeners
-   REST endpoints to trigger messages
-   Docker Compose with Artemis
-   Monitoring (JMX/Jolokia)
-   Error handling & retries
-   Persist telemetry and build dashboards

## 📘 What I Learned

-   How JMS integrates with Spring Boot
-   Queue vs topic semantics
-   Message converters & listener factories
-   Scheduling producers
-   Request--response with JMSReplyTo
-   Designing messaging flows with multiple subscribers
-   Routing logic for telemetry messages
