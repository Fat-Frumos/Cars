import os

directory = 'resize'

files = os.listdir(directory)

for filename in files:
    if ' ' in filename:
        new_filename = filename.replace(' ', '')
        os.rename(os.path.join(directory, filename), os.path.join(directory, new_filename))
        print(f'Renamed: {filename} -> {new_filename}')
