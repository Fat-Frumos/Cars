from PIL import Image
import os

folder_path = '800'
tall_folder = 'tall'
wide_folder = 'wide'
same_folder = 'same'

# Create destination folders if they don't exist
if not os.path.exists(tall_folder):
    os.makedirs(tall_folder)
if not os.path.exists(wide_folder):
    os.makedirs(wide_folder)
if not os.path.exists(same_folder):
    os.makedirs(same_folder)

# Get list of image files in folder
image_files = [f for f in os.listdir(folder_path) if f.endswith(('.png', '.jpg', '.jpeg'))]

# Sort images by size and save to destination folders
for image in image_files:
    img = Image.open(os.path.join(folder_path, image))
    width, height = img.size
    if height == width:
        img.save(os.path.join(same_folder, image))
    elif height > width:
        img.save(os.path.join(tall_folder, image))
    else:
        img.save(os.path.join(wide_folder, image))
