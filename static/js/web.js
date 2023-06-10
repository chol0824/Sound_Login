
    const btn = document.getElementById('btn');
    const result = document.getElementById('result');

    const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
    const recognition =  new SpeechRecognition();

    recognition.onstart =  function () {
           console.log('You can speak now');
      }

    recognition.onresult = function(event){
        var text = event.results[0][0].transcript;
        console.log(text);
        
        document.getElementById('result').innerHTML = text;
        
        
    } 
           
    


      