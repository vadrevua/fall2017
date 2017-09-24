var Alexa = require('alexa-sdk');

var http = require('http');
var request = require('request');
var XMLHttpRequest = require('xmlhttprequest').XMLHttpRequest;

var handlers = {
	'LaunchRequest': function () {
        this.emit(':ask', 'How can cap one help you today');
    },
    'GetAccountBalance': function (intent) {
		var self = this;

        var itemSlot = this.event.request.intent.slots.account;
        var url = 'http://api.reimaginebanking.com/accounts/59bd7426a73e4942cdafdd73?key=8f7e16545889aa026c63a00515216542';
        request(url, function (error, response, body) {
			if (!error && response.statusCode == 200) {
				var output = JSON.parse(body);
				self.emit(':tell', 'The balance in ' + output.nickname + ' is ' + output.balance);
			} else {
				self.emit(':tell', 'There was a problem retrieving your account information')
			}
		});
    },
	'FindATM': function (intent) {
		var self = this;

		var rad = this.event.request.intent.slots.radius.value;
		if (rad == null || rad <= 0) { rad = 1; }
        var url = 'http://api.reimaginebanking.com/atms?lat=37.545012&lng=-77.448169&rad=' + rad + '&key=8f7e16545889aa026c63a00515216542';
        request(url, function (error, response, body) {
			if (!error && response.statusCode == 200) {
				var output = JSON.parse(body);
				var atmArray = [];
				if (output.data.length == 0) { self.emit(':tell', 'There are no ATM\'s located in the given area') }
				else {
					var address = '';
					for ( var i = 0; i < output.data.length; i++ ) {
						var temp = output.data[i].address.street_number + ' ' + output.data[i].address.street_name;
						if (address == temp) continue;
						atmArray.push(address);
						address = temp;
					}
				}
				self.emit(':tell', 'The ATM\'s in your area are located at ' + atmArray);
			}
        });
    },
	'TransferFunds': function (intent) {
		var self = this;

		var first_name = this.event.request.intent.slots.first_name.value;
		var last_name = this.event.request.intent.slots.last_name.value;
		//var amount = this.event.request.intent.slots.amount.value;

		//if (amount == null || amount <= 0) {
		//	self.emit(':tell', 'You have to transfer something, silly')
		//}

		var xmlhttp = new XMLHttpRequest();
		var url = 'http://api.reimaginebanking.com/customers?key=8f7e16545889aa026c63a00515216542';
		xmlhttp.onreadystatechange = function() {
			console.log('\n' + first_name + '\n');
			if (this.readyState == 4 && this.status == 200) {
				var customers = JSON.parse(this.responseText);
				var found = false;
				for ( var i = 0; i < customers.length; i++ ) {
					if(customers[i].first_name.toUpperCase() == first_name.toUpperCase() && customers[i].last_name.toUpperCase() == last_name.toUpperCase()) {
						found = true;
						break;
					}
				}
				if (!found) self.emit(':tell', 'There are no customers named ' + first_name + ' ' + last_name);
				else self.emit(':tell', first_name + ' ' + last_name + ' exists my dude');
			} else { self.emit(':tell', 'There was a problem completing your transfer'); }
      //customers.sort(compare);
      //document.getElementById("demo").innerHTML = customers[0].first_name + ", " + customers[1].first_name + ", "+ customers[2].first_name; //prints name onto screen
		};
		xmlhttp.open("GET", url , true);
		xmlhttp.send(null);

        var geturl = 'http://api.reimaginebanking.com/customers?key=8f7e16545889aa026c63a00515216542';
        request(geturl, function (error, response, body) {
			if (!error && response.statusCode == 200) {
				var arr = JSON.parse(body);
				var found = false;
				console.log('\n' + arr[0] + '\n');
				for ( var i = 0; i < arr.length; i++ ) {
					if(arr[i].first_name == first_name && arr[i].last_name == last_name) {
						atmArray.push(output[i]._id);
						found = true;
						break;
					}
				}
				if (!found) self.emit(':tell', 'There are no customers named ' + first_name + ' ' + last_name);
				else self.emit(':tell', first_name + ' ' + last_name + 'exists my dude');
			}
        });

	},
    'AMAZON.HelpIntent': function () {
        const speechOutput = this.t('HELP_MESSAGE');
        const reprompt = this.t('HELP_MESSAGE');
        this.emit(':ask', speechOutput, reprompt);
    },
    'AMAZON.CancelIntent': function () {
        this.emit(':tell', this.t('STOP_MESSAGE'));
    },
    'AMAZON.StopIntent': function () {
        this.emit(':tell', this.t('STOP_MESSAGE'));
    },
};

exports.handler = function (event, context) {
    const alexa = Alexa.handler(event, context);
    alexa.APP_ID = "CapOne";
    // To enable string internationalization (i18n) features, set a resources object.
    alexa.registerHandlers(handlers);
    alexa.execute();
};
