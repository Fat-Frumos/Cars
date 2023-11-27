import requests
from bs4 import BeautifulSoup

with open('h2_elements.txt', 'w') as f:
    for i in range(1, 200):
        url = 'https://www.etsy.com/no-en/market/coupon_gift?ref=pagination&page=' + str(i)
        response = requests.get(url)
        soup = BeautifulSoup(response.text, 'html.parser')
        h2_elements = soup.find_all('h2')
        for h2 in h2_elements:
            f.write(h2.text)
