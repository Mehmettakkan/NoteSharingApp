#!/bin/sh
# Export environment variables from .env file
export $(cat .env | xargs)

# Run the application
exec java -jar /app/NoteSharingApp.jar
