#!/bin/bash

mysql -h mysql -u root -p devsu-database -e "SELECT 1" &> /dev/null

if [[ $? -eq 0 ]]; then
    exit 0  # Success
else
    exit 1  # Failure
fi