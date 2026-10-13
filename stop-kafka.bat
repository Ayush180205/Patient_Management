@echo off
echo ==============================================
echo    Stopping Apache Kafka
echo ==============================================
for /f "tokens=5" %%a in ('netstat -aon ^| findstr ":9092" ^| findstr "LISTENING"') do (
    taskkill /f /pid %%a >nul 2>&1
    echo Kafka process terminated (PID: %%a).
)
echo Kafka is stopped.
pause
