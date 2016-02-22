var compiledTemplate = Handlebars.compile(typeof(overrideTemplate) !== 'undefined' ? overrideTemplate : defaultTemplate);
compiledTemplate({'name': 'abracadabra'});
