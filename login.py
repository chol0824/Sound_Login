from io import StringIO
from flask import Flask, render_template
from flask_socketio import SocketIO,emit
from filecmp import cmp
from flask import redirect
import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from email.mime.base import MIMEBase
from email import encoders
from email.header import Header

app = Flask(__name__)
socketio = SocketIO(app)

s = smtplib.SMTP('smtp.gmail.com', 587)
s.starttls()
s.login('hs.soundlogin@gmail.com','gljmfiwmwfzwxaxp')

html = """\
<DOCTYPE HTML5>
    <html>
      <head>
        <link rel="stylesheet" href="{{ url_for('static', filename='css/style.css') }}">
      </head>
      <body>
        <p><h1 style="background-color: #007ffd; color: cornsilk;">SOUNDLOGIN SUCCESS</h1><br>
           <h3>사용자 님의 계정이 소리 로그인을 통해 로그인되었습니다.</h3><br><br><hr>
           
           <h4>사용자 님이 로그인하신 게 아니라면 버튼을 클릭해주세요.</h4><br>
           <a href='http://172.20.10.5:5000/logout' target="_blank"><button type="submit" class="submit" style="background-color: rgb(14, 47, 110); color:cornsilk">제가 로그인한 것이 아닙니다.</button></a>
            

           
        

           
        </p>
     
      </body>
    </html>
"""

msg = MIMEText(html, 'html')
msg['Subject'] = '[SoundLogin] 새로운 로그인 이력이 발견되었습니다.'

msg.set_charset('utf8')

@app.route('/')
def sessions():
    
    return render_template('SoundLogin.html')

def messageReceived(methods=['GET', 'POST']):
    print('message was received!!!')
    result = cmp('webSound.txt','appSound.txt')

    
@app.route('/registration')
def regist():
    return render_template('registration.html')

@app.route('/logout')
def logout():

    return render_template('logout.html')


@socketio.on('message')
def handle_my_custom_event(json, methods=['GET', 'POST']):
    
    f = open('registration.txt', 'a', encoding='utf-8') # 파일 열기
   
    print(json, file=f) # 파일 저장하기

    f.close()

    socketio.emit('my response', str(f), callback=messageReceived)
    socketio.emit('Response',str(f))
   

@socketio.on('my event')
def send(json, methods=['GET', 'POST']):
    
    f = open('webSound.txt', 'w', encoding='utf-8') # 파일 열기
    
   
    print(json, file=f) # 파일 저장하기

    f.close()  

  

@socketio.on('send_sound')
def sendUSER(json, methods=['GET', 'POST']):
   
    f = open('appSound.txt', 'w', encoding='utf-8')
    print(json, file=f)

    f.close()


@socketio.on('send_user')
def sendUSER(json, methods=['GET', 'POST']):
    f = open('appLogin.txt', 'w', encoding='utf-8')

    print(json, file=f)

    f.close()

    with open("registration.txt") as f:
        with open("appLogin.txt") as d:
                srcData = f.readlines()
                cmpData = d.readlines()
    
                for cmpline in cmpData:
                        
                    result = cmpline in srcData
                    if (result == True):        
                     str = 'success'
                     print('로그인 성공 ' + str)
                     socketio.emit('result', {'str': str})
                     break
                    else:
                     str = 'fail'
                     print('로그인 실패 ' + str)
                     socketio.emit('result', {'str': str})
                     break      


@app.route('/main')
def main():
    result = cmp('webSound.txt','appSound.txt')

    print(result)
    if(result == True):       
         str1 = 'Success'
         print('로그인 성공 '+str1)
         socketio.emit('s_result', {'str':str1})
         #msg = Message('[SoundLogin]', sender='hs.soundlogin@gmail.com', recipients=['chol0824@naver.com'])
         #msg.body = '로그인되었습니다 Hello 123'
         #mail.send(msg)
         with open('registration.txt') as f:
            with open("appLogin.txt") as d:
                cmpData = d.readlines()

                cmpData = list(map(lambda s: s.strip(), cmpData))
                wordList = cmpData # input().split()
                words = set(wordList)

        
            for line_number, line in enumerate(f, 1):
                word = line.strip()
                if word in words:
                    # print(word, line_number)
                    with open("userMailInfo.txt") as g:
                        data = g.readlines()[line_number-1]
                        jsondata = data
                      
                        print(jsondata)
                        s.sendmail("hs.soundlogin@gmail.com", "1871477@hansung.ac.kr", msg.as_string())
                        s.quit()

         return render_template('main.html')
         
    else:
        str1 = 'Fail'
        print('로그인 실패 '+str1)
        socketio.emit('s_result', {'str':str1})
        return redirect('/')



    

@socketio.on('logout')
def logout(str, methods=['GET', 'POST']):
   
    f = open('logOut.txt', 'a', encoding='utf-8')
    print(str, file=f)

    f.close()
    print("소켓 성공")


    return redirect('/logout')

@socketio.on('send_mail')
def sendUSER(str, methods=['GET', 'POST']):    
   
    f = open('userMailInfo.txt', 'a', encoding='utf-8')
    print(str, file=f)

    f.close()

   
    

if __name__ == '__main__':
    socketio.run(app, host="0.0.0.0", debug=True)


  
