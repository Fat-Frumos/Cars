import os
import glob
import numpy as np
from matplotlib import pyplot as plt
from matplotlib import image as mpimg

input_path = 'imagedir'
output_path = 'resize'
new_size = (960, 640)

if not os.path.exists(output_path):
    os.makedirs(output_path)

count = 1
for infile in glob.glob(os.path.join(input_path, '*.jpg')):
    print(f"Processing image: {os.path.basename(infile)}")
    img = mpimg.imread(infile)
    height, width = img.shape[:2]

    scale_x = new_size[0] / width
    scale_y = new_size[1] / height

    new_img = np.zeros((new_size[1], new_size[0], 3), dtype=np.uint8)

    for i in range(new_size[1]):
        for j in range(new_size[0]):
            x = int(j / scale_x)
            y = int(i / scale_y)
            new_img[i, j] = img[y, x]

    outfile = os.path.join(output_path, f"image_{count}.jpg")
    plt.imsave(outfile, new_img)
    count += 1
