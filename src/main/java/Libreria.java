import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Libreria {


        FileWriter fichero;
        PrintWriter escribir;
        GitHub github = null;
        GHRepository repo = null;


        public GitHub Token(String pathalFich) {
            File fichero = new File(pathalFich);

            if (fichero.exists()) {
                try {
                    github = new GitHubBuilder()
                            .fromPropertyFile(pathalFich)
                            .build();
                } catch (IOException e) {
                    System.out.println(e.toString()));
                }

            } else {
                try {
                    fichero = new FileWriter(fichero, true);
                    escribir = new PrintWriter(fichero);
                    String token = JOptionPane.showInputDialog("Introducir token");
                    escribir.print("oauth=" + token);
                    github = new GitHubBuilder()
                            .withOAuthToken(token)
                            .build();

                } catch (IOException e) {
                    System.out.println(e.getMessage());

                } finally {
                    escribir.close();
                }
            }
            return github;
        }

        public void crearRepositorio() {
            try {
                repo = github
                        .createRepository(JOptionPane.showInputDialog("Introduce el nombre con el cual quieres llamar a tu repositorio"))
                        .create();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
    }
