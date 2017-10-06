var SlackBot = require('slackbots');

var bot = new SlackBot({
  token: 'xoxb-250437965174-aEWO3fVjdRVy352SWdTfkf7S',
  name: 'aditya.bot'
});
bot.on('start', function() {
    // more information about additional params https://api.slack.com/methods/chat.postMessage

    // define channel, where bot exist. You can adjust it there https://my.slack.com/services
    bot.postMessageToChannel('general', 'Posted by Aditya using a bot!');
});
