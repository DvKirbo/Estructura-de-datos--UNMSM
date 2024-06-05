import 'package:collection/collection.dart';

class HuffmanNodo {
  final int frecuencia;
  final String? caracter;
  HuffmanNodo? izquierda;
  HuffmanNodo? derecha;

  HuffmanNodo(this.caracter, this.frecuencia);
}

int comparadorNodos(HuffmanNodo a, HuffmanNodo b) {
  return a.frecuencia.compareTo(b.frecuencia);
}

HuffmanNodo arbolHuffman(Map<String, int> mapaFrecuencia) {
  var colaPrioridad = PriorityQueue<HuffmanNodo>(comparadorNodos);

  mapaFrecuencia.forEach((char, frec) {
    colaPrioridad.add(HuffmanNodo(char, frec));
  });

  while (colaPrioridad.length > 1) {
    var izquierda = colaPrioridad.removeFirst();
    var derecha = colaPrioridad.removeFirst();
    var combinar = HuffmanNodo(null, izquierda.frecuencia + derecha.frecuencia);
    combinar.izquierda = izquierda;
    combinar.derecha = derecha ;
    colaPrioridad.add(combinar);
  }

  return colaPrioridad.removeFirst();
}

void generarcodigosHuffman(HuffmanNodo nodo, String codigo, Map<String, String> codigosHuffman) {
  if (nodo.caracter != null) {
    codigosHuffman[nodo.caracter!] = codigo;
    return;
  }

  if (nodo.izquierda != null) {
    generarcodigosHuffman(nodo.izquierda!, codigo + '0',codigosHuffman);
  }
  if (nodo.derecha != null) {
    generarcodigosHuffman(nodo.derecha!, codigo + '1', codigosHuffman);
  }
}

String encode(String text) {
  var mapaFrecuencia = <String, int>{};
  for (var char in text.split('')) {
    mapaFrecuencia[char] = (mapaFrecuencia[char] ?? 0) + 1;
  }

  var raiz = arbolHuffman(mapaFrecuencia);

  var codigosHuffman = <String, String>{};
  generarcodigosHuffman(raiz, '', codigosHuffman);

  var textoCodificado = text.split('').map((char) => codigosHuffman[char]).join();

  return textoCodificado;
}

void main() {
  var texto = 'ejemplo de texto para comprimir usando Huffman';
  var textoCodificado = encode(texto);
  print('Texto original: $texto');
  print('Texto codificado: $textoCodificado');
}