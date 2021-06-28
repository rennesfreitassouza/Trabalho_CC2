from django.http import HttpResponse
from rest_framework import viewsets
from rest_framework.viewsets import ModelViewSet
from rest_framework.response import Response
from rest_framework import authentication, permissions
from .models import *
from .serializers import *
import json

class CursoViewSet(viewsets.ModelViewSet):
	queryset = Curso.objects.all()
	serializer_class = CursoSerializer
	permission_classes = [permissions.IsAuthenticated]

class AlunoViewSet(viewsets.ModelViewSet):
	queryset = Aluno.objects.all()
	serializer_class = AlunoSerializer
	permission_classes = [permissions.IsAuthenticated]

class DisciplinaViewSet(viewsets.ModelViewSet):
	queryset = Disciplina.objects.all()
	serializer_class = DisciplinaSerializer
	permission_classes = [permissions.IsAuthenticated]

