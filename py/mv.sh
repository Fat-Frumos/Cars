#!/bin/bash

counter=193

for file in im_*; do
    extension="${file##*.}"
    new_name="image_${counter}.${extension}"
    
    mv "$file" "$new_name"
    
    counter=$((counter + 1))
done