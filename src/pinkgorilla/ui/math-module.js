define([], function () {
    return {
       version: 'math 0.0.3 (mathjax v3)',

       render: function (id_or_domel, data) {

          var node = id_or_domel;
          if (typeof id_or_domel === 'string' || id_or_domel instanceof String) {
             var id  = '#'+ id_or_domel;
             node = document.querySelector (id);
             console.log ('math-module is rendering to selector id: ' + id);
          } else {
             console.log ('math-module is rendering to dom-element');
          }

          //var dataJson = JSON.stringify(data)
          //console.log ('math-module data: ' + dataJson);

          require(['mathjax'], function(MathJax) {
            //console.log('mathjax: ' + MathJax)
            //pinkgorilla.ui.math.queue_mathjax_rendering (mathjax, selector_or_domel);
            var options = MathJax.getMetricsFor (node, true);
            MathJax.tex2chtmlPromise(data, options)
              .then((html) => {
                 node.appendChild(html);
                 var sheet = document.querySelector('#MJX-CHTML-styles');
                 if (sheet) sheet.parentNode.removeChild(sheet);
                 document.head.appendChild(MathJax.chtmlStylesheet());
              });
            }, function(err) {
              console.log('mathjax library Failed to load. error: ' + err);
          });
       }
    }
});