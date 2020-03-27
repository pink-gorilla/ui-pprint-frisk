define([], function () {
    return {
       version: 'vega 0.0.4',

       render: function (id_or_domel, data) {

          var selector_or_domel = id_or_domel;
          if (typeof id_or_domel === 'string' || id_or_domel instanceof String) {
             selector_or_domel = '#'+ id_or_domel;
             console.log ('vega-module is rendering to selector id: ' + selector_or_domel);
          } else {
             console.log ('vega-module is rendering to dom-element');
          }

          //var dataJson = JSON.stringify(data)
          //console.log ('vega-module data: ' + dataJson);

          var vegaOptions = {
             actions: false, 
             defaultStyle: true
          };

          require(['vega', 'vega-lite', 'vega-embed'], function(vega, vegaLite, vegaEmbed) {
            vegaEmbed(selector_or_domel, data, vegaOptions).catch(function(em) {
                console.log('Error in Rendering Vega Spec: ' + em);
                var txt = document.createTextNode ('Vega Spec error' + em);
                selector_or_domel.appendChild (txt);
               });
            }, function(err) {
              console.log('Vega-Embed failed to load');
              var txt = document.createTextNode ('Vega-Embed failed to load');
              selector_or_domel.appendChild (txt);
          });
       }
    }
});