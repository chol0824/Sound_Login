src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js",'//cdnjs.cloudflare.com/ajax/libs/socket.io/2.2.0/socket.io.js'

$(document).ready(function(){
    var sock = io.connect('http://127.0.0.1:5000');
    sock.on('connect', function(){
    var connect_string = 'new_connect';
    sock.send(connect_string);
    });
    
    sock.on('hello', function(msg){
    $('#messages').append('<li>' +'>>Hello :'+ msg + '</li>');
    console.log('Received Hello Message');
    });
    
    sock.on('message', function(msg){
    // console.log(type(msg));
    if(msg.type === 'normal'){
    $('#messages').append('>> '+msg.message+'<br>');
    }else{
    $('#messages').append('<li>' + msg.message + '</li>');
    }
    console.log('Received Message : '+msg.type);
    });
    
    
    
    $('#sendbutton').on('click', function(){
    sock.send($('#myMessage').val());
    $('#myMessage').val('');
    });
    });
    
  