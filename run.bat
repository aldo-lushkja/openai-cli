@echo off
setlocal enabledelayedexpansion

:mainLoop
set /p "query=Enter a query for ChatGPT (type 'exit' to quit): "

if "!query!" == "exit" (
    echo Exiting the script.
    goto :eof
)

gradlew.bat quarkusDev -x test -Dquarkus.args="openai -q '!query!'"
goto mainLoop
