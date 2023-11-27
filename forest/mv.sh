#!/bin/bash

counter=310

for file in photo_*; do
    extension="${file##*.}"
    new_name="photo_${counter}.${extension}"
    
    mv "$file" "$new_name"
    
    counter=$((counter + 1))
done