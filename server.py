import os

from telegram.ext import Updater, MessageHandler, Filters
from telegram.ext import CommandHandler
from bot import get_info

telegram_bot_token = '1016015048:AAECjJ8bVoWFiTPtmSuxIS3mStC3EtPzBbY'

updater = Updater(token=telegram_bot_token, use_context=True)
dispatcher = updater.dispatcher

def start(update, context):
    chat_id = update.effective_chat.id
    context.bot.send_message(chat_id=chat_id, text="Provide any English word and I will give you information about it.")


def get_word_info(update, context):
    
    word_info = get_info(update.message.text)

    if word_info.__class__ is str:
        update.message.reply_text(word_info)
        return


    word = word_info['word']

    origin = word_info.get('origin', 'default_value')
    meanings = '\n'

    synonyms = ''
    definition = ''
    example = ''
    antonyms = ''

    meaning_counter = 1

    for word_meaning in word_info['meanings']:
        meanings += 'Meaning ' + str(meaning_counter) + ':\n'

        for word_definition in word_meaning['definitions']:

            definition = word_definition['definition']

            if 'example' in word_definition:
                example = word_definition['example']

            for word_synonym in word_definition['synonyms']:
                synonyms += word_synonym + ', '

            for word_antonym in word_definition['antonyms']:
                antonyms += word_antonym + ', '

        meanings += 'Definition: ' + definition + '\n\n'
        meanings += 'Example: ' + example + '\n\n'
        meanings += 'Synonym: ' + synonyms + '\n\n'
        meanings += 'Antonym: ' + antonyms + '\n\n\n'

        meaning_counter += 1

    message = f"Word: {word}\n\nOrigin: {origin}\n{meanings}"

    update.message.reply_text(message)

def help_command(update, context):
    chat_id = update.effective_chat.id
    context.bot.send_message(chat_id=chat_id, text="This is a help message.")

def info_command(update, context):
    chat_id = update.effective_chat.id
    context.bot.send_message(chat_id=chat_id, text="This is an info message.")

def stop_command(update, context):
    chat_id = update.effective_chat.id
    context.bot.send_message(chat_id=chat_id, text="This is a stop message.")
    updater.stop()

def restart(update, context):
    context.bot.send_message(chat_id=update.effective_chat.id, text="Bot is restarting...")
    updater.stop()
    os.execl(sys.executable, sys.executable, *sys.argv)

def commands(update, context):
    chat_id = update.effective_chat.id
    message = "/start - Start the bot\n/help - Show available commands"
    context.bot.send_message(chat_id=chat_id, text=message)

dispatcher.add_handler(CommandHandler("commands", commands))    
dispatcher.add_handler(CommandHandler("restart", restart))
dispatcher.add_handler(CommandHandler("help", help_command))
dispatcher.add_handler(CommandHandler("info", info_command))
dispatcher.add_handler(CommandHandler("stop", stop_command))
dispatcher.add_handler(CommandHandler("start", start))

dispatcher.add_handler(MessageHandler(Filters.text, get_word_info))
updater.start_webhook(listen="0.0.0.0",
                      port=int(os.environ.get('PORT', 5000)),
                      url_path=telegram_bot_token,
                      webhook_url='https://bot-ews2.onrender.com/' + telegram_bot_token
                      )
