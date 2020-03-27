define([], function () {
    return {
       version: 'highcharts 0.0.3',

       render: function (id_or_domel, data) {

          var selector_or_domel = id_or_domel;
          if (typeof id_or_domel === 'string' || id_or_domel instanceof String) {
             selector_or_domel = '#'+ id_or_domel;
             console.log ('highcharts-module is rendering to selector id: ' + selector_or_domel);
          } else {
             console.log ('highcharts-module is rendering to dom-element');
          }

          //var dataJson = JSON.stringify(data)
          //console.log ('highcharts-module data: ' + dataJson);

          require(['highcharts'], function(highcharts) {  
            var chart = new highcharts.Chart (selector_or_domel, data); //.catch(console.warn);
            }, function(err) {
              console.log('highcharts Failed to load!');
          });
       }
    }
});