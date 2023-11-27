from PIL import Image
import os

# Specify the input and output directories
input_dir = 'images'
output_dir = 'output_images'

# Create the output directory if it doesn't exist
os.makedirs(output_dir, exist_ok=True)

# Specify the target size for resizing
target_size = (960, 640)

# Traverse the input directory and resize images
for root, dirs, files in os.walk(input_dir):
    for file in files:
        input_path = os.path.join(root, file)
        output_path = os.path.join(output_dir, file)

        # Open the image
        with Image.open(input_path) as img:
            # Resize the image while preserving the aspect ratio
            img.thumbnail(target_size)

            # Save the resized image
            img.save(output_path)
            print(f"Resized {file} and saved to {output_path}")

print("Image resizing completed.")
