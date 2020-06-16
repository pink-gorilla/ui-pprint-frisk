window.MathJax = {
    tex: {
      inlineMath: [["@@", "@@"],
                   ['$', '$'], 
                   ['\\(', '\\)']]
    },
    svg: {
      fontCache: 'global'
    }
  };
  
  (function () {
      var url = '/tex-svg-full.js';
      //var url = 'https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-svg.js';
      console.log ("loading mathjax from: " + url)
    var script = document.createElement('script');
    script.src = url
    script.async = true;
    document.head.appendChild(script);
  })();
  