from django.http import HttpResponse
from django.contrib.auth.decorators import login_required
import controlbasic3

@login_required
def controlbasic(request):
    controlbasic3.controlconfirm('1lampon')
    return HttpResponse('1')



