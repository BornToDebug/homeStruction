from django.http import HttpResponse
from django.contrib.auth.decorators import login_required
import controlbasic3
from subprocess import call
from rest_framework.authtoken.models import Token

from rest_framework import HTTP_HEADER_ENCODING, exceptions

def androidlogin(request):
    try:
        auth = request.META.get('HTTP_AUTHORIZATION', b'').split()
        if not auth or auth[0].lower() != 'token':
            return HttpResponse('not a token header')
        if len(auth) < 2:
            return HttpResponse('no token recieved')
        if len(auth) > 2:
            return HttpResponse('too many arguments, check if token has spaces')
        matchingToken = Token.objects.get(pk=auth[1])
        return True
    except:
        return False

def androidcontrol(request):
    if androidlogin(request):
        command = request.GET.get('command', '')
        controlbasic3.controlconfirm(command)
        return HttpResponse(command)
    else:
        return HttpResponse('not authorized')



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

