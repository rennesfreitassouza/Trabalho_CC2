from rest_framework import serializers
from .models import *

class CursoSerializer(serializers.ModelSerializer):
	class Meta:
		model = Curso
		fields = '__all__'

class AlunoSerializer(serializers.ModelSerializer):
	class Meta:
		model = Aluno
		fields = '__all__'

class DisciplinaSerializer(serializers.ModelSerializer):
	class Meta:
		model = Disciplina
		fields = '__all__'

