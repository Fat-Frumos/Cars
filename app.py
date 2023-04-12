import os
from flask import Flask, request
from telegram import Bot, Update
from telegram.ext import Dispatcher, MessageHandler, Filters

TOKEN = '1016015048:AAECjJ8bVoWFiTPtmSuxIS3mStC3EtPzBbY'
server = Flask(__name__)
bot = Bot(token=os.environ['TOKEN'])
dispatcher = Dispatcher(bot, None, use_context=True)

@server.route('/', methods=['POST'])
def webhook():
    update = Update.de_json(request.get_json(force=True), bot)
    dispatcher.process_update(update)
    return 'OK'

@dispatcher.message_handler(Filters.text)
def echo(update, context):
    update.message.reply_text(update.message.text)

if __name__ == '__main__':
    server.run()



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
    