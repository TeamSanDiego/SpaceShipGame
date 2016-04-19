package display;

import javax.swing.*;
import java.awt.*;

public class Display {
    private JFrame frame;
    private Canvas canvas;

    public Display(String name, int width, int height) {
        // inicializirame poleto s ime frame s obekta koito sme suzdali blagodarenie
        // na tozi konstruktor
        this.frame = new JFrame(name);
        // predpochitan razmer
        this.frame.setPreferredSize(new Dimension(width, height));
        // minimalnite i maksimalnite razmeri- taka che da ne se promenq
        this.frame.setMinimumSize(new Dimension(width, height));
        this.frame.setMaximumSize(new Dimension(width, height));
        // freima trqbva da se vijda
        this.frame.setVisible(true);
        // fokus - towa e koq papka ti e na preden plan
        // kogato go setnem na true mojem da go naprawim aktiwen kato cuknem vurhu nego
        // durji se kato obiknovenna papka koqto mojem da vadim na preden plan kato
        // cuknem na neq
        this.frame.setFocusable(true);
        // da ne se resaizva
        this.frame.setResizable(false);
        // za da izleze kogato natisnem hiksa(za wseki sluchai da ne se bugne neshto)
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // za da si centriram freima w sredata na dekstopa
        this.frame.setLocationRelativeTo(null);

        // inicializiram si canvasa
        this.canvas = new Canvas();
        // za da sme sigurni che canvasa shte e sus sushtite razmeri kato frame
        this.canvas.setPreferredSize(new Dimension(width, height));
        this.canvas.setMinimumSize(new Dimension(width, height));
        this.canvas.setMaximumSize(new Dimension(width, height));
        this.canvas.setFocusable(true);

        // kum freima dobavqme canvasa
        this.frame.add(this.canvas);
        // nakraq paketirame vsichko ww edin gotov freim koito da meje da se vizualizira
        this.frame.pack();
    }

    // getur za canvasa - poletata trqbva da sa private i da se dostupvat
    // prez metod, koito narichame getur
    public Canvas getCanvas() {
        return canvas;
    }
}
