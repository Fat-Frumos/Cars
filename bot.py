import os
import requests
from flask import Flask

server = Flask(__name__)


@server.route('/<word>')
def get_info(word):

    url = 'https://api.dictionaryapi.dev/api/v2/entries/en/{}'.format(word)

    response = requests.get(url)

# return a custom response if an invalid word is provided
    if response.status_code == 404:
        error_response = 'We are not able to provide any information about your word. Please confirm that the word is ' \
                         'correctly spelt or try the search again at a later time.'
        return error_response

    data = response.json()[0]

    print(data)
    return data

get_info("food")

if __name__ == "__main__":
    port = int(os.environ.get('PORT', 5000))
    server.run(debug=True, host='0.0.0.0', port=port)

# import os
# import telebot
# from flask import Flask, request

# TOKEN = '1016015048:AAECjJ8bVoWFiTPtmSuxIS3mStC3EtPzBbY'
# APP_URL = f'https://bot-ews2.onrender.com/{TOKEN}'
# bot = telebot.TeleBot(TOKEN)
# server = Flask(__name__)


# @bot.message_handler(commands=['start'])
# def start(message):
#     bot.reply_to(message, 'Hello, ' + message.from_user.first_name)


# @bot.message_handler(func=lambda message: True, content_types=['text'])
# def echo(message):
#     bot.reply_to(message, message.text)


# @server.route('/' + TOKEN, methods=['POST'])
# def get_message():
#     json_string = request.get_data().decode('utf-8')
#     update = telebot.types.Update.de_json(json_string)
#     bot.process_new_updates([update])
#     return '!', 200


# @server.route('/')
# def webhook():
#     bot.remove_webhook()
#     bot.set_webhook(url=APP_URL)
#     return '!', 200


# if __name__ == '__main__':
#     server.run(host='0.0.0.0', port=int(os.environ.get('PORT', 5000)))
                          