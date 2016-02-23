var compiledTemplate = Handlebars.compile(typeof(overrideTemplate) !== 'undefined' ? overrideTemplate : defaultTemplate);
var contextData = JSON.parse(context.data);
compiledTemplate(contextData);