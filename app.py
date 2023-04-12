import os
from flask import Flask, request
import telegram

app = Flask(__name__)
bot = telegram.Bot(token=os.environ['1016015048:AAECjJ8bVoWFiTPtmSuxIS3mStC3EtPzBbY'])

@app.route('/webhook', methods=['POST'])
def webhook():
    update = telegram.Update.de_json(request.get_json(force=True), bot)
    chat_id = update.message.chat_id
    text = update.message.text
    bot.send_message(chat_id=chat_id, text=text)
    return 'OK'

if __name__ == '__main__':
    app.run(debug=True)