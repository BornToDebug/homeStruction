from django.http import HttpResponse, Http404
from django.contrib.auth.decorators import login_required


@login_required
def imageView(request):
    try:
        with open("/home/david/Pictures/bentlakas.jpg") as f:
            return HttpResponse(f.read(), content_type="image/jpeg")
    except IOError:
        raise Http404
