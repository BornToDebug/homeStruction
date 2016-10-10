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
    if image == 'temp':
        imageurl = '/home/david/git/homeStruction/website/Temperature.png'
    if image == 'humid':
        imageurl = '/home/david/git/homeStruction/website/Humidity.png'
    if image == 'light':
        imageurl = '/home/david/git/homeStruction/website/Light.png'
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

