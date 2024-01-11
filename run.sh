#!/bin/bash


while true; do
    read -p "Enter a query for ChatGPT (type 'exit' to quit): " query

    if [ "$query" == "exit" ]; then
        echo "Exiting the script."
        break
    fi

    ./gradlew quarkusDev -x test -Dquarkus.args="openai-cli -q '$query'"
done
