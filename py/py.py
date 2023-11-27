import re

text = ''https://images.trvl-media.com/lodging''

url_pattern = r'https?://[^\s/$.?#].[^\s]*'

urls = re.findall(url_pattern, text)

for url in urls:
    print(url)