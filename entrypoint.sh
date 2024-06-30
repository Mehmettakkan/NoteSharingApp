#!/bin/sh
# Export environment variables from .env file
export $(grep -v '^#' .env | xargs)

# Run the application
exec java -jar /app/NoteSharingApp.jar