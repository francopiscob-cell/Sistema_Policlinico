# Sistema Policlínico

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

* RF-01 Registrar pacientes (datos personales, tipo adulto/niño).
* RF-02 Programar, reprogramar y cancelar citas, evitando choques de horario.
* RF-03 Registrar la atención médica en la historia clínica.
* RF-04 Gestionar tratamientos de distintos tipos.
* RF-05 Generar reportes de atención (por día, por médico).
* RF-06 Buscar y filtrar pacientes y citas.
* RF-07 Proteger datos sensibles (DNI, teléfono) sin mostrarlos en texto plano.

## Equipo

* Franco Rol: Programador de lógica (Prueba de viabilidad en Java, repositorio y commits, código del módulo).
* Christian Rol: Diseñador de clases (Etapa 3-6: Diagrama UML, conclusiones, apoyo con la lista de riesgos)
* Bianca Rol: Integrador / documento (Consolida el informe en Word, historial de versiones, entrega en el aula virtual)
* Jose Rol: Presentación (Diapositivas, y apoyo en pruebas y revisión cruzada)

## Restricciones de diseño

* Uso de herramientas de código abierto: Java OpenJDK, Apache NetBeans y Git/GitHub.
* Cumplimiento de la Ley N.° 29733 (Ley de Protección de Datos Personales): DNI enmascarado o cifrado
* Integración de al menos 3 paradigmas: orientado a objetos, funcional y orientado a eventos.
* Código limpio, con comentarios estructurados en español y commits descriptivos en español.

## Criterios de éxito

* Una cita duplicada (mismo médico y mismo horario) es rechazada el 100% de las veces.
* El DNI nunca aparece completo en pantalla ni en archivos.
* Al menos 3 pruebas automatizadas con JUnit se ejecutan y pasan correctamente.

## Tecnologías

* Java OpenJDK
* Apache NetBeans
* Swing (Interfaz)
* JUnit
* GitHub

* \## DD-01
* 
* Se usarán los paradigmas Orientado a Objetos (POO), Funcional y Orientado a Eventos para desarrollar el módulo del policlínico.
* 
* \### Justificación
* \- \*\*POO:\*\* Se usa en RF-01 (principal), RF-03, RF-04 y RF-07. En la Tabla 2 obtuvo Alto en Encapsulamiento, Mantenibilidad y Reutilización porque protege los datos con atributos privados, permite agregar tratamientos sin cambiar el código base y reutiliza atributos con herencia. El RF-03 se incluye aquí porque registrar una atención médica requiere conectar las clases principales del sistema (como el paciente y su historia clínica).
* \- \*\*Funcional:\*\* Se usa en RF-05, RF-06 y como apoyo en RF-01 y RF-07. En la Tabla 2 obtuvo Alto en Colecciones y Reutilización porque permite filtrar y transformar datos sin modificar las listas originales. En el código se aplicará con `stream().filter()`, `map()` y lambdas para los reportes, búsquedas y para enmascarar el DNI.
* \- \*\*Orientado a Eventos:\*\* Se usa en RF-02. En la Tabla 2 obtuvo Alto en Interacción porque responde a lo que hace el usuario sin seguir un orden fijo. En Java Swing se aplicará con `JButton` y `ActionListener` para validar horarios y gestionar citas con un clic.
* 
* \### Integración y descarte
* Los tres paradigmas trabajan juntos en Java: la pantalla en Swing está hecha con objetos (POO), los botones escuchan los clics (listeners) del usuario (Eventos) y, cuando se necesita buscar o filtrar información, se pasa la lista al enfoque Funcional para obtener el resultado sin alterar los datos originales. En cambio, el paradigma Estructurado se descarta porque no obtuvo ningún Alto en la Tabla 2; como se vio en las Tablas 1 y 2, no tiene encapsulamiento y modificar una función puede generar errores en otras partes del código.
* 
* \### Trazabilidad
* Esta decisión cumple con la restricción de diseño 3 (integrar al menos tres paradigmas) y se comprobará en la prueba de viabilidad y en el módulo funcional hecho en Java OpenJDK con Apache NetBeans, subiendo los avances con commits en GitHub.

