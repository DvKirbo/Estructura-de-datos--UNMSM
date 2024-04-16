import 'dart:io';
import 'dart:collection';

HashSet<String> getPalabras(String texto) {
  HashSet<String> words = HashSet<String>();
  RegExp regExp = RegExp(r"[a-zA-ZáéíóúÁÉÍÓÚ']+");
  Iterable<Match> matches = regExp.allMatches(texto.toLowerCase());
  for (Match match in matches) {
    words.add(match.group(0)!);
  }
  return words;
}

HashSet<String> getCoincidencias(HashSet<String> list1, HashSet<String> list2) {
  HashSet<String> result = HashSet<String>();
  List<String> l1 = list1.toList()..sort();
  List<String> l2 = list2.toList()..sort();
  int i1 = 0;
  int i2 = 0;
  while (i1 < l1.length && i2 < l2.length) {
    int num = l1[i1].compareTo(l2[i2]);
    if (num == 0) {
      result.add(l1[i1]);
      i1++;
      i2++;
    } else if (num < 0)
      i1++;
    else if (num > 0) i2++;
  }
  return result;
}

void reporte(HashSet<String> list1, HashSet<String> list2, HashSet<String> comun) {
  print("Archivo #1 palabras = ${list1.length}");
  print("Archivo #2 palabras = ${list2.length}");
  print("Palabras en común = ${comun.length}");
  double pct1 = 100.0 * comun.length / list1.length;
  double pct2 = 100.0 * comun.length / list2.length;
  print("% del archivo 1 en superposición  = $pct1");
  print("% del archivo 2 en superposición  = $pct2");
}

void main() {
  String texto1 = File("./t1.txt").readAsStringSync();
  String texto2 = File("./t2.txt").readAsStringSync();
  HashSet<String> list1 = getPalabras(texto1);
  HashSet<String> list2 = getPalabras(texto2);

  print("Texto 1:");
  print(list1);
  print("\nTexto 2:");
  print(list2);

  HashSet<String> comun = getCoincidencias(list1, list2);

  print("\nPalabras en común:");
  print(comun);

  reporte(list1, list2, comun);
}