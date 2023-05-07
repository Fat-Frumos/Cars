import requests
import textwrap
from tqdm import tqdm
from bs4 import BeautifulSoup
from gtts import gTTS
from flask import Flask, render_template, request

app = Flask(__name__)

@app.route('/')
def index():
    query = request.args.get('query')
    return render_template('index.html', query=query)

@app.route('/parse', methods=['POST'])
def parse():
        url = request.form['text']
    with open(url, 'r') as f:
        html_content = f.read()

    soup = BeautifulSoup(html_content, 'html.parser')
    text = soup.get_text()

    language = 'en'
    query = request.args.get('query') or "default"
    filename = query + '.mp3'

    with open(filename, "wb") as fp:
        chunks = textwrap.wrap(text, 100)
        with tqdm(total=len(chunks)) as pbar:
            for chunk in chunks:
                tts_chunk = gTTS(chunk, lang=language, tld='com', slow=False)
                tts_chunk.write_to_fp(fp)
                pbar.update(1)
    return render_template('result.html', url=url, query=query)

@app.route('/process', methods=['POST'])
def process():
    url = request.form['text']
    response = requests.get(url)
    html_content = response.text

    soup = BeautifulSoup(html_content, 'html.parser')
    headings = [h.get_text() for h in soup.find_all(['h1', 'h2', 'h3', 'h4', 'h5', 'h6'])]
    paragraphs = [p.get_text() for p in soup.find_all('p')]
    text = ''.join(headings)
    print(text)
    print(paragraphs)
    
    language = 'en'
    query = request.args.get('query') or "default"
    filename =  query + ".mp3"

    with open(filename, "wb") as fp:
        chunks = textwrap.wrap(text, 100)
        with tqdm(total=len(chunks)) as pbar:
            for chunk in chunks:
                tts_chunk = gTTS(chunk, lang=language, tld='com', slow=False)
                tts_chunk.write_to_fp(fp)
                pbar.update(1)

    return render_template('result.html', url=url, query=query)

if __name__ == '__main__':
    app.run(debug=True)
    
  # with open(url, encoding='latin-1') as f:
    #     html_content = f.read()

# voice_language_code = 'en-US'
# gender = 'male'    
# html_file = 'Java.html'
# output_file = 'Java.mp3'

# def text_to_speech(text, language='en', gender='female'):
#     engine = pyttsx3.init()
#     engine.setProperty('rate', 150)
#     engine.setProperty('voice', f'{language}-{gender}')
#     engine.say(text)
#     engine.runAndWait()

# text_to_speech(text, language='en', gender='female')
