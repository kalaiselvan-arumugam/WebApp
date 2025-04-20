``` xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration monitorInterval="30">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout charset="UTF-8" pattern="%d{yyyy-MM-dd HH:mm:ss SSS} %-5level %logger{36}.%M() @%L : %msg%n"/>
        </Console>
        <RollingRandomAccessFile name="AsyncAppender">
            <FileName>D:/ApplicationLogs/WebApp/ApplicationLogs.log</FileName>
            <FilePattern>D:/ApplicationLogs/WebApp/Archive/%d{yyyy-MM-dd-hh}-%i.log.zip</FilePattern>
            <PatternLayout charset="UTF-8" pattern="%d{yyyy-MMM-dd HH:mm:ss SSS} %-5level %logger{36}.%M() @%L : %msg%n"/>
            <Policies>
                <SizeBasedTriggeringPolicy size="75500KB"/>
            </Policies>
            <DefaultRolloverStrategy max="200"/>
        </RollingRandomAccessFile>
        <Async name="Async">
            <AppenderRef ref="AsyncAppender"/>
        </Async>
    </Appenders>
    <Loggers>
        <Logger name="com.webapp" level="debug" additivity="false">
            <AppenderRef ref="AsyncAppender"/>
            <AppenderRef ref="Console"/>
        </Logger>
        <Root level="trace"></Root>
    </Loggers>
</Configuration>
```


As part of the recent knowledge transfer session, I wanted to summarize my current understanding of the NuanceTTS application flows for your confirmation. Please let me know if I have missed or misunderstood anything.

## Flow 1: TAP to NuanceTTS to Moxtra

The TAP application initiates a POST request to the NuanceTTS application with three files in the body:

- Two JSON files
- One PDF file

The NuanceTTS application:

- Performs a sanity check on the JSON files.
- Creates a folder based on a specific value from the JSON payload (e.g., a unique name or ID).
- Saves all three files in the newly created folder.
- Makes a REST API call to the Moxtra application to inform or trigger the next step.

## Flow 2: CSV File Upload and Validation

A CSV file is uploaded via the Nuance File Uploader application.

The application:

- Performs validation on the CSV file using a data dictionary, which outlines valid fields and their expected formats or values.
- Upon successful validation, the records are persisted to the database.

## Request

If available, could you please share:

- The list of fields that are validated from the CSV.
- Which fields are mandatory and which are optional.


``` mermaid
graph TD
    A1[TAP Application] -->|POST: 2 JSONs + 1 PDF| B1[NuanceTTS Application]
    B1 --> C1{Sanity Check JSON}
    C1 -->|Passed| D1[Create Folder & Save Files]
    D1 --> E1[Call Moxtra REST API]

    A2[User Uploads CSV] --> B2[Nuance File Uploader App]
    B2 --> C2{Validate CSV with Data Dictionary}
    C2 -->|Passed| D2[Save Records to DB]
```
