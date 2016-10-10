from datetime import datetime
from django.http import HttpResponse
from django.contrib.auth.decorators import login_required
import controlbasic3
from subprocess import call
from rest_framework.authtoken.models import Token

from rest_framework import HTTP_HEADER_ENCODING, exceptions
from project.models import Alarm

def androidlogin(request):
    try:
        auth = request.META.get('HTTP_AUTHORIZATION', b'').split()
        if not auth or auth[0].lower() != 'token':
            return 'not a token header'
        if len(auth) < 2:
            return 'no token recieved'
        if len(auth) > 2:
            return 'too many arguments, check if token has spaces'
        matchingToken = Token.objects.get(pk=auth[1])
        return 'auth'
    except:
        return 'not authorized'

def androidcontrol(request):
    authcheck = androidlogin(request)
    if authcheck == 'auth':
        command = request.GET.get('command', '')
        controlbasic3.controlconfirm(command)
        return HttpResponse(command)
    else:
        return HttpResponse(authcheck)

def getimage(request):
    image = request.GET.get('image', '')
    imageurl = None
    dirurl = '/home/projekt/homeStruction/website/'
    if image == 'temp':
        imageurl = dirurl + 'Temperature.png'
    if image == 'humid':
        imageurl = dirurl + 'Humidity.png'
    if image == 'light':
        imageurl = dirurl + 'Light.png'
    if imageurl is not None:
        try:
            with open(imageurl) as f:
                return HttpResponse(f.read(), content_type="image/png")
        except IOError:
            return HttpResponse('image cannot be accessed')
    return HttpResponse(image + ' no such image')

@login_required
def imageView(request):
    return getimage(request)

def androidImageView(request):
    authcheck = androidlogin(request)
    if authcheck is 'auth':
        return getimage(request)
    else:
        return HttpResponse(authcheck)

def setAlarm(request):
    hour = request.GET.get('hour', '00')
    minute = request.GET.get('minute', '00')
    monday = request.GET.get('monday', 'False')
    tuesday = request.GET.get('tuesday', 'False')
    wednesday = request.GET.get('wednesday', 'False')
    thursday = request.GET.get('thursday', 'False')
    friday = request.GET.get('friday', 'False')
    saturday = request.GET.get('saturday', 'False')
    sunday = request.GET.get('sunday', 'False')
    coffee = request.GET.get('coffee', 'False')

    try:
        Alarm.objects.create(
                monday=(monday == 'True'),
                tuesday=(tuesday == 'True'),
                wednesday=(wednesday == 'True'),
                thursday=(thursday == 'True'),
                friday=(friday == 'True'),
                saturday=(saturday == 'True'),
                sunday=(sunday == 'True'),
                alarm_time=datetime.strptime(hour + ':' + minute, '%H:%M').time())
        return HttpResponse('success')
    except ValueError:
        return HttpResponse('values are not correct')

@login_required
def setAlarmView(request):
    return setAlarm(request)

def androidSetAlarmView(request):
    authcheck = androidlogin(request)
    if authcheck is 'auth':
        return setAlarm(request)
    else:
        return HttpResponse(authcheck)

@login_required
def controlbasic(request):
    command = request.GET.get('command', '')
    controlbasic3.controlconfirm(command)
    return HttpResponse(command)

@login_required
def startstream(request):
    call(['/home/projekt/homeStruction/stream/stream7 start'], shell=True)
    return HttpResponse('started')

@login_required
def stopstream(request):
    call(['/home/projekt/homeStruction/stream/stream7 stop'], shell=True)
    return HttpResponse('stopped')

@login_required
def togglemp3(request):
    call(['/home/projekt/homeStruction/newmp3/checkrunning'], shell=True)
    return HttpResponse('mp3 toggledddd')

