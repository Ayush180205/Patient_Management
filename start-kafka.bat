@echo off
echo ==============================================
echo    Starting Apache Kafka (KRaft mode)
echo    Port: localhost:9092
echo ==============================================
cd /d C:\kafka
call .\bin\windows\kafka-server-start.bat .\config\server.properties
pause
