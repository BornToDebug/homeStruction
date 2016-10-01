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

