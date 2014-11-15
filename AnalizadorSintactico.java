package analizadorSintactico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Sofia Legal - David Gomez
 * 
*/
public class AnalizadorSintactico {

    /**
     * @param args the command line arguments
     *
     */
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    FileWriter fichero = null;
    PrintWriter pw = null;
    List<String> entrada = new ArrayList<String>();

    public void escribir(String salida, String palabra, boolean cerrar) {

        try {
            if (salida.equals("")) {
                // System.out.println(System.getProperty("java.class.path") + "\\salida.txt");
                fichero = new FileWriter(System.getProperty("java.class.path") + "\\salida.txt");
            } else {
                fichero = new FileWriter(salida);
            }
            pw = new PrintWriter(fichero);
            pw.println(palabra);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // cerrar fichero
                if (cerrar) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void leer(String fuente, String salida) throws FileNotFoundException {
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            if (fuente.equals("")) {
                archivo = new File("C:\\fuente.txt");
            } else {
                archivo = new File(fuente);
            }
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            String caracter = "";
            String valor = "";
            String errores = "";

            AnalizadorSintactico t = new AnalizadorSintactico();
            String archivo = "";
            int cont = 0;
            while ((linea = br.readLine()) != null) {
                cont++;
                //System.out.println("linea " + linea);
                String finDeLinea = "";

                // recorre cada linea
                caracter = "";
                for (int i = 0; i < linea.length(); i++) {

                    // mientras no se espacio  ,  o "" concatena para obtener el token
                    if (linea.charAt(i) != ' ' && linea.charAt(i) != ',' && linea.charAt(i) != '"') {

                        caracter = caracter + linea.charAt(i);

                    } else if (linea.charAt(i) == ',') {
                        // si el caracter es , se guarda para concatenar al final
                        finDeLinea = "COMA";

                    }

                    //
                    if (linea.charAt(i) == '"' && i != linea.length() - 1) {

                        int contador = i + 1;
                        caracter = caracter + linea.charAt(i);
                        while (contador < linea.length() && linea.charAt(contador) != '"') {
                            // System.out.println("token " + linea.charAt(contador));
                            //System.out.println("Entra a while " + contador);
                            caracter = caracter + linea.charAt(contador);
                            contador++;
                        }
                        if (caracter != "") {
                            caracter = caracter + "\"";
                        }
                        i = contador;
                        //System.out.println("Caracter " + caracter);

                    } else if (linea.charAt(i) == '"' && i == linea.length() - 1) {
                        valor = "Cadena erronea linea : -" + cont;
                        archivo = archivo + valor;

                    }

                    if ((linea.charAt(i) == ' ' || linea.charAt(i) == ',') && !caracter.equals("")) {

                        if ("[".equals(caracter)) {
                            valor = "L_CORCHETE";
                            entrada.add("L_CORCHETE");
                        } else if ("]".equals(caracter)) {
                            valor = "R_CORCHETE";
                            entrada.add("R_CORCHETE");
                        } else if ("{".equals(caracter)) {
                            valor = "L_LLAVE";
                            entrada.add("L_LLAVE");
                        } else if ("}".equals(caracter)) {
                            valor = "R_LLAVE";
                            entrada.add("R_LLAVE");
                        } else if (",".equals(caracter)) {
                            valor = ",";
                        } else if (":".equals(caracter)) {
                            valor = "DOS_PUNTOS";
                            entrada.add("DOS_PUNTOS");
                        } else if ("true".equals(caracter) || "TRUE".equals(caracter)) {
                            valor = "PR_TRUE";
                            entrada.add("PR_TRUE");
                        } else if ("false".equals(caracter) || "FALSE".equals(caracter)) {
                            valor = "PR_FALSE";
                            entrada.add("PR_FALSE");
                        } else if ("null".equals(caracter) || "NULL".equals(caracter)) {
                            valor = "PR_NULL";
                            entrada.add("PR_NULL");
                        } else if (caracter.charAt(0) == '"' && caracter.charAt(caracter.length() - 1) == '"') {

                            valor = "LITERAL_CADENA";
                            entrada.add("LITERAL_CADENA");
                        }

                        archivo = archivo + valor + " ";
                        // si se encontro una , se agrega al final
                        if (!finDeLinea.equals("")) {
                            archivo = archivo + " " + finDeLinea;
                            entrada.add("COMA");

                        }
                        caracter = "";
                    }

                    if (i == linea.length() - 1 && caracter != "") {
                        if ("[".equals(caracter)) {
                            valor = "L_CORCHETE";
                            entrada.add("L_CORCHETE");
                        } else if ("]".equals(caracter)) {
                            valor = "R_CORCHETE";
                            entrada.add("R_CORCHETE");
                        } else if ("{".equals(caracter)) {
                            valor = "L_LLAVE";
                            entrada.add("L_LLAVE");
                        } else if ("}".equals(caracter)) {
                            valor = "R_LLAVE";
                            entrada.add("R_LLAVE");
                        } else if (",".equals(caracter)) {
                            valor = ",";
                        } else if (":".equals(caracter)) {
                            valor = "DOS_PUNTOS";
                            entrada.add("DOS_PUNTOS");
                        } else if ("true".equals(caracter) || "TRUE".equals(caracter)) {
                            valor = "PR_TRUE";
                            entrada.add("PR_TRUE");
                        } else if ("false".equals(caracter) || "FALSE".equals(caracter)) {
                            valor = "PR_FALSE";
                            entrada.add("PR_FALSE");
                        } else if ("null".equals(caracter) || "NULL".equals(caracter)) {
                            valor = "PR_NULL";
                            entrada.add("PR_NULL");
                        } else if (caracter.charAt(0) == '"' && caracter.charAt(caracter.length() - 1) == '"') {

                            valor = "LITERAL_CADENA";
                            entrada.add("LITERAL_CADENA");
                        } else {
                            valor = "ERROR linea ";
                        }

                        archivo = archivo + valor + " ";
                        // si se encontro una , se agrega al final
                        if (!finDeLinea.equals("")) {
                            archivo = archivo + " " + finDeLinea;
                            entrada.add("COMA");

                        }
                        caracter = "";
                    }
                }

                // se escribe en el archivo
                archivo = archivo + "\n";
                //archivo = archivo + "EOF";
                t.escribir(salida, archivo, true);

            }
            archivo = archivo + "EOF";
            entrada.add("EOF");
            t.escribir(salida, archivo, true);
            for (String a2 : entrada) {
                System.out.println("token: " + a2);
            }

            LL1();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    public void LL1() {
        List<String> pila = new ArrayList<String>();
        int contErrores = 0;
        pila.add("JSONML");
        System.out.println("token " + entrada.get(0));
        System.out.println("pila " + pila.get(pila.size() - 1));
        while (entrada.size() > 0) {
            System.out.println("token " + entrada.get(0));
            System.out.println("pila " + pila.get(pila.size() - 1));
            if ("JSONML".equals(pila.get(pila.size() - 1))) {
                if ("L_CORCHETE".equals(entrada.get(0)) || "LITERAL_CADENA".equals(entrada.get(0))) {
                    System.out.println("entraaaa en json");
                    pila.remove(pila.size() - 1);
                    pila.add("EOF");
                    pila.add("ELEMENT");
                } else {
                    System.out.println("Error se esperaba L_CORCHETE o LITERAL_CADENA");
                    while (!"EOF".equals(entrada.get(0))) {
                        entrada.remove(0);
                    }
                    contErrores++;
                }

            }
            if ("ELEMENT".equals(pila.get(pila.size() - 1))) {
                if ("L_CORCHETE".equals(entrada.get(0))) {
                    pila.remove(pila.size() - 1);
                    pila.add("R_CORCHETE");
                    pila.add("ELEMENT2");
                    pila.add("TAGNAME");
                    pila.add("L_CORCHETE");
                } else {
                    if ("LITERAL_CADENA".equals(entrada.get(0))) {
                        pila.remove(pila.size() - 1);
                        pila.add("LITERAL_CADENA");
                    } else {
                        System.out.println("Error se esperaba L_CORCHETE o LITERAL_CADENA");
                        while (!"EOF".equals(entrada.get(0)) || !"L_CORCHETE".equals(entrada.get(0)) || 
                                !"LITERAL_CADENA".equals(entrada.get(0))) {
                            entrada.remove(0);
                        }
                        contErrores++;
                    }
                }
            }
            if ("ELEMENT2".equals(pila.get(pila.size() - 1))) {
                if ("COMA".equals(entrada.get(0))) {
                    pila.remove(pila.size() - 1);
                    pila.add("ELEMENT2");
                    pila.add("ELEMENT3");
                    pila.add("COMA");
                } else {
                    if ("R_CORCHETE".equals(entrada.get(0)) || "COMA".equals(entrada.get(0))) {
                        pila.remove(pila.size() - 1);
                    } else {
                        System.out.println("Error se esperaba COMA o VACIO");
                        while (!"R_CORCHETE".equals(entrada.get(0))) {
                            entrada.remove(0);
                        }
                        contErrores++;
                    }
                }
            }
            if ("ELEMENT3".equals(pila.get(pila.size() - 1))) {
                if ("L_LLAVE".equals(entrada.get(0))) {
                    pila.remove(pila.size() - 1);
                    pila.add("ATTRIBUTES");
                } else {
                    if ("R_LLAVE".equals(entrada.get(0))) {
                        pila.remove(pila.size() - 1);
                        pila.add("ELEMENT-LIST");
                    } else {
                        System.out.println("Error se esperaba L_LLAVE o R_LLAVE");
                        while (!"COMA".equals(entrada.get(0)) || !"R_CORCHETE".equals(entrada.get(0))) {
                            entrada.remove(0);
                        }
                        contErrores++;
                    }
                }
            }

            if ("TAGNAME".equals(pila.get(pila.size() - 1))) {
                if ("LITERAL_CADENA".equals(entrada.get(0))) {
                    pila.remove(pila.size() - 1);
                    pila.add("LITERAL_CADENA");
                } else {
                    System.out.println("Error se esperaba LITERAL_CADENA");
                    while (!"EOF".equals(entrada.get(0)) || !"COMA".equals(entrada.get(0)) || 
                            !"L_CORCHETE".equals(entrada.get(0))
                            || !"LITERAL_CADENA".equals(entrada.get(0))) {
                        entrada.remove(0);
                    }
                    contErrores++;
                }
            }

            if ("ATTRIBUTES".equals(pila.get(pila.size() - 1))) {
                if ("L_LLAVE".equals(entrada.get(0))) {
                    pila.remove(pila.size() - 1);
                    pila.add("R_LLAVE");
                    pila.add("ATT");
                    pila.add("L_LLAVE");
                } else {
                    System.out.println("Error se esperaba L_LLAVE");
                    contErrores++;
                    while (!"EOF".equals(entrada.get(0)) || !"COMA".equals(entrada.get(0)) || 
                            !"L_CORCHETE".equals(entrada.get(0))
                            || !"LITERAL_CADENA".equals(entrada.get(0))) {
                        entrada.remove(0);
                    }
                }
            }

            if ("ATT".equals(pila.get(pila.size() - 1))) {
                if ("LITERAL_CADENA".equals(entrada.get(0))) {
                    pila.remove(pila.size() - 1);
                    pila.add("ATTRIBUTE-LIST");
                } else {
                    if ("R_LLAVE".equals(entrada.get(0))) {
                        pila.remove(pila.size() - 1);
                    } else {
                        System.out.println("Error se esperaba R_LLAVE o VACIO");
                        contErrores++;
                        while (!"R_LLAVE".equals(entrada.get(0))) {
                            entrada.remove(0);
                        }
                    }
                }
            }

            if ("ATTRIBUTE-LIST".equals(pila.get(pila.size() - 1))) {
                if ("LITERAL_CADENA".equals(entrada.get(0))) {
                    pila.remove(pila.size() - 1);
                    pila.add("ATTLIST");
                    pila.add("ATTRIBUTE");
                } else {
                    System.out.println("Error se esperaba LITERAL_CADENA");
                    contErrores++;
                    while (!"R_LLAVE".equals(entrada.get(0))) {
                        entrada.remove(0);
                    }
                }
            }

            if ("ATTLIST".equals(pila.get(pila.size() - 1))) {
                if ("COMA".equals(entrada.get(0))) {
                    pila.remove(pila.size() - 1);
                    pila.add("ATTLIST");
                    pila.add("ATTRIBUTE");
                    pila.add("COMA");
                } else {
                    if ("R_LLAVE".equals(entrada.get(0))) {
                        pila.remove(pila.size() - 1);
                    } else {
                        System.out.println("Error se esperaba COMA o VACIO");
                        contErrores++;
                        while (!"R_LLAVE".equals(entrada.get(0))) {
                            entrada.remove(0);
                        }
                    }
                }
            }

            if ("ATTRIBUTE".equals(pila.get(pila.size() - 1))) {
                if ("LITERAL_CADENA".equals(entrada.get(0))) {
                    pila.remove(pila.size() - 1);
                    pila.add("ATTRIBUTE-VALUE");
                    pila.add("DOS_PUNTOS");
                    pila.add("ATTRIBUTE-NAME");
                } else {
                    System.out.println("Error se esperaba LITERAL_CADENA");
                    contErrores++;
                    while (!"R_LLAVE".equals(entrada.get(0)) || !"COMA".equals(entrada.get(0))) {
                        entrada.remove(0);
                    }
                }
            }

            if ("ATTRIBUTE-NAME".equals(pila.get(pila.size() - 1))) {
                if ("LITERAL_CADENA".equals(entrada.get(0))) {
                    pila.remove(pila.size() - 1);
                    pila.add("LITERAL_CADENA");
                } else {
                    System.out.println("Error se esperaba LITERAL_CADENA");
                    contErrores++;
                    while (!"DOS_PUNTOS".equals(entrada.get(0))) {
                        entrada.remove(0);
                    }
                }
            }

            if ("ATTRIBUTE-VALUE".equals(pila.get(pila.size() - 1))) {
                if ("LITERAL_CADENA".equals(entrada.get(0))) {
                    pila.remove(pila.size() - 1);
                    pila.add("LITERAL_CADENA");
                } else {
                    if ("LITERAL_NUM".equals(entrada.get(0))) {
                        pila.remove(pila.size() - 1);
                        pila.add("LITERAL_NUM");
                    } else {
                        if ("PR_TRUE".equals(entrada.get(0))) {
                            pila.remove(pila.size() - 1);
                            pila.add("PR_TRUE");
                        } else {
                            if ("PR_FALSE".equals(entrada.get(0))) {
                                pila.remove(pila.size() - 1);
                                pila.add("PR_FALSE");
                            } else {
                                if ("PR_NULL".equals(entrada.get(0))) {
                                    pila.remove(pila.size() - 1);
                                    pila.add("PR_NULL");
                                } else {
                                    System.out.println("Se esperaba LITERAL_CADENA, LITERAL_NUM, PR_TRUE, PR_FALSE, PR_NULL");
                                    contErrores++;
                                    while (!"R_LLAVE".equals(entrada.get(0)) || !"COMA".equals(entrada.get(0))) {
                                        entrada.remove(0);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if ("ELEMENT-LIST".equals(pila.get(pila.size() - 1))) {
                if ("L_CORCHETE".equals(entrada.get(0))) {
                    pila.remove(pila.size() - 1);
                    pila.add("ELELIST");
                    pila.add("ELEMENT");
                } else {
                    if ("LITERAL_CADENA".equals(entrada.get(0))) {
                        pila.remove(pila.size() - 1);
                        pila.add("ELELIST");
                        pila.add("ELEMENT");
                    } else {
                        System.out.println("Error se esperaba L_CORCHETE o LITERAL_CADENA");
                        contErrores++;
                        while (!"R_CORCHETE".equals(entrada.get(0)) || !"COMA".equals(entrada.get(0))) {
                            entrada.remove(0);
                        }
                    }
                }
            }

            if ("ELELIST".equals(pila.get(pila.size() - 1))) {
                if ("COMA".equals(entrada.get(0))) {
                    pila.remove(pila.size() - 1);
                    pila.add("ELELIST");
                    pila.add("ELEMENT");
                    pila.add("COMA");
                } else {
                    if ("COMA".equals(entrada.get(0)) || "R_CORCHETE".equals(entrada.get(0))) {
                        pila.remove(pila.size() - 1);
                    } else {
                        System.out.println("Error se esperaba COMA o VACIO");
                        contErrores++;
                        while (!"R_CORCHETE".equals(entrada.get(0)) || !"COMA".equals(entrada.get(0))) {
                            entrada.remove(0);
                        }
                    }
                }
            }
            if (pila.get(pila.size() - 1).equals(entrada.get(0))) {
                pila.remove(pila.size() - 1);
                entrada.remove(0);
            }

        }
        System.out.println("FIN DE REVISION SE HAN ENCONTRADO " + contErrores + " ERRORES");
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        AnalizadorSintactico t = new AnalizadorSintactico();
        System.out.println("Ingrese direccion de fuente: ");
        String fuente = in.nextLine();
        System.out.println("Ingrese direccion del archivo salida: ");
        String salida = in.nextLine();
        if (salida.equals("")) {
            System.out.println("Se creara el archivo en la carpeta build/classes del proyecto");
        }
        t.leer(fuente, salida);
        // TODO code application logic here
    }

}
