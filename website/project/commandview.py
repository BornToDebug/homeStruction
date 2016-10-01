from django.http import HttpResponse
from django.contrib.auth.decorators import login_required
import controlbasic3
from subprocess import call

@login_required
def controlbasic(request):
    controlbasic3.controlconfirm('1lampon')
    return HttpResponse('1')

@login_required
def startstream(request):
    call(['/home/projekt/homeStruction/stream/stream7 start'], shell=True)
    return HttpResponse('started')

@login_required
def togglemp3(request):
    call(['/home/projekt/homeStruction/newmp3/checkrunning'], shell=True)
    return HttpResponse('mp3 toggled')


