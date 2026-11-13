#Sistema Policlínico

Policlínico pequeño: historias clínicas, citas y tratamientos. Tiene una jerarquía natural de herencia (Paciente adulto/niño, distintos tipos de tratamiento). 

## Descripción

Se trata de un policlínico pequeño en Lima, con 2 a 3 consultorios, que actualmente lleva el registro de pacientes y citas en cuadernos y hojas sueltas. Esto provoca citas duplicadas, historias clínicas perdidas y datos personales de los pacientes expuestos.

Este proyecto propone un software que digitaliza esa gestión, aplicando múltiples paradigmas de programación, principios de código limpio y la protección de datos personales de los pacientes.

## Ruta de trabajo

1. Elegir caso, requerimientos, restricciones y criterios de éxito.
2. Tabla comparativa de paradigmas con criterios técnicos.
3. Diagrama de clases UML con tipos de relaciones y patrones.
4. Código del módulo, cifrado o enmascarado de datos y excepciones.
5. Pruebas JUnit, SOLID y buenas prácticas.
6. Conclusiones, limitaciones y reflexión ética.
7. Diapositivas. 

## Requerimientos del software

- RF-01 Registrar pacientes (datos personales, tipo adulto/niño).
- RF-02 Programar, reprogramar y cancelar citas, evitando choques de horario.
- RF-03 Registrar la atención médica en la historia clínica.
- RF-04 Gestionar tratamientos de distintos tipos.
- RF-05 Generar reportes de atención (por día, por médico).
- RF-06 Buscar y filtrar pacientes y citas.
- RF-07 Proteger datos sensibles (DNI, teléfono) sin mostrarlos en texto plano.

## Equipo
- Franco Rol:?
- Christian Rol:?
- Bianca Rol:?
- Jose Rol:?

## Restricciones de diseño

- Uso de herramientas de código abierto: Java OpenJDK, Apache NetBeans y Git/GitHub.
- Cumplimiento de la Ley N.° 29733 (Ley de Protección de Datos Personales): DNI enmascarado o cifrado
- Integración de al menos 3 paradigmas: orientado a objetos, funcional y orientado a eventos.
- Código limpio, con comentarios estructurados en español y commits descriptivos en español.

## Criterios de éxito

- Una cita duplicada (mismo médico y mismo horario) es rechazada el 100% de las veces.
- El DNI nunca aparece completo en pantalla ni en archivos.
- Al menos 3 pruebas automatizadas con JUnit se ejecutan y pasan correctamente.

## Tecnologías

- Java OpenJDK
- Apache NetBeans
- Swing (Interfaz)
- JUnit
- GitHub 
