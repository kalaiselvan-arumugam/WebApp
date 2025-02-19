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
