import os
import re
import requests

for i in range(34, 38):
    with open(f'www.home-designing.com_{i}.har', 'r') as file:
        text = file.read()

    pattern = r"https://www.home-designing.com/wp-content/uploads/[^ ]+?\.jpg"

    matches = re.findall(pattern, text)

    unique_matches = list(set(matches))

    output_dir = f'image_{i}'
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    for index, match in enumerate(unique_matches, start=1):
        print(match)
        response = requests.get(match)
        if response.status_code == 200:
            image_path = os.path.join(output_dir, match.split("/")[-1])
            with open(image_path, 'wb') as image_file:
                image_file.write(response.content)
            print(f"Downloaded image {index} to {image_path}")
        else:
            print(f"Failed to download image {index}")

    print("Image download completed.")
