define([], function () {
    return {
       version: 'module-test 0.0.3',

       render: function (id_or_domel, data) {
           
          var domElement = id_or_domel;
          if (typeof id_or_domel === 'string' || id_or_domel instanceof String) {
             selector = '#'+ id_or_domel;
             console.log ('module-json is rendering to selector: ' + selector);
             domElement = document.getElementById (selector); 
          } else {
             console.log ('module-json is rendering to dom-element');
          }

          var dataJson = JSON.stringify(data)
          console.log ('module-json data: ' + dataJson);

          var p = document.createElement ('p');
          var json = JSON.stringify (data);
          var textnode = document.createTextNode (dataJson);  
          //var textnode = document.createTextNode ('module-test rocks');  
          p.appendChild (textnode);
          domElement.appendChild (p);
       }
    }
});